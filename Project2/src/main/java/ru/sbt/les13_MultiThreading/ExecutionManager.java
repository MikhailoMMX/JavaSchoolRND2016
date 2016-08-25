package ru.sbt.les13_MultiThreading;

public interface ExecutionManager {
    Context execute(Runnable callback, Runnable[] tasks);
    void shutdown();
}
