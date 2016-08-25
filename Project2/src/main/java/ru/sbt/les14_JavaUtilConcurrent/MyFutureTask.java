package ru.sbt.les14_JavaUtilConcurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class MyFutureTask extends FutureTask {
    public MyFutureTask(Callable callable) {
        super(callable);
    }

    @Override
    public void run() {
        super.run();
        System.out.println("Post method");
    }
}
