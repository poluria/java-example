package com.poluria.example.lang;

import org.junit.Test;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

    @Test
    public void schedule() throws InterruptedException {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("aaa");
            }
        }, 0, 1000);
        Thread.sleep(5000);
    }

    @Test
    public void timer() throws InterruptedException {
        Timer timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
            }
        };

        System.out.println(timerTask.scheduledExecutionTime());

        timer.schedule(timerTask, 1000);

        System.out.println(timerTask.scheduledExecutionTime());
    }

}
