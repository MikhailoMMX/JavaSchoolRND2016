package ru.sbt.les14_MultiThreading;

public interface Context {
    int getCompletedTaskCount();

    int getFailedTaskCount();

    int getInterruptedTaskCount();

    void interrupt();

    boolean isFinished();
}
