package ru.sbt.les18_DesignPatterns.HomeWork_2;

public class StateTurningOn implements State{
    LightController lightController;

    public StateTurningOn(LightController lightController) {
        this.lightController = lightController;
    }

    @Override
    public void doCycle(LightContext context, boolean sensorTriggered) {
        double newLevel = lightController.getLightLevel() + 0.1f;
        newLevel = Math.min(1, newLevel);
        lightController.setLightLevel(newLevel);
        if (newLevel >= 1)
            context.switchToOn();
    }
}
