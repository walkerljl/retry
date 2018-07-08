package org.walkerljl.retry.standard.resource.abstracts;

import java.util.concurrent.atomic.AtomicInteger;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.walkerljl.retry.BaseUnitTest;
import org.walkerljl.retry.standard.resource.Resource;
import org.walkerljl.retry.standard.resource.ResourceRepository;
import org.walkerljl.retry.standard.resource.exception.CannotDestroyResourceException;
import org.walkerljl.retry.standard.resource.exception.CannotInitResourceException;

/**
 * AbstractResourceTest
 *
 * @author xingxun
 * @Date 2016/1/3
 */
public class AbstractResourceTest extends BaseUnitTest {

    @Test
    public void test() {

        Resource resource = new DefaultResource();

        Assert.assertEquals(resource.getId(), "default");
        Assert.assertEquals(resource.getName(), "default");
        Assert.assertEquals(resource.getGroup(), "default");

        String expectedInstanceId = resource.toString();
        Assert.assertEquals(resource.getInstanceId(), expectedInstanceId);

        resource.init();
        resource.destroy();
    }

    @Test
    public void destroy() {

        final AtomicInteger actualCounter = new AtomicInteger(0);
        Resource expected = new AbstractResource() {
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
        expected.init();
        expected.destroy();
        Assert.assertEquals(actualCounter.get(), 1);
        expected.destroy();
        Assert.assertEquals(actualCounter.get(), 1);

        boolean actualFlag = false;
        expected = new AbstractResource() {
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

        expected.init();
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
        Resource expected = new AbstractResource() {
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
        expected.init();
        Assert.assertEquals(actualCounter.get(), 1);
        Resource actual = ResourceRepository.lookup("default", "default");
        Assert.assertEquals(actual, expected);
        expected.destroy();
        actual = ResourceRepository.lookup("default", "default");
        Assert.assertNull(actual);

        boolean actualFlag = false;
        expected = new AbstractResource() {
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
}

class DefaultResource extends AbstractResource implements Resource {

    @Override
    public String getId() {
        return "default";
    }

    @Override
    public String getGroup() {
        return "default";
    }

    @Override
    public void processInit() throws CannotInitResourceException {

    }

    @Override
    public void processDestroy() throws CannotDestroyResourceException {

    }
}