package ru.sbt.io;

import javafx.scene.media.VideoTrack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sbt.dao.AccountRepository;
import ru.sbt.dao.ClientRepository;
import ru.sbt.dao.DocumentRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.*;

/**
 * Контекст для состояний, обраватывающих команды пользователя
 */
public class Context {
    private static final String EXIT = "exit";
    private Scanner scanner;
    //private Connection connection;
    private DataSource dataSource;
    public Map<String, State> states;

    private ClientRepository clientRepository;
    private AccountRepository accountRepository;
    private DocumentRepository documentRepository;

    public void setClientRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientRepository getClientRepository() {
        return clientRepository;
    }

    public AccountRepository getAccountRepository() {
        return accountRepository;
    }

    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public DocumentRepository getDocumentRepository() {
        return documentRepository;
    }

    public void setDocumentRepository(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public Context() {
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
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

    public DataSource getDataSource() {
        return dataSource;
    }

    public void printStates() {
        for (String stateName : states.keySet())
            System.out.println("\t"+stateName);
        System.out.println("\t" + EXIT);
    }

    public void readCommands() {
        while(true) {
            System.out.print(">");
            String command = scanner.nextLine().toLowerCase();
            if (EXIT.equals(command))
                break;
            command = command.trim().toLowerCase();
            if (states.containsKey(command))
                states.get(command).readCommand(this);
        }
    }
}
