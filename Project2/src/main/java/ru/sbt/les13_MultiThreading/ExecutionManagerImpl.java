package ru.sbt.les13_MultiThreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutionManagerImpl implements ExecutionManager{
    private ExecutorService pool;

    public ExecutionManagerImpl() {
        pool = Executors.newFixedThreadPool(4);
    }

    public void shutdown(){
        pool.shutdown();
    }

    private Thread newCallBackThread(Future[] futures, Runnable callback){
        return new Thread(new Runnable() {
            Future[] futures;
            Runnable callback;
            @Override
            public void run() {
                for (Future f : futures)
                    try {
                        f.get();
                    } catch (Exception e) {
                        //nop
                    }
                callback.run();
            }
            public Runnable init(Future[] futures, Runnable callback){
                this.futures = futures;
                this.callback = callback;
                return this;
            }
        }.init(futures, callback));
    }

    @Override
    public Context execute(Runnable callback, Runnable[] tasks) {
        Future[] futures = new Future[tasks.length];
        MyRunnable[] runnables = new MyRunnable[tasks.length];
        for (int i = 0; i < tasks.length; i++) {
            runnables[i] = new MyRunnable(tasks[i]);
            futures[i] = pool.submit(runnables[i]);
        }
        Context result = new ContextImpl(futures, runnables);
        newCallBackThread(futures, callback).start();
        return result;
    }
}
