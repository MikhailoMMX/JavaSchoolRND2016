package ru.sbt.les18_DesignPatterns.HomeWork_2;

public class LightController implements Runnable{
    private LightContext lightContext;
    private CommandLight lightLevelCommand;
    private Sensor sensor;
    public LightController(CommandLight lightLevelCommand, Sensor sensor) {
        this.lightLevelCommand = lightLevelCommand;
        this.sensor = sensor;
        lightContext = new LightContext(this);
    }

    private double lightLevel;

    public double getLightLevel() {
        return lightLevel;
    }

    public void setLightLevel(double lightLevel) {
        this.lightLevel = lightLevel;
        lightLevelCommand.setLevel(lightLevel);
    }

    private void start(){
        setLightLevel(0);
    }
    private void loop(){
        lightContext.doCycle(sensor.isSensorTriggered());
    }

    @Override
    public void run() {
        start();
        while (true) {
            loop();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
