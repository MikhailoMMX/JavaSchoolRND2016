package ru.sbt.les18_DesignPatterns.HomeWork;

import Catalano.Imaging.FastBitmap;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;

public class ImgLoaderBW extends ImgLoaderDecorator{
    public ImgLoaderBW(ImgLoader loader) {
        super(loader);
    }

    @Override
    public Image loadImage(String path) {
        Image img = super.loadImage(path);
        BufferedImage bImg = SwingFXUtils.fromFXImage(img, null);
        FastBitmap fastBitmap = new FastBitmap(bImg);
        fastBitmap.toGrayscale();
        return SwingFXUtils.toFXImage(fastBitmap.toBufferedImage(), null);
    }
}
