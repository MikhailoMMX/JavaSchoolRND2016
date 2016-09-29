package ru.microbyte.command;

public class Client {
    public static void main(String[] args) {
        Computer c = new Computer();

        Command start = new StartCommand(c);
        Command stop = new StopCommand(c);
        Command reset = new ResetCommand(c);

        User user = new User(start, stop, reset);
        user.startComputer();
        user.stopComputer();
        user.resetComputer();

    }
}

class Computer {
    public void start() {
        System.out.println("start");
    }

    public void  stop() {
        System.out.println("stop");
    }

    public void  reset() {
        System.out.println("reset");
    }

}

interface Command {
    void execute();
}

class StartCommand implements Command {
    Computer computer;

    public StartCommand(Computer computer) {
        this.computer = computer;
    }

    @Override
    public void execute() {
        computer.start();
    }
}

class StopCommand implements Command {
    Computer computer;

    public StopCommand(Computer computer) {
        this.computer = computer;
    }

    @Override
    public void execute() {
        computer.stop();
    }
}

class ResetCommand implements Command {
    Computer computer;

    public ResetCommand(Computer computer) {
        this.computer = computer;
    }

    @Override
    public void execute() {
        computer.reset();
    }
}

class User {

    Command start;
    Command stop;
    Command reset;

    public User(Command start, Command stop, Command reset) {
        this.start = start;
        this.stop = stop;
        this.reset = reset;
    }

    public void startComputer() {
        start.execute();
    }

    public void stopComputer() {
        stop.execute();
    }

    public void resetComputer() {
        reset.execute();
    }
}