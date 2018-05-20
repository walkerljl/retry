package org.walkerljl.retry.standard.machine;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.walkerljl.retry.standard.machine.abstracts.AbstractMachine;
import org.walkerljl.retry.standard.machine.exception.CannotStartMachineException;
import org.walkerljl.retry.standard.machine.exception.CannotStopMachineException;
import org.walkerljl.retry.standard.machine.exception.MachineException;
import org.walkerljl.retry.standard.resource.Resource;

import java.util.Collection;

/**
 * MachineRepositoryTest
 *
 * @author xingxun
 * @Date 2018/5/19
 */
public class MachineRepositoryTest {

    @Test
    public void test() {

        String groupId = "groupId";
        String machineId = "resourceId";

        boolean actualFlag = false;
        try {
            MachineRepository.lookup(null, machineId);
        } catch (MachineException e) {
            actualFlag = true;
        }
        Assert.assertTrue(actualFlag);
        actualFlag = false;

        try {
            MachineRepository.lookup(groupId, null);
        } catch (MachineException e) {
            actualFlag = true;
        }
        Assert.assertTrue(actualFlag);
        actualFlag = false;

        Resource actual = MachineRepository.lookup(groupId, machineId);
        Assert.assertNull(actual);

        Machine expected = new AbstractMachine() {
            @Override
            public void processStart() throws CannotStartMachineException {

            }

            @Override
            public void processStop() throws CannotStopMachineException {

            }

            @Override
            public String getId() {
                return machineId;
            }

            @Override
            public String getGroup() {
                return groupId;
            }
        };
        MachineRepository.register(expected.getGroup(), expected.getId(), expected);
        actual = MachineRepository.lookup(groupId, machineId);
        Assert.assertEquals(actual, expected);

        Collection<Machine> allMachines = MachineRepository.lookupAll();
        Assert.assertEquals(allMachines.size(), 1);
        Assert.assertEquals(allMachines.iterator().next(), expected);
        try {
            allMachines.add(expected);
        } catch (UnsupportedOperationException e) {
            actualFlag = true;
        }
        Assert.assertTrue(actualFlag);

        MachineRepository.unregister(expected.getGroup(), expected.getId());
        actual = MachineRepository.lookup(groupId, machineId);
        Assert.assertNull(actual);
    }
}
