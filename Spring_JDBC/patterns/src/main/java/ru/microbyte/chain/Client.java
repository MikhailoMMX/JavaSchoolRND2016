package ru.microbyte.chain;

public class  Client {
    public static void main(String[] args) {
        SMSLogger logger0 = new SMSLogger(Level.ERROR);
        FILELogger logger1 = new FILELogger(Level.DEBUG);
        logger1.setNext(logger0);


        logger1.writeMessage(Level.DEBUG, "debug");
        logger1.writeMessage(Level.INFO, "info");
        logger1.writeMessage(Level.ERROR, "error");
    }
}


class Level {
    static public int DEBUG = 1;
    static public int INFO = 2;
    static public int ERROR = 3;
}

interface Logger {
    void writeMessage(int Priority, String message);
}

class SMSLogger implements Logger {

    int priority;
    Logger next;

    public SMSLogger(int priority) {
        this.priority = priority;
    }

    @Override
    public void writeMessage(int priority, String message) {
        if (priority >= this.priority) {
            System.out.println("SMS: " + message);
        }
        if (next != null) {
            next.writeMessage(priority, message);
        }
    }

    public void setNext(Logger next) {
        this.next = next;
    }
}

class FILELogger implements Logger {

    int priority;
    Logger next;

    public FILELogger(int priority) {
        this.priority = priority;
    }

    @Override
    public void writeMessage(int priority, String message) {
        if (priority >= this.priority) {
            System.out.println("FILE: " + message);
        }
        if (next != null) {
            next.writeMessage(priority, message);
        }
    }

    public void setNext(Logger next) {
        this.next = next;
    }
}