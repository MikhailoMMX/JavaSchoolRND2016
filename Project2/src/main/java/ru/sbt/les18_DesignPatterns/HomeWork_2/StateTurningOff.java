package ru.sbt.les18_DesignPatterns.HomeWork_2;

public class StateTurningOff implements State{
    LightController lightController;

    public StateTurningOff(LightController lightController) {
        this.lightController = lightController;
    }

    @Override
    public void doCycle(LightContext context, boolean sensorTriggered) {
        if (sensorTriggered)
            context.switchToTurningOn();
        else {
            double newLevel = lightController.getLightLevel() - 0.02f;
            newLevel = Math.max(0, newLevel);
            lightController.setLightLevel(newLevel);
            if (newLevel <= 0)
                context.switchToOff();
        }
    }
}
