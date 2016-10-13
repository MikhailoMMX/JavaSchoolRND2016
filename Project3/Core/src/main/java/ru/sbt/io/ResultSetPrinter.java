package ru.sbt.io;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Вспомогательный класс, выводящий на экран содержимое таблицы
 */
class ResultSetPrinter {
    static void printResultSet(ResultSet resultSet){
        try {
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int count = rsmd.getColumnCount();
            for (int i = 1; i <= count; i++) {
                System.out.print(rsmd.getColumnName(i));
                if (i != count)
                    System.out.print(", ");
            }
            System.out.println("");
            while(resultSet.next()){
                for (int i = 1; i <= count; i++) {
                    System.out.print(resultSet.getObject(i));
                    if (i != count)
                        System.out.print(", ");
                }
                System.out.println("");
            }
        } catch (SQLException e) {
            Logger.getGlobal().log(Level.SEVERE, "SQLException", e);
        }
    }
}
