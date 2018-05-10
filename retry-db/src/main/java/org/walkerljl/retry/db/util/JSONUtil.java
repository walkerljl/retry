package org.walkerljl.retry.db.util;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 *
 * @author xingxun
 */
public class JSONUtil {
    /**
     * fastjson 统一配置
     */
    private static final SerializerFeature[] FASTJSON_CONFIG = new SerializerFeature[] {
            SerializerFeature.DisableCircularReferenceDetect,
            SerializerFeature.WriteDateUseDateFormat,
            SerializerFeature.WriteMapNullValue,
            SerializerFeature.WriteNullStringAsEmpty
    };
    //,SerializerFeature.PrettyFormat};

    /**
     * 将对象转成JSON字符串
     *
     * @param object
     * @return
     */
    public static String toJSONString(Object object) {
        return JSON.toJSONString(object, FASTJSON_CONFIG);
    }

    /**
     * 将JSON字符串解析成Java对象
     *
     * @param jsonString
     * @param clazz
     * @return
     */
    public static <T> T parseObject(String jsonString, Class<T> clazz) {
        if (jsonString == null || "".equals(jsonString.trim())) {
            return null;
        }
        if (clazz == null || clazz == String.class) {
            return (T) jsonString;
        }
        return JSON.parseObject(jsonString, clazz);
    }

    /**
     * 解析成Map
     * @param jsonString JSON字符串
     * @return
     */
    public static Map<String, String> parseMap(String jsonString) {

        if (jsonString == null || "".equals(jsonString.trim())) {
            return null;
        }

        return JSON.parseObject(jsonString, Map.class);
    }

}