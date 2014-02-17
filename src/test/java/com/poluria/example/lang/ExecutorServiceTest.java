package com.poluria.example.lang;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceTest {

    @Test
    public void submit() throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        List<Future<?>> futures = new ArrayList<Future<?>>();

        for (int i = 0; i < 10; i++) {
            Future<?> future = executor.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("aaa");
                    throw new RuntimeException();
                }
            });
            futures.add(future);
        }

        for (Future<?> future : futures) {
            Object o = future.get();
            System.out.println(o);
        }
    }

    @Test
    public void submit_result() throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        List<Future<Integer>> futures = new ArrayList<Future<Integer>>();

        for (int i = 0; i < 10; i++) {
            Future<Integer> future = executor.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("aaa");
                }
            }, i);
            futures.add(future);
        }

        for (Future<?> future : futures) {
            Object o = future.get();
            System.out.println(o);
        }
    }
}
