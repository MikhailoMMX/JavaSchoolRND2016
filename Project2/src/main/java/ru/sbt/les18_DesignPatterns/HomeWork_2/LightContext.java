package ru.sbt.les18_DesignPatterns.HomeWork_2;

public class LightContext {
    private LightController lightController;

    private State currentState;

    private State stateOff;
    private State stateOn;
    private State stateTurningOn;
    private State stateTurningOff;

    public LightContext(LightController lightController) {
        this.lightController = lightController;
        stateOff = new StateOff(lightController);
        stateOn = new StateOn(lightController);
        stateTurningOn = new StateTurningOn(lightController);
        //stateTurningOn = new StateTurnOnInstantly(lightController);     //для мгновенного включения - вместо предыдущей строки
        stateTurningOff = new StateTurningOff(lightController);
        switchToOff();
    }

    public void switchToOn() {
        currentState = stateOn;
    }
    public void switchToOff() {
        currentState = stateOff;
    }
    public void switchToTurningOn() {
        currentState = stateTurningOn;
    }
    public void switchToTurningOff() {
        currentState = stateTurningOff;
    }

    public void doCycle(boolean SensorTriggered){
        currentState.doCycle(this, SensorTriggered);
    }
}
