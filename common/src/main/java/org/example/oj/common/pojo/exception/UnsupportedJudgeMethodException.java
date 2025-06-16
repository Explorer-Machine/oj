package org.example.oj.common.pojo.exception;

public class UnsupportedJudgeMethodException extends RuntimeException{
    private String message = "不支持的评测方式";

    @Override
    public String getMessage() {
        return message;
    }
}
