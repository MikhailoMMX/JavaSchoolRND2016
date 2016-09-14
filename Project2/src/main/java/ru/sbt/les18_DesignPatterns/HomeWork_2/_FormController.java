package ru.sbt.les18_DesignPatterns.HomeWork_2;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import ru.sbt.les18_DesignPatterns.HomeWork.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

public class _FormController implements Initializable {

    @FXML Button trigger;
    @FXML ProgressBar progressBar;

    LightController lightController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LightController lightController = new LightController(new CommandJFX(this), new SensorJFX(this));
        Thread thread = new Thread(lightController);
        thread.start();
        this.lightController = lightController;
    }

    @FXML
    private void handleButtonAction() {
        isButtonClicked = true;
    }
    public void SetLevel(double level){
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                progressBar.setProgress(level);
            }
        });
    }
    private boolean isButtonClicked;

    public boolean isButtonClicked() {
        boolean result = isButtonClicked;
        isButtonClicked = false;
        return result;
    }
}
