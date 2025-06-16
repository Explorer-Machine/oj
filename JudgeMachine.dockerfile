# 基于Ubuntu 24.04官方镜像
FROM ubuntu:24.04

# 设置环境变量（避免apt安装交互）
ENV DEBIAN_FRONTEND=noninteractive

# 安装基础工具链和语言环境（整合C/C++/Java/Python）
RUN apt-get update && \
    apt-get install -y \
    gcc g++ make \
    openjdk-17-jdk \
    python3 python3-pip \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

# 配置Java环境变量
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV PATH=$JAVA_HOME/bin:$PATH

# 创建工作目录并复制脚本
WORKDIR /app
COPY jt.sh /app/
RUN chmod +x /app/jt.sh

# 设置容器入口点
ENTRYPOINT ["/app/jt.sh"]