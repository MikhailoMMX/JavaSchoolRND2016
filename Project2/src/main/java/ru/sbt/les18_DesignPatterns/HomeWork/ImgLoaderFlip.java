package ru.sbt.les18_DesignPatterns.HomeWork;

import Catalano.Imaging.Filters.Mirror;
import Catalano.Imaging.FastBitmap;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;

/**
 * Created by MikhailoMMX on 11.09.2016.
 */
public class ImgLoaderFlip extends ImgLoaderDecorator {
    public ImgLoaderFlip(ImgLoader loader) {
        super(loader);
    }

    @Override
    public Image loadImage(String path) {
        Image img = super.loadImage(path);
        BufferedImage bImg = SwingFXUtils.fromFXImage(img, null);
        FastBitmap fastBitmap = new FastBitmap(bImg);
        Mirror mirror = new Mirror(true, false);
        mirror.applyInPlace(fastBitmap);
        return SwingFXUtils.toFXImage(fastBitmap.toBufferedImage(), null);
    }
}
