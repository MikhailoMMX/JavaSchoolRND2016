package ru.sbt.les18_DesignPatterns.HomeWork;

import javafx.scene.image.Image;

public class ImgLoaderDecorator implements ImgLoader{
    ImgLoader loader;
    public ImgLoaderDecorator(ImgLoader loader){
        this.loader = loader;
    }
    @Override
    public Image loadImage(String path) {
        return loader.loadImage(path);
    }
}
