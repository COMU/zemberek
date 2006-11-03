/*
 * Created on 13.Mar.2004
 */
package net.zemberek.araclar;

import junit.framework.TestCase;

/**
 * @author MDA & GBA
 */
public class TestTimeTracker extends TestCase {

    public void testStartClock() {
        TimeTracker.startClock("x");
        sleepMe(300);
        long time = TimeTracker.getElapsedTime("x");
        assertTrue(time >= 300 && time < 400);
        System.out.println(TimeTracker.stopClock("x"));
    }

    public void testDelta() {
        TimeTracker.startClock("x");
        sleepMe(300);
        long time = TimeTracker.getElapsedTime("x");
        assertTrue(time >= 300 && time < 400);
        sleepMe(300);
        long delta = TimeTracker.getTimeDelta("x");
        time = TimeTracker.getElapsedTime("x");
        assertTrue(time >= 600 && time < 700);
        assertTrue(delta >= 300 && delta < 400);
        System.out.println(TimeTracker.stopClock("x"));
    }


    private void sleepMe(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
