package ru.sbt.les18_DesignPatterns.HomeWork;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImgLoaderBorder extends ImgLoaderDecorator {
    public ImgLoaderBorder(ImgLoader loader) {
        super(loader);
    }

    @Override
    public Image loadImage(String path) {
        Image img = super.loadImage(path);
        BufferedImage bImg = SwingFXUtils.fromFXImage(img, null);
        Graphics graphics = bImg.getGraphics();
        graphics.setColor(Color.GREEN);
        graphics.drawRect(3,3, bImg.getWidth()-6, bImg.getHeight()-6);
        graphics.drawRect(4,4, bImg.getWidth()-8, bImg.getHeight()-8);
        return SwingFXUtils.toFXImage(bImg, null);
    }
}
