package jyx.common;

import ognl.DefaultTypeConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class DateConverter extends DefaultTypeConverter {
    private SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); // 转换为自己想要日期格式
    public Object convertValue(Map context, Object value, Class toType) {
        try {
            if (toType == Date.class) {
                String dataStr = ((String[]) value)[0];
                return f.parse(dataStr);
            } else if (toType == String.class) {
                return f.format((Date) value);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;

    }
}
