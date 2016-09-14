package ru.sbt.les18_DesignPatterns.HomeWork_2;

public class StateTurnOnInstantly implements State {
    LightController lightController;

    public StateTurnOnInstantly(LightController lightController) {
        this.lightController = lightController;
    }

    @Override
    public void doCycle(LightContext context, boolean sensorTriggered) {
        lightController.setLightLevel(1.0f);
        context.switchToOn();
    }
}
