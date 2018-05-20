package org.walkerljl.retry.standard.machine.abstracts;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.walkerljl.retry.standard.machine.Machine;
import org.walkerljl.retry.standard.machine.exception.CannotStartMachineException;
import org.walkerljl.retry.standard.machine.exception.CannotStopMachineException;

/**
 * AbstractMachineTest
 *
 * @author xingxun
 * @Date 2016/1/3
 */
public class AbstractMachineTest {

    @Test
    public void test() {

        Machine machine = new DefaultMacine();
        Assert.assertFalse(machine.isRunning());

        machine.init();
        machine.start();
        Assert.assertTrue(machine.isRunning());

        machine.stop();
        machine.destroy();
        Assert.assertFalse(machine.isRunning());

        Assert.assertEquals(machine.getId(), "default");
        Assert.assertEquals(machine.getName(), "default");
        Assert.assertEquals(machine.getGroup(), "default");

        String expectedInstanceId = machine.toString();
        Assert.assertEquals(machine.getInstanceId(), expectedInstanceId);
    }

}

class DefaultMacine extends AbstractMachine implements Machine {

    @Override
    public String getId() {
        return "default";
    }

    @Override
    public String getGroup() {
        return "default";
    }

    @Override
    public void processStart() throws CannotStartMachineException {

    }

    @Override
    public void processStop() throws CannotStopMachineException {

    }
}
