package ru.sbt.les18_DesignPatterns.HomeWork_2;

public interface State {
    void doCycle(LightContext context, boolean sensorTriggered);
}
