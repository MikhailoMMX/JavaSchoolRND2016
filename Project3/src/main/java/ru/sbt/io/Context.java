package ru.sbt.io;

import javafx.scene.media.VideoTrack;

import java.sql.Connection;
import java.util.*;

/**
 * Контекст для состояний, обраватывающих команды пользователя
 */
public class Context {
    private static final String EXIT = "exit";
    private Scanner scanner;
    private Connection connection;
    public Map<String, State> states;

    public Context() {
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setStates(Set<State> statesSet){
        states = new LinkedHashMap<>();
        for (State state : statesSet){
            states.put(state.stateName(), state);
        }
    }

    Scanner getScanner() {
        return scanner;
    }

    Connection getConnection() {
        return connection;
    }

    public void printStates() {
        for (String stateName : states.keySet())
            System.out.println("\t"+stateName);
        System.out.println("\t" + EXIT);
    }

    public void readCommands() {
        while(true) {
            System.out.println(">");
            String command = scanner.nextLine().toLowerCase();
            if (EXIT.equals(command))
                break;
            command = command.trim().toLowerCase();
            if (states.containsKey(command))
                states.get(command).readCommand(this);
        }
    }
}
