package org.walkerljl.retry.standard.machine;

import org.walkerljl.retry.standard.machine.exception.MachineException;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 机器仓库
 *
 * @author xingxun
 * @Date 2016/12/12
 */
public class MachineRepository {

    private static final Map<String, Machine> REPOSITROY = new HashMap<String, Machine>(1);

    /**
     * 注册
     *
     * @param groupId 组ID
     * @param machineId 机器ID
     * @param machine 机器
     */
    public static void register(String groupId, String machineId, Machine machine) {
        assertParams(groupId, machineId);
        if (machine == null) {
            throw new MachineException("Machine instance is null.");
        }
        REPOSITROY.put(buildIdentityKey(groupId, machineId), machine);
    }

    /**
     * 取消注册
     *
     * @param groupId 组ID
     * @param machineId 机器ID
     */
    public static void unregister(String groupId, String machineId) {
        assertParams(groupId, machineId);
        REPOSITROY.remove(buildIdentityKey(groupId, machineId));
    }

    /**
     * 查找机器
     *
     * @param groupId 组ID
     * @param machineId 机器ID
     * @return
     */
    public static Machine lookup(String groupId, String machineId) {
        assertParams(groupId, machineId);
        return REPOSITROY.get(buildIdentityKey(groupId, machineId));
    }

    /**
     * 查找所有机器
     *
     * @return
     */
    public static Collection<Machine> lookupAll() {
        return Collections.unmodifiableCollection(REPOSITROY.values());
    }

    /**
     * 断言参数
     *
     * @param groupId 组ID
     * @param machineId 机器ID
     */
    private static void assertParams(String groupId, String machineId) {
        if (groupId == null || "".equals(groupId.trim())) {
            throw new MachineException("Machine group id is blank.");
        }
        if (machineId == null || "".equals(machineId.trim())) {
            throw new MachineException("Machine id is blank.");
        }
    }

    /**
     * 构建身份Key
     *
     * @param groupId 组ID
     * @param machineId 机器ID
     * @return
     */
    private static String buildIdentityKey(String groupId, String machineId) {
        return String.format("%s:%s", groupId, machineId);
    }
}
