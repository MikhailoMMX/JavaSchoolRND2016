package ru.microbyte;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

class User {
    Integer id;
    String firstName, lastName;

    public User(Integer id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}

public class JdbcTest {

    static private String SQL_CREATE_TABLE =
                    "CREATE TABLE users (" +
                    "id INTEGER IDENTITY PRIMARY KEY," +
                    "first_name VARCHAR(30)," +
                    "last_name  VARCHAR(30));";

    static private Logger log = LoggerFactory.getLogger(JdbcTest.class);

    public static void main(String[] args) {
        Connection connection = null;
        //URL к базе состоит из протокола:подпротокола://[хоста]:[порта_СУБД]/[БД] и других_сведений

        String url = "jdbc:h2:mem:testdb";
        String name = "user";
        String password = "123456";
        try {
            //Загружаем драйвер
            Class.forName("org.h2.Driver");
            log.info("Драйвер подключен");
            //Создаём соединение
            connection = DriverManager.getConnection(url, name, password);
            log.info("Соединение установлено");
            
            Statement statement = connection.createStatement();
            statement.execute(SQL_CREATE_TABLE);

            //Для использования SQL запросов существуют 3 типа объектов:
            //1.Statement: используется для простых случаев без параметров

            statement = connection.createStatement();
            // Вставить запись
            statement.executeUpdate("INSERT INTO users(first_name, last_name) values('Иван', 'Иванов')");
            statement.executeUpdate("INSERT INTO users(first_name, last_name) values('Петя', 'Петров')");
            //Обновить запись
            statement.executeUpdate("UPDATE users SET first_name = 'Вася' where id = 1");
            //Выполним запрос
            ResultSet result1 = statement.executeQuery("SELECT * FROM users");
            //result это указатель на первую строку с выборки
            //чтобы вывести данные мы будем использовать
            //метод next() , с помощью которого переходим к следующему элементу
            log.info("Выводим statement");
            while (result1.next()) {
                log.info("Номер в выборке: {}\t first_name - {}\t last_name - {}",
                        result1.getRow(), result1.getString("first_name"), result1.getString("last_name"));
            }

            {
/*            //2.PreparedStatement: предварительно компилирует запросы,
            //которые могут содержать входные параметры
            PreparedStatement preparedStatement = null;
            // ? - место вставки нашего значеня
            preparedStatement = connection.prepareStatement("SELECT * FROM users where id > ? and id < ?");
            //Устанавливаем в нужную позицию значения определённого типа
            preparedStatement.setInt(1, 2);
            preparedStatement.setInt(2, 10);
            //выполняем запрос
            ResultSet result2 = preparedStatement.executeQuery();

            log.info("Выводим PreparedStatement");
            while (result2.next()) {
                log.info("Номер в выборке #" + result2.getRow()
                        + "\t Номер в базе #" + result2.getInt("id")
                        + "\t" + result2.getString("username"));
            }

            preparedStatement = connection.prepareStatement("INSERT INTO users(username) values(?)");
            preparedStatement.setString(1, "user_name");
            //метод принимает значение без параметров
            //темже способом можно сделать и UPDATE
            preparedStatement.executeUpdate();*/

/*            //3.CallableStatement: используется для вызова хранимых функций,
            // которые могут содержать входные и выходные параметры
            CallableStatement callableStatement = null;
            //Вызываем функцию myFunc (хранится в БД)
            callableStatement = connection.prepareCall(" { call myfunc(?,?) } ");
            //Задаём входные параметры
            callableStatement.setString(1, "Dima");
            callableStatement.setString(2, "Alex");
            //Выполняем запрос
            ResultSet result3 = callableStatement.executeQuery();
            //Если CallableStatement возвращает несколько объектов ResultSet,
            //то нужно выводить данные в цикле с помощью метода next
            //у меня функция возвращает один объект
            result3.next();
            log.info(result3.getString("MESSAGE"));
            //если функция вставляет или обновляет, то используется метод executeUpdate()*/
            }

        } catch (Exception ex) {
            //выводим наиболее значимые сообщения
            log.error("", ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    log.error("", ex);
                }
            }
        }

    }
}
