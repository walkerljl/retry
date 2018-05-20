package org.walkerljl.retry.standard.resource.abstracts;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.walkerljl.retry.standard.resource.Resource;
import org.walkerljl.retry.standard.resource.exception.CannotDestroyResourceException;
import org.walkerljl.retry.standard.resource.exception.CannotInitResourceException;

/**
 * AbstractResourceTest
 *
 * @author xingxun
 * @Date 2016/1/3
 */
public class AbstractResourceTest {

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