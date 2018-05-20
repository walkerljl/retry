package org.walkerljl.retry.impl.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.walkerljl.retry.model.RetryJob;

/**
 * JSONUtilTest
 *
 * @author xingxun
 * @Date 2018/5/19
 */
public class JSONUtilTest {

    private static final SerializerFeature[] FASTJSON_CONFIG = new SerializerFeature[] {
            SerializerFeature.DisableCircularReferenceDetect,
            SerializerFeature.WriteDateUseDateFormat,
            SerializerFeature.WriteMapNullValue,
            SerializerFeature.WriteNullStringAsEmpty
    };

    @Test
    public void toJSONString() {

        RetryJob retryJob = RetryObjectBuilderTest.newRetryJob(1);
        String expected = JSON.toJSONString(retryJob, FASTJSON_CONFIG);
        String actual = JSONUtil.toJSONString(retryJob);
        Assert.assertEquals(actual, expected);
    }
}
