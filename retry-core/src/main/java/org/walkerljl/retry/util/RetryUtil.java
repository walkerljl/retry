package org.walkerljl.retry.util;

/**
 * Retry util
 *
 * @author xingxun
 */
public class RetryUtil {

    /**
     * Build identifer
     *
     * @param bizType Business type
     * @param bizId Business id
     * @return
     */
    public static String buildIdentifier(String bizType, String bizId) {
        return String.format("%s:%s", bizType, bizId);
    }
}