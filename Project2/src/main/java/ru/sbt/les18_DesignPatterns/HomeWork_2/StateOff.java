package ru.sbt.les18_DesignPatterns.HomeWork_2;

public class StateOff implements State{

    LightController lightController;

    public StateOff(LightController lightController) {
        this.lightController = lightController;
    }

    @Override
    public void doCycle(LightContext context, boolean sensorTriggered) {
        if (sensorTriggered)
            context.switchToTurningOn();
    }
}
