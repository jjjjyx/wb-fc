package jyx.common;

public enum Code {
    SUCCESS(ResultUtils.SUCCESS, "操作成功"),
    FAIL(ResultUtils.ERROR, "操作失败"),
    ERROR(ResultUtils.ERROR, "发生异常"),
    PARAME_ERROR(ResultUtils.WARNING, "参数错误"),
    LIMITER_ERROR(ResultUtils.WARNING, "访问频率过快"),
    PARAMETER_FAIL(-1, "参数错误"),
    ACCESS_FAIL(2, "权限不足"),
    PASS_FAIL(1, "密码错误"),
    WARNING(ResultUtils.WARNING, ""),
	LIMIT(1, "报名人数已达上限");



    Code(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private final int code;
    private final String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}