package ru.sbt.les18_DesignPatterns.Examples.Observer;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        MeteoStation meteo = new MeteoStation();
        Observer obs = new ConsoleObserver();
        meteo.addObserver(obs);

        meteo.setData(10,760);
    }
}

interface Observable{
    void notifyObservers(int temperature, int pressure);
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
}

interface Observer {
    void onChange(int temperature, int pressure);
}

class MeteoStation implements Observable{
    int temperature;
    int pressure;

    void setData(int temperature, int pressure){
        this.temperature = temperature;
        this.pressure = pressure;
        notifyObservers(temperature, pressure);
    }

    List<Observer> observers = new ArrayList<>();

    @Override
    public void notifyObservers(int temperature, int pressure) {
        for (Observer obs : observers)
            obs.onChange(temperature, pressure);
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
}

class ConsoleObserver implements Observer{

    @Override
    public void onChange(int temperature, int pressure) {
        System.out.println("Temp: " + temperature + "; pressure: " + pressure);
    }
}