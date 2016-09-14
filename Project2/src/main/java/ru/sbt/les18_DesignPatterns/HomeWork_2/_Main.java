package ru.sbt.les18_DesignPatterns.HomeWork_2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
    Идея - смоделировать освещение, включающееся по сенсору так, как оно было бы реализовано на Arduino.
    В контроллере Arduino есть два метода:
        start() - инициализация
        loop() - работает в цикле
    Подсветка работает в 4 режимах - выключено, включается, включено, выключается.
    Включение и выключение - плавные, занимают несколько тактов.
 */

public class _Main extends Application {
    private static final String TITLE = "Patterns Example";

    static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("_Form.fxml"));
        primaryStage.setTitle(TITLE);
        primaryStage.setScene(new Scene(root, 300, 300));
        primaryStage.show();
    }
}
