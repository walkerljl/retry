package org.walkerljl.retry.impl.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * JSONUtil
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
}