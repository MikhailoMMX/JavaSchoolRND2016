package ru.sbt.les18_DesignPatterns.HomeWork;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static final String TITLE = "Decorator Example";

    static Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle(TITLE);
        primaryStage.setScene(new Scene(root, 427, 640));
        primaryStage.show();
    }

    public static void setTitle(String title){
        if (primaryStage != null) {
            primaryStage.setTitle(TITLE + ": " + title);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
