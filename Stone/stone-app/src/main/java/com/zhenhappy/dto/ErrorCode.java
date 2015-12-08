package com.zhenhappy.dto;

public enum ErrorCode {
    SUCCESS(0, "操作成功"),
    ERROR(1, "服务器错误"),
    CMS_ERROR(11, "资讯服务器错误"),
    DATAERROR(9, "请求参数错误"), CHECKCODE_FREQUENCE(83, "验证码发送间隔不能小于1分钟"), USERNAME_REGISTED(8, "用户名已经被注册"), OLD_PASSWORD_ERROR(
            82, "旧密码错误"), ALREADY_COLLECT(71, "已经收藏过该展商"), LOGINFAIL(81, "用户名或密码错误"), USERLOCKED(84, "用户被锁定"), USERUNVERIFY(85, "用户未验证通过"), USERUNREGIST(86, "用户未注册"), VERIFYUSED(87, "验证邮箱已经失效");

    private Integer code;
    private String message;

    private ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return message;
    }
}
