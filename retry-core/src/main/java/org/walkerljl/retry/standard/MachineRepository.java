package org.walkerljl.retry.standard;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.walkerljl.retry.exception.machine.MachineException;

/**
 * MachineRepository
 *
 * @author xingxun
 * @Date 2016/12/12
 */
public class MachineRepository {

    private static final Map<String, Machine> REPOSITROY = new HashMap<String, Machine>();

    public static void register(String groupId, String machineId, Machine machine) {
        assertParams(groupId, machineId);
        if (machine == null) {
            throw new MachineException("Machine instance is null.");
        }
        REPOSITROY.put(buildIdentityKey(groupId, machineId), machine);
    }

    public static void unregister(String groupId, String machineId) {
        assertParams(groupId, machineId);
        REPOSITROY.remove(buildIdentityKey(groupId, machineId));
    }

    private static void assertParams(String groupId, String machineId) {
        if (groupId == null || "".equals(groupId.trim())) {
            throw new MachineException("Machine group id is blank.");
        }
        if (machineId == null || "".equals(machineId.trim())) {
            throw new MachineException("Machine id is blank.");
        }
    }

    public static Machine lookup(String groupId, String machineId) {
        assertParams(groupId, machineId);
        return REPOSITROY.get(buildIdentityKey(groupId, machineId));
    }

    public static Collection<Machine> lookupAll() {
        return Collections.unmodifiableCollection(REPOSITROY.values());
    }

    private static String buildIdentityKey(String groupId, String machineId) {
        return String.format("%s:%s", groupId, machineId);
    }
}
