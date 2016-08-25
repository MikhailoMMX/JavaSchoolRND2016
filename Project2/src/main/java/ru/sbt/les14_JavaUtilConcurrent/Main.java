package ru.sbt.les14_JavaUtilConcurrent;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        Test4();
    }

    private static void Test1() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Runnable task = new Task();
        System.out.println("Main thread: \"" + Thread.currentThread().getName()+"\"");
        executor.submit(task);
        executor.shutdown();
    }

    private static void Test2() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        Runnable task = new Task();
        System.out.println("Main thread: \"" + Thread.currentThread().getName()+"\"");
        executor.scheduleAtFixedRate(task, 0, 4, TimeUnit.SECONDS);
        //executor.shutdown();
    }

    private static void Test3() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Callable<String> task = new TaskCallable();
        System.out.println("Main thread: \"" + Thread.currentThread().getName()+"\"");
        MyFutureTask ft = new MyFutureTask(task);
        executor.execute(ft);
        try {
            if (!ft.isDone())
                System.out.println("Working thread was: \"" + ft.get()+"\"");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }

    private static void Test4() {
        int SIZE = 3;
        CountDownLatch startGate = new CountDownLatch(1);
        CountDownLatch endGate = new CountDownLatch(SIZE);
        for (int i = 0; i < SIZE; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Entering thread " + Thread.currentThread().getName());
                    try {
                        startGate.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Started work in " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(ThreadLocalRandom.current().nextInt(5000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Finished work in " + Thread.currentThread().getName());
                    endGate.countDown();
                }
            }).start();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("== CountDown ==");
        startGate.countDown();
        try {
            endGate.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("== Finished ==");
        //Latch - защелка. Одноразовая, с ее помощью можно дождаться завершения инициализации чего-нибудь и
        //подержать потоки до того момента, когда счетчик достигнет нуля
    }
}
