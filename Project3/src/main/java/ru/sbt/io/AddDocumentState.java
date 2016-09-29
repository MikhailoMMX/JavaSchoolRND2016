package ru.sbt.io;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
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
            //TODO change account saldo
            System.out.print("Enter debit account number: ");
            long accDT = context.getScanner().nextLong();
            System.out.print("Enter credit account number: ");
            long accCT = context.getScanner().nextLong();
            System.out.print("Enter summa: ");
            BigDecimal summa = context.getScanner().nextBigDecimal();
            context.getScanner().nextLine();
            System.out.print("Enter purpose: ");
            String purpose = context.getScanner().nextLine();
            PreparedStatement statement = context.getConnection().prepareStatement("insert into documents (ACC_DT, ACC_CT, SUMMA, PURPOSE, DOC_DATE) values (?, ?, ?, ?, ?)");
            statement.setLong(1, accDT);
            statement.setLong(2, accCT);
            statement.setBigDecimal(3, summa);
            statement.setString(4, purpose);
            statement.setDate(5, new java.sql.Date(new Date().getTime()));
            statement.execute();
            System.out.println("Document added");
        } catch (SQLException e) {
            Logger.getGlobal().log(Level.SEVERE, "SQLException:",  e);
        }
    }

    @Override
    public String stateName() {
        return "add document";
    }
}
