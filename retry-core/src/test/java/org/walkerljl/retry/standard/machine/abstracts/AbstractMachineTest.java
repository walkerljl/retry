package org.walkerljl.retry.standard.machine.abstracts;

import java.util.concurrent.atomic.AtomicInteger;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.walkerljl.retry.BaseUnitTest;
import org.walkerljl.retry.standard.machine.Machine;
import org.walkerljl.retry.standard.machine.exception.CannotStartMachineException;
import org.walkerljl.retry.standard.machine.exception.CannotStopMachineException;
import org.walkerljl.retry.standard.resource.Resource;
import org.walkerljl.retry.standard.resource.ResourceRepository;
import org.walkerljl.retry.standard.resource.exception.CannotDestroyResourceException;
import org.walkerljl.retry.standard.resource.exception.CannotInitResourceException;

/**
 * AbstractMachineTest
 *
 * @author xingxun
 * @Date 2016/1/3
 */
public class AbstractMachineTest extends BaseUnitTest {

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

    @Test
    public void destroy() {

        final AtomicInteger actualCounter = new AtomicInteger(0);
        Machine expected = new AbstractMachine() {
            @Override
            public void processStart() throws CannotStartMachineException {

            }

            @Override
            public void processStop() throws CannotStopMachineException {

            }

            @Override
            public void processInit() throws CannotInitResourceException {
            }

            @Override
            public void processDestroy() throws CannotDestroyResourceException {
                actualCounter.incrementAndGet();
            }

            @Override
            public String getId() {
                return "default";
            }

            @Override
            public String getGroup() {
                return "default";
            }
        };
        expected.destroy();
        Assert.assertEquals(actualCounter.get(), 1);

        boolean actualFlag = false;
        expected = new AbstractMachine() {
            @Override
            public void processStart() throws CannotStartMachineException {

            }

            @Override
            public void processStop() throws CannotStopMachineException {

            }

            @Override
            public void processInit() throws CannotInitResourceException {
            }

            @Override
            public void processDestroy() throws CannotDestroyResourceException {
                throw new RuntimeException();
            }

            @Override
            public String getId() {
                return "default";
            }

            @Override
            public String getGroup() {
                return "default";
            }
        };

        try {
            expected.destroy();
        } catch (CannotDestroyResourceException e) {
            actualFlag = true;
        }
        Assert.assertTrue(actualFlag);
    }

    @Test
    public void init() {

        final AtomicInteger actualCounter = new AtomicInteger(0);
        Machine expected = new AbstractMachine() {
            @Override
            public void processStart() throws CannotStartMachineException {

            }

            @Override
            public void processStop() throws CannotStopMachineException {

            }

            @Override
            public void processInit() throws CannotInitResourceException {
                actualCounter.incrementAndGet();
            }

            @Override
            public void processDestroy() throws CannotDestroyResourceException {

            }

            @Override
            public String getId() {
                return "default";
            }

            @Override
            public String getGroup() {
                return "default";
            }
        };
        expected.init();
        Assert.assertEquals(actualCounter.get(), 1);
        Assert.assertEquals(actualCounter.get(), 1);
        Resource actual = ResourceRepository.lookup("default", "default");
        Assert.assertEquals(actual.getGroup(), "default");
        Assert.assertEquals(actual.getId(), "default");
        expected.destroy();
        actual = ResourceRepository.lookup("default", "default");
        Assert.assertNull(actual);

        boolean actualFlag = false;
        expected = new AbstractMachine() {
            @Override
            public void processStart() throws CannotStartMachineException {

            }

            @Override
            public void processStop() throws CannotStopMachineException {

            }

            @Override
            public void processInit() throws CannotInitResourceException {
                throw new RuntimeException();
            }

            @Override
            public void processDestroy() throws CannotDestroyResourceException {
            }

            @Override
            public String getId() {
                return "default";
            }

            @Override
            public String getGroup() {
                return "default";
            }
        };

        try {
            expected.init();
        } catch (CannotInitResourceException e) {
            actualFlag = true;
        }
        Assert.assertTrue(actualFlag);
    }

    @Test
    public void start() {

    }

    @Test
    public void stop() {

    }

    @Test
    public void restart() {

    }

    @Test
    public void processStart() {

    }

    @Test
    public void processStop() {

    }

    @Test
    public void callbackOnSuccessToStart() {

    }

    @Test
    public void callbackOnFailureToStart() {

    }

    @Test
    public void callbackOnSuccessToStop() {

    }

    @Test
    public void callbackOnFailureToStop() {

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
