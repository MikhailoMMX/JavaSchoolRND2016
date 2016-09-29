package ru.sbt.io;

import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * Состояние обрабатывает команду добавления клиента
 */
class AddClientState implements State {
    @Override
    public void readCommand(Context context) {
        //TODO create client class
        //TODO extract DB interaction
        try {
            System.out.print("Enter name: ");
            String name = context.getScanner().nextLine();
            PreparedStatement statement = context.getConnection().prepareStatement("insert into clients (NAME) values (?)");
            statement.setString(1, name);
            statement.execute();
            System.out.println("Client " + name + " added");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String stateName() {
        return "add client";
    }
}
