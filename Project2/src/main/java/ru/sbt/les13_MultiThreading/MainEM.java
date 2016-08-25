package ru.sbt.les13_MultiThreading;

import java.util.concurrent.ThreadLocalRandom;

public class MainEM {
    private static Runnable newRunnable(){
        return new Runnable() {
            @Override
            public void run() {
                int sl = ThreadLocalRandom.current().nextInt(1000);
                boolean fail = ThreadLocalRandom.current().nextInt(10)>=5;
                System.out.println(" ↓  " + Thread.currentThread().getName() + " will" + (fail ? " fail after " : " be running ") + sl + " ms");
                try {
                    Thread.sleep(sl);
                    if (fail) {
                        System.out.println("  ↑ " + Thread.currentThread().getName() + " failed");
                        throw new RuntimeException("failed");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("  ↑ " + Thread.currentThread().getName() + " finished");
            }
        };
    }
    private static Runnable newCallback(){
        return new Runnable() {
            @Override
            public void run() {
                System.out.println("== Callback ==");
            }
        };
    }

    public static void main(String[] args) {
        final int sleepTime = 100;
        ExecutionManager manager = new ExecutionManagerImpl();
        int TASKS = 10;
        Runnable[] tasks = new Runnable[TASKS];
        for (int i = 0; i < TASKS; i++)
            tasks[i] = newRunnable();
        Context ctx = manager.execute(newCallback(), tasks);
        int time = 0;
        while (!ctx.isFinished()){
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time += sleepTime;
            if (time == sleepTime*7) {
                ctx.interrupt();
                System.out.println("== Interrupting ==");
            }
            System.out.println(time + "ms; Finished: " + ctx.getCompletedTaskCount() + "; failed: " + ctx.getFailedTaskCount()+"; interrupted: " + ctx.getInterruptedTaskCount());
        }
        manager.shutdown();
    }
}
