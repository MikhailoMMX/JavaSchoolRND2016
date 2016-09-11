package ru.sbt.les18_DesignPatterns.HomeWork;

import javafx.scene.image.Image;

import java.io.File;

public class DefaultImageLoader implements ImgLoader{
    @Override
    public Image loadImage(String path) {
        File file = new File(path);
        return new Image(file.toURI().toString());
    }
}
