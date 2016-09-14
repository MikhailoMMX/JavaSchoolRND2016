package ru.sbt.les18_DesignPatterns.HomeWork_2;

public class StateOn implements State{
    private final int INITIAL_COUNTER = 50;
    private int delayCounter = INITIAL_COUNTER;

    LightController lightController;

    public StateOn(LightController lightController) {
        this.lightController = lightController;
    }

    @Override
    public void doCycle(LightContext context, boolean sensorTriggered) {
        if (sensorTriggered)
            delayCounter = INITIAL_COUNTER;
        else {
            delayCounter -= 1;
            if (delayCounter < 0) {
                context.switchToTurningOff();
                delayCounter = INITIAL_COUNTER;
            }
        }
    }
}
