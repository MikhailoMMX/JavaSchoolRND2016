package ru.sbt.io;

import java.sql.Connection;
import java.util.*;

/**
 * Контекст для состояний, обраватывающих команды пользователя
 */
public class Context {
    private Scanner scanner;
    private Connection connection;
    private Map<String, State> states = new LinkedHashMap<>();

    public Context(Scanner scanner, Connection connection) {
        this.scanner = scanner;
        this.connection = connection;

        List<State> statesList = new ArrayList<>();
        statesList.add(new AddClientState());
        statesList.add(new AddAccountState());
        statesList.add(new AddDocumentState());
        statesList.add(new ShowClientsState());
        statesList.add(new ShowAccountsState());
        statesList.add(new ShowDocumentsState());

        for (State state : statesList){
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
    }

    public void readCommand(String command) {
        command = command.trim().toLowerCase();
        if (states.containsKey(command))
            states.get(command).readCommand(this);
    }
}
