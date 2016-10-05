package ru.sbt.io;

import ru.sbt.data.Client;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Состояние обрабатывает команду отображения клиентов
 */
class ShowClientsState implements State {
    @Override
    public void readCommand(Context context) {
        Iterable<Client> all = context.getClientRepository().findAll();
        System.out.println("Clients:");
        for(Client client:all){
            System.out.println("[id="+client.getId() + ", name=" + client.getName()+"]");
        }
    }

    @Override
    public String stateName() {
        return "show clients";
    }
}
