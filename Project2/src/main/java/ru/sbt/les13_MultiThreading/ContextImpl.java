package ru.sbt.les13_MultiThreading;

import java.util.concurrent.Future;

public class ContextImpl implements Context{
    private Future[] futures;
    private MyRunnable[] runnables;
    public ContextImpl(Future[] futures, MyRunnable[] runnables) {
        this.futures = futures;
        this.runnables = runnables;
    }

    @Override
    public int getCompletedTaskCount() {
        int result = 0;
        for (int i = 0; i < futures.length; i++) {
            if (!futures[i].isDone() || runnables[i].isCancelled())
                continue;
            try {
                futures[i].get();
                result += 1;
            } catch (Exception e) {
                //nop
            }
        }
        return result;
    }

    @Override
    public int getFailedTaskCount() {
        int result = 0;
        for (int i = 0; i < futures.length; i++) {
            if (!futures[i].isDone())
                continue;
            try {
                futures[i].get();
            } catch (Exception e) {
                result += 1;
            }
        }
        return result;
    }

    @Override
    public int getInterruptedTaskCount() {
        int result = 0;
        for (int i = 0; i < runnables.length; i++) {
            if (runnables[i].isCancelled())
                result +=1;
        }
        return result;
    }

    @Override
    public void interrupt() {
        for (MyRunnable r : runnables)
            r.cancel();
    }

    @Override
    public boolean isFinished() {
        for (int i = 0; i < futures.length; i++) {
            if (!futures[i].isDone())
                return false;
        }
        return true;
    }
}
