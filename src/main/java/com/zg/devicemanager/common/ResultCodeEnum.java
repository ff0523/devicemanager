package com.zg.devicemanager.common;

import lombok.Getter;

@Getter
public enum ResultCodeEnum {
    SUCCESS(200,"成功"),
    FAIL(201, "失败"),
    SERVICE_ERROR(202, "服务异常"),
    DATA_ERROR(204, "数据异常"),
    SIGN_ERROR(300, "签名错误"),
    PAY_PASSWORD_ERROR(401, "支付密码错误"),
    REPEAT_ERROR(402, "重复提交")
    ;

    private Integer code;
    private String message;

    private ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
