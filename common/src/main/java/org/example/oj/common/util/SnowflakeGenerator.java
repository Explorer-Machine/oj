package org.example.oj.common.util;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 雪花算法ID生成器
 * 结构：64位 = 1位符号位(0) + 41位时间戳 + 5位数据中心ID + 5位机器ID + 12位序列号
 * 特性：每秒可生成409.6万个ID，支持69年时间周期
 */
public class SnowflakeGenerator {
    // 基础参数配置[3,11](@ref)
    private static final long EPOCH = 1743676800000L; // 起始时间(2025-04-01)
    private static final long DATA_CENTER_BITS = 5L;
    private static final long WORKER_ID_BITS = 5L;
    private static final long SEQUENCE_BITS = 12L;

    // 最大值计算[4](@ref)
    private static final long MAX_DATA_CENTER_ID = ~(-1L << DATA_CENTER_BITS);
    private static final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);
    private static final long MAX_SEQUENCE = ~(-1L << SEQUENCE_BITS);

    // 位移计算[3](@ref)
    private static final long TIMESTAMP_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATA_CENTER_BITS;
    private static final long DATA_CENTER_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;
    private static final long WORKER_SHIFT = SEQUENCE_BITS;

    private final long dataCenterId;
    private final long workerId;
    private final AtomicLong sequence = new AtomicLong(0);
    private volatile long lastTimestamp = -1L;

    // 单例模式[9](@ref)
    private static class Holder {
        private static final SnowflakeGenerator INSTANCE = new SnowflakeGenerator(1, 1);
    }

    public static SnowflakeGenerator getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * @param dataCenterId 数据中心ID (0-31)
     * @param workerId     机器ID (0-31)
     */
    public SnowflakeGenerator(long dataCenterId, long workerId) {
        if (dataCenterId > MAX_DATA_CENTER_ID || dataCenterId < 0) {
            throw new IllegalArgumentException("数据中心ID范围: 0-" + MAX_DATA_CENTER_ID);
        }
        if (workerId > MAX_WORKER_ID || workerId < 0) {
            throw new IllegalArgumentException("机器ID范围: 0-" + MAX_WORKER_ID);
        }
        this.dataCenterId = dataCenterId;
        this.workerId = workerId;
    }

    /**
     * 生成唯一ID（线程安全）
     */
    public synchronized long nextId() {
        long currentTimestamp = timeGen();

        // 时钟回拨检测[5,11](@ref)
        if (currentTimestamp < lastTimestamp) {
            throw new RuntimeException("时钟回拨异常，拒绝生成ID. 回拨时间: " + (lastTimestamp - currentTimestamp) + "ms");
        }

        // 同一毫秒内序列递增
        if (lastTimestamp == currentTimestamp) {
            long currentSequence = sequence.incrementAndGet() & MAX_SEQUENCE;
            if (currentSequence == 0) { // 当前毫秒序列耗尽
                currentTimestamp = waitNextMillis(lastTimestamp);
            }
        } else {
            sequence.set(0);
        }

        lastTimestamp = currentTimestamp;

        return ((currentTimestamp - EPOCH) << TIMESTAMP_SHIFT)
                | (dataCenterId << DATA_CENTER_SHIFT)
                | (workerId << WORKER_SHIFT)
                | sequence.get();
    }

    /**
     * 阻塞到下一毫秒[7](@ref)
     */
    private long waitNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 获取当前毫秒数
     */
    protected long timeGen() {
        return Instant.now().toEpochMilli();
    }

    /**
     * 解析ID结构[8](@ref)
     */
    public static void parseId(long id) {
        long timestamp = (id >>> TIMESTAMP_SHIFT) + EPOCH;
        long dataCenterId = (id >>> DATA_CENTER_SHIFT) & MAX_DATA_CENTER_ID;
        long workerId = (id >>> WORKER_SHIFT) & MAX_WORKER_ID;
        long sequence = id & MAX_SEQUENCE;

        System.out.printf("ID解析：\n  时间：%tc\n  数据中心：%d\n  机器：%d\n  序列号：%d\n",
                timestamp, dataCenterId, workerId, sequence);
    }
}