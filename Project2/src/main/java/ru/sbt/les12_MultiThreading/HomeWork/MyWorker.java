package ru.sbt.les12_MultiThreading.HomeWork;

import javafx.util.Pair;
import java.util.concurrent.Callable;

//Основной Runnable-класс, работающий в потоке
//Получает у пула потоков очередную задачу, решает ее и ждет следующую
//Основная идея - переиспользование одного потока, вместо постоянного порождения новых

public class MyWorker implements Runnable {
    private volatile boolean interrupting = false;
    private MyThreadPool pool;

    public MyWorker(MyThreadPool pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        while(true){
            if (interrupting)
                return;
            Pair<Callable, MyFuture> pair = pool.nextTask();
            if (pair == null)
                continue;
            try {
                Object result = pair.getKey().call();
                pair.getValue().set(result);
            } catch (RuntimeException re) {
                pair.getValue().setException(re);
            } catch (Exception e) {
                pair.getValue().setException(new RuntimeException(e));
            }
        }
    }

    public void interrupt(){
        interrupting = true;
    }

    public boolean isInterrupting() {
        return interrupting;
    }
}
