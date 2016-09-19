package ru.sbt.les21_JDBC;

import java.sql.*;

public class Main {
    private static final String URL = "jdbc:h2:tcp://localhost/~/test";
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, "sa", "")){
//            Statement statement = conn.createStatement();
//            ResultSet resultSet = statement.executeQuery("select * from songs");
//            while (resultSet.next()){
//                System.out.println("Album: " + resultSet.getString("NAME") + "; Time: " + resultSet.getBigDecimal("SONG_TIME"));
//            }

            PreparedStatement ps = conn.prepareStatement("select * from songs where id = ?");
            ps.setString(1, "25");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                System.out.println("Album: " + resultSet.getString("NAME") + "; Time: " + resultSet.getBigDecimal("SONG_TIME"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Ok");
    }
}
