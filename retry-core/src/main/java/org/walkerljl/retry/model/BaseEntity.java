package org.walkerljl.retry.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Base entity
 *
 * @author xingxun
 */
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 8613057061704029872L;

    private static String buildJSONString(Object obj) {
        try {
            StringBuilder json = new StringBuilder("{");

            Class<?> clz = obj.getClass();
            while (clz != null) {
                Field[] fs = clz.getDeclaredFields();
                clz = clz.getSuperclass();//遍历父类
                if (fs == null || fs.length <= 0) {
                    continue;
                }

                for (Field field : fs) {
                    field.setAccessible(true);//修改访问权限
                    Object v = field.get(obj);
                    if (v == null || Modifier.isStatic(field.getModifiers())) {
                        continue;
                    }
                    json.append("\"").append(field.getName()).append("\":");

                    if (v instanceof String) {
                        json.append("\"").append(v).append("\"");
                    } else {
                        json.append(v);
                    }
                    json.append(",");
                }
            }
            return json.append("}").toString().replaceAll(",+}$", "}");
        } catch (Exception e) {
            return null;
        }
    }

    public String toJSONString(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            String json = convertJSONString(obj);
            if (json == null) {
                json = buildJSONString(obj);
            }
            return json;
        } catch (Exception e) {
            return obj.getClass() + "@" + obj.hashCode();
        }
    }

    private String convertJSONString(Object obj) {
        try {
            Class<?> clz = null;
            try {
                clz = Class.forName("com.alibaba.fastjson.JSON");
            } catch (ClassNotFoundException e1) {
                clz = Class.forName("com.jd.fastjson.JSON");
            }
            if (clz != null) {
                return (String) clz.getMethod("toJSONString", Object.class).invoke(null, obj);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return toJSONString(this);
    }
}