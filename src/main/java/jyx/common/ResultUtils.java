package jyx.common;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 结果的处理类，统一json 返回值
 */
public final class ResultUtils {
    private ResultUtils() {
    }
    //    public static final int PARAME_ERROR = -2;
    public static final int SUCCESS = 0;
    public static final int ERROR = 1;
    public static final int WARNING = 2;
    public static final int info = 3;

    public enum Code {
        SUCCESS(ResultUtils.SUCCESS, "操作成功"),
        ERROR(ResultUtils.ERROR, "发生异常"),
        PARAME_ERROR(ResultUtils.WARNING, "参数错误"),
        LIMITER_ERROR(ResultUtils.WARNING, "访问频率过快"),
        WARNING(ResultUtils.WARNING, "");

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

    public static Map<String, Object> set(Code c) {
        return set(c.code,c.msg);
    }

    public static Map<String, Object> set(Code c,String msg) {
        return set(c.code,msg);
    }

    public static Map<String, Object> set(int code, String msg) {
        Map<String, Object> map = new HashMap<String, Object>();
        set(map, code, msg);
        return map;
    }

    public static Map<String, Object> set(Object obj) {
        Map<String, Object> map = new HashMap<String, Object>();
        set(map, obj);
        return map;
    }

    public static void set(Map<String, Object> map, Code c) {
        set(map,c.code,c.msg);
    }

    public static void set(Map<String, Object> map, int code, String msg) {

        set(map,code,null,msg);
    }
    public static void set(Map<String, Object> map, String msg) {
        set(map,0,null,msg);
    }
    public static void set(Map<String, Object> map, Object data) {
        set(map,0,data,null);
    }
    public static void set(Map<String, Object> map, Object data, String msg) {
        set(map,0,data,msg);
    }
    private static void set(Map<String, Object> map, int code, Object data, String msg) {
        if(map == null) throw new NullPointerException();

        map.put("code", code);

        if(data!=null)
            map.put("data", data);
        if(StringUtils.isNotEmpty(msg)) {
            map.put("msg", msg);
        }
    }

    public static void main(String[] args) {

    }
}


