package ru.sbt.io;

import org.h2.command.dml.Select;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

            System.out.print("Enter client name: ");
            String name = context.getScanner().nextLine();
            long id = getIdByName(context.getConnection(), name);

            PreparedStatement statement = context.getConnection().prepareStatement("insert into accounts (SALDO, ACC_NUM, CLIENT_ID) values (0, ?, ?)");
            statement.setString(1, accNum);
            statement.setLong(2, id);
            statement.execute();
            System.out.println("Account " + accNum + " added for client " + name);
        } catch (SQLException e) {
            Logger.getGlobal().log(Level.SEVERE, "SQLException:",  e);
        }
    }

    private long getIdByName(Connection connection, String name) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select id from clients where name = ?");
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.absolute(1);
        return resultSet.getLong(1);
    }

    @Override
    public String stateName() {
        return "add account";
    }
}
