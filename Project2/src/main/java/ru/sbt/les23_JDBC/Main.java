package ru.sbt.les23_JDBC;

import java.sql.*;

public class Main {
    private static final String URL = "jdbc:h2:tcp://localhost/~/test";
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, "sa", "")){
//            System.out.println("TYPE_FORWARD_ONLY: " + conn.getMetaData().supportsResultSetType(ResultSet.TYPE_FORWARD_ONLY));
//            System.out.println("TYPE_SCROLL_INSENSITIVE: " + conn.getMetaData().supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE));
//            System.out.println("TYPE_SCROLL_SENSITIVE: " + conn.getMetaData().supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE));
//
//            System.out.println("CONCUR_READ_ONLY: " + conn.getMetaData().supportsResultSetConcurrency(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY));
//            System.out.println("CONCUR_UPDATABLE: " + conn.getMetaData().supportsResultSetConcurrency(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE));
//
//            System.out.println("CONCUR_UPDATABLE: " + conn.getMetaData().supportsResultSetHoldability(ResultSet.HOLD_CURSORS_OVER_COMMIT));

            log("Connection established");

            conn.setAutoCommit(false);
            Savepoint startSavePoint = conn.setSavepoint("Start");
            PreparedStatement st = conn.prepareStatement("insert into songs(NAME) values(?)");
            st.setString(1, "My song");
            st.execute();

            log("My song added");

            conn.rollback(startSavePoint);

            st.setString(1, "My song 2");
            st.execute();

            log("My song 2 added");

            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void log(String msg){
        Thread t = new Thread(new Runnable() {
            String str;
            public Runnable init(String msg){
                str = msg;
                return this;
            }
            @Override
            public void run() {
                try (Connection conn = DriverManager.getConnection(URL, "sa", "")) {
                    PreparedStatement st = conn.prepareStatement("insert into log(str) values(?)");
                    st.setString(1, str);
                    st.execute();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }.init(msg));
        t.start();
    }
}
