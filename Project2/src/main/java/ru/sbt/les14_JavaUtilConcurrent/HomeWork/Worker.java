package ru.sbt.les14_JavaUtilConcurrent.HomeWork;

import java.util.concurrent.Callable;

public class Worker implements Callable<Integer> {
    private String str1;
    private String str2;
    IDistance distanceImpl;
    public Worker(String str1, String str2, IDistance distanceImpl) {
        this.str1 = str1;
        this.str2 = str2;
        this.distanceImpl = distanceImpl;
    }

    @Override
    public Integer call() throws Exception {
        return distanceImpl.CalculateDistance(str1, str2);
    }

}
