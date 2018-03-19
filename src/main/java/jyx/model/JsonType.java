package jyx.model;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Properties;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.ParameterizedType;
import org.hibernate.usertype.UserType;
//import org.postgresql.util.PGobject;

//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.databind.JavaType;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.type.SimpleType;

/**
 * Define a Jackson Serializer/Deserializer use to persist
 *
 * The implementation is a hibernate custom type
 *
 * @author Marvin H Froeder
 * @author Regis Leray
 */
public class JsonType implements UserType,ParameterizedType,Serializable {

    private static final int[] SQL_TYPES = { Types.VARCHAR };
    private Class<?> returnedClass = HashMap.class;
    private Gson gson = new Gson();
    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        if (x == y) {
            return true;
        } else if (x == null || y == null) {
            return false;
        } else {
            return x.equals(y);
        }
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        return null == x ? 0 : x.hashCode();
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {
//        System.out.println("set;"+value);
        if(value==null){
            st.setString(index,"");
        }else {
            st.setString(index,gson.toJson(value));
        }
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner) throws HibernateException, SQLException {
        String result = rs.getString(names[0]);
        Object map;
        try {
            if(StringUtils.isNotEmpty(result)) {
                map =  gson.fromJson(result, returnedClass);
            }else {
                map = newObject();
            }
        } catch (Exception e) {
            map = null;
        }
        return map;

    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        if(value == null) return value;
        else {
            Gson gson = new Gson();
            String json = gson.toJson(value);
            return gson.fromJson(json, returnedClass);
        }
    }

    /**
     * Optionnal
     */
    @Override
    public Object replace(Object original, Object target, Object owner)
            throws HibernateException {
        return original;
    }

    /**
     * (optional operation)
     *
     * @param value
     *
     * @throws HibernateException
     */
    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        return (Serializable) deepCopy(value);
    }

    /**
     * (optional operation)
     *
     * @param cached
     * @param owner
     *
     * @return the instance cached
     *
     * @throws HibernateException
     */
    @Override
    public Object assemble(Serializable cached, Object owner)
            throws HibernateException {
        return cached;
    }
    @Override
    public int[] sqlTypes() {
        return SQL_TYPES;
    }

    private Object newObject() {
        try {
            return this.returnedClass.newInstance();
        } catch (Exception e) {
            throw new HibernateException(e);
        }
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public Class<?> returnedClass() {
        return this.returnedClass;
    }

    @Override
    public void setParameterValues(Properties properties) {
        String type = (String) properties.get("type");
        if(StringUtils.isNotEmpty(type)) {
            try {
                this.returnedClass = Class.forName(type);
            } catch (ClassNotFoundException e) {
                throw new HibernateException(e);
            }
        }
    }

    public static void main(String[] args) {
        String a = "";
        Gson gson = new Gson();

        String s =String[].class.getName();
        System.out.println(s);
        Object o = null;

            o = gson.fromJson(a,HashMap.class);

        System.out.println(o);
        System.out.println(ArrayUtils.toString(o));
    }
}