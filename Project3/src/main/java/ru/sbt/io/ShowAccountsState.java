package ru.sbt.io;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Состояние обрабатывает команду отобрашения счетов
 */
class ShowAccountsState implements State {
    @Override
    public void readCommand(Context context) {
        try{
            PreparedStatement statement = context.getConnection().prepareStatement("select * from accounts");
            ResultSet resultSet = statement.executeQuery();
            System.out.println("Accounts:");
            ResultSetPrinter.printResultSet(resultSet);
        } catch (SQLException e) {
            Logger.getGlobal().log(Level.SEVERE, "SQLException:",  e);
        }
    }

    @Override
    public String stateName() {
        return "show accounts";
    }
}
