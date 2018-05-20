package org.walkerljl.retry.standard.resource;


import org.walkerljl.retry.standard.machine.exception.MachineException;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 资源仓库
 *
 * @author xingxun
 * @Date 2016/12/12
 */
public class ResourceRepository {

    private static final Map<String, Resource> REPOSITROY = new HashMap<String, Resource>(1);

    /**
     * 注册
     *
     * @param groupId 组ID
     * @param resourceId 资源ID
     * @param resource 资源
     */
    public static void register(String groupId, String resourceId, Resource resource) {
        assertParams(groupId, resourceId);
        if (resource == null) {
            throw new MachineException("Resource instance is null.");
        }
        REPOSITROY.put(buildIdentityKey(groupId, resourceId), resource);
    }

    /**
     * 取消注册
     *
     * @param groupId 组ID
     * @param resourceId 资源ID
     */
    public static void unregister(String groupId, String resourceId) {
        assertParams(groupId, resourceId);
        REPOSITROY.remove(buildIdentityKey(groupId, resourceId));
    }

    /**
     * 查找资源
     *
     * @param groupId 组ID
     * @param resourceId 资源ID
     * @return
     */
    public static Resource lookup(String groupId, String resourceId) {
        assertParams(groupId, resourceId);
        return REPOSITROY.get(buildIdentityKey(groupId, resourceId));
    }

    /**
     * 查找所有资源
     *
     * @return
     */
    public static Collection<Resource> lookupAll() {
        return Collections.unmodifiableCollection(REPOSITROY.values());
    }

    /**
     * 断言参数
     *
     * @param groupId 组ID
     * @param resourceId 资源ID
     */
    private static void assertParams(String groupId, String resourceId) {
        if (groupId == null || "".equals(groupId.trim())) {
            throw new MachineException("Resource group id is blank.");
        }
        if (resourceId == null || "".equals(resourceId.trim())) {
            throw new MachineException("Resource id is blank.");
        }
    }

    /**
     * 构建身份Key
     *
     * @param groupId 组ID
     * @param resourceId 资源ID
     * @return
     */
    private static String buildIdentityKey(String groupId, String resourceId) {
        return String.format("%s:%s", groupId, resourceId);
    }
}
