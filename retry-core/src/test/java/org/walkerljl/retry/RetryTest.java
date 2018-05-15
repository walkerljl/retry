package org.walkerljl.retry;

import java.util.Timer;
import java.util.TimerTask;

import org.testng.annotations.Test;

/**
 *
 * @author xingxun
 */
public class RetryTest extends BaseUnitTest {

    @Test
    public void test() throws InterruptedException {

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Timer.");
            }
        }, 1000, 1000);
    }
}