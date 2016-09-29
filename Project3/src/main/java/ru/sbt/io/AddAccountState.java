package ru.sbt.io;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Состояние обрабатывает команду добавления счета
 */
class AddAccountState implements State {
    @Override
    public void readCommand(Context context) {
        try{
            //TODO create account class
            //TODO extract DB interaction
            //TODO generate account number
            System.out.print("Enter account number: ");
            String accNum = context.getScanner().nextLine();

            System.out.print("Enter account id: ");
            long id = context.getScanner().nextLong();
            context.getScanner().nextLine();
            PreparedStatement statement = context.getConnection().prepareStatement("insert into accounts (SALDO, ACC_NUM, CLIENT_ID) values (0, ?, ?)");
            statement.setString(1, accNum);
            statement.setLong(2, id);
            statement.execute();
            System.out.println("Account " + accNum + " added for client " + id);
        } catch (SQLException e) {
            Logger.getGlobal().log(Level.SEVERE, "SQLException:",  e);
        }
    }

    @Override
    public String stateName() {
        return "add account";
    }
}
