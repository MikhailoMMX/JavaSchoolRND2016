package ru.sbt;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.sbt.io.Context;

import java.sql.*;
import java.util.Scanner;

/**
 * Главный класс приложения
 */
public class Main {
    private static final String URL = "jdbc:h2:tcp://localhost/~/test";

    public static void main(String[] args) {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("context.xml");
        Context stateContext = appContext.getBean(Context.class);

        try {
            System.out.println("Commands:");
            stateContext.printStates();
            stateContext.readCommands();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //TODO report
//    private static void report(Scanner scanner) {
//        try(Connection connection = DriverManager.getConnection(URL, "sa", "")){
//            System.out.print("Enter client ID: ");
//            long id = scanner.nextLong();
//            PreparedStatement statement = connection.prepareStatement("select * from documents where ACC_DT = ? or ACC_CT = ?");
//            statement.setLong(1, id);
//            statement.setLong(2, id);
//            ResultSet resultSet = statement.executeQuery();
//            printReport(resultSet);
//        } catch (SQLException e) {
//            Logger.getGlobal().log(Level.SEVERE, "SQLException:",  e);
//        }
//    }

}
