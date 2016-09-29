package ru.microbyte.observer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MeteoStation implements Observable{
    List<Observer> observables = new ArrayList<Observer>();

    int temperature;
    int pressure;


    void setData(int temperature, int pressure) {
        this.temperature = temperature;
        this.pressure = pressure;
        notifyObservers(temperature, pressure);
    }

    public void add(Observer o) {
        observables.add(o);
    }

    public void remove(Observer o) {
        observables.remove(o);
    }

    public void notifyObservers(int temperature, int pressure) {
        for (Observer observer: observables) {
            observer.onChange(temperature, pressure);
        }
    }

    public static void main(String[] args) {
        MeteoStation ms = new MeteoStation();

        ms.add(new FileObserver());
        ms.setData(30, 750);
        ms.setData(31, 755);
        ms.add(new ConsoleObserver());
        ms.setData(33, 760);
        ms.setData(34, 775);

    }
}


interface Observer {
    void onChange(int temperature, int pressure);
}


interface Observable {
    void add(Observer o);
    void remove(Observer o);
    void notifyObservers (int temperature, int pressure);
}

class ConsoleObserver implements Observer {
    public void onChange(int temperature, int pressure) {
        System.out.println("Temp = " + temperature + ", pressure: " + pressure);
    }
}

class FileObserver implements Observer {
    public void onChange(int temperature, int pressure) {
        try (PrintWriter writer = new PrintWriter(new File("c:/ms" + UUID.randomUUID() + ".dat"))) {
            writer.println("Temp = " + temperature + ", pressure: " + pressure);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}