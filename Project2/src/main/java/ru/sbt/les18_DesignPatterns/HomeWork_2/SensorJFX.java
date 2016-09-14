package ru.sbt.les18_DesignPatterns.HomeWork_2;

public class SensorJFX implements Sensor{
    _FormController formController;

    public SensorJFX(_FormController formController) {
        this.formController = formController;
    }

    @Override
    public boolean isSensorTriggered() {
        return formController.isButtonClicked();
    }
}
