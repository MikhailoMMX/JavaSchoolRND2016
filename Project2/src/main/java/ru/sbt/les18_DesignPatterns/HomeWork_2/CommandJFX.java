package ru.sbt.les18_DesignPatterns.HomeWork_2;

public class CommandJFX implements CommandLight {
    private _FormController formController;

    public CommandJFX(_FormController formController) {
        this.formController = formController;
    }

    @Override
    public void setLevel(double level) {
        formController.SetLevel(level);
    }
}
