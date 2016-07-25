package ru.sbertech.lesson5;

class Engine{
    void startEngine(){
        System.out.println("started");
    }
}
class Vehicle{
    private Engine engine;

    public void setEngine(Engine engine) {
        this.engine = engine;
    }
    public void StartEngine(){
        engine.startEngine();
    }
}
public class NullPointerExceptionTest {
    public static void main(String[] args) {
        Engine engine = new Engine();

        Vehicle car = new Vehicle();
        car.setEngine(engine);
        car.StartEngine();

        Vehicle bike = new Vehicle();
        bike.StartEngine();

        System.out.println("All started");
    }
}
