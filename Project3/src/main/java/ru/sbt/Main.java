package ru.sbt;

import ru.sbt.io.Context;

import java.sql.*;
import java.util.Scanner;

/**
 * Главный класс приложения
 */
public class Main {
    private static final String URL = "jdbc:h2:tcp://localhost/~/test";
    private static final String EXIT = "exit";

    public static void main(String[] args) {
        try (
                Scanner scanner = new Scanner(System.in);
                Connection connection = DriverManager.getConnection(URL, "sa", "")
        ) {
            Context stateContext = new Context(scanner, connection);

            System.out.println("Commands:");
            stateContext.printStates();
            System.out.println("\t" + EXIT);

            while (true) {
                System.out.print(">");
                String command = scanner.nextLine().toLowerCase();
                if (EXIT.equals(command))
                    break;

                stateContext.readCommand(command);
            }
        } catch (SQLException e) {
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
