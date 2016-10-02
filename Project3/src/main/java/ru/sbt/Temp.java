package ru.sbt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Temp {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection( "jdbc:h2:tcp://localhost/~/test", "sa", "");
    }

    public static Scanner getScanner(){
        return new Scanner(System.in);
    }
}
