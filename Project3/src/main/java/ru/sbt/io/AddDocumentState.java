package ru.sbt.io;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Состояние обрабатывает команду добавления документа
 */
class AddDocumentState implements State {
    @Override
    public void readCommand(Context context) {
        try{
            //TODO create document class
            //TODO extract DB interaction
            System.out.print("Enter debit account number: ");
            long accDT = context.getScanner().nextLong();
            System.out.print("Enter credit account number: ");
            long accCT = context.getScanner().nextLong();
            System.out.print("Enter summa: ");
            BigDecimal summa = context.getScanner().nextBigDecimal();
            context.getScanner().nextLine();
            System.out.print("Enter purpose: ");
            String purpose = context.getScanner().nextLine();
            boolean autoCommit = context.getConnection().getAutoCommit();
            context.getConnection().setAutoCommit(false);
            PreparedStatement statement = context.getConnection().prepareStatement("insert into documents (ACC_DT, ACC_CT, SUMMA, PURPOSE, DOC_DATE) values (?, ?, ?, ?, ?)");
            statement.setLong(1, accDT);
            statement.setLong(2, accCT);
            statement.setBigDecimal(3, summa);
            statement.setString(4, purpose);
            statement.setDate(5, new java.sql.Date(new Date().getTime()));
            statement.execute();

            BigDecimal dtSaldo = getSaldo(context.getConnection(), accDT);
            BigDecimal ctSaldo = getSaldo(context.getConnection(), accCT);
            dtSaldo = dtSaldo.add(summa);
            ctSaldo = ctSaldo.subtract(summa);
            setSaldo(context.getConnection(), accDT, dtSaldo);
            setSaldo(context.getConnection(), accCT, ctSaldo);

            context.getConnection().commit();
            context.getConnection().setAutoCommit(autoCommit);
            System.out.println("Document added");
        } catch (SQLException e) {
            Logger.getGlobal().log(Level.SEVERE, "SQLException:",  e);
        }
    }

    private BigDecimal getSaldo(Connection connection, long id) throws SQLException {
        PreparedStatement getSaldoStatement = connection.prepareStatement("select saldo from accounts where id = ?");
        getSaldoStatement.setLong(1, id);
        ResultSet resultSet = getSaldoStatement.executeQuery();
        resultSet.absolute(1);
        return resultSet.getBigDecimal(1);
    }

    private void setSaldo(Connection connection, long id, BigDecimal newSaldo) throws SQLException {
        PreparedStatement setSaldoStatement = connection.prepareStatement("update accounts set saldo = ? where id = ?");
        setSaldoStatement.setBigDecimal(1, newSaldo);
        setSaldoStatement.setLong(2, id);
        setSaldoStatement.execute();
    }

    @Override
    public String stateName() {
        return "add document";
    }
}
