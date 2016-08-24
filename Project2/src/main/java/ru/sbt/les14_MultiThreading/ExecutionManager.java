package ru.sbt.les14_MultiThreading;

public interface ExecutionManager {
    Context execute(Runnable callback, Runnable[] tasks);
    void shutdown();
}
