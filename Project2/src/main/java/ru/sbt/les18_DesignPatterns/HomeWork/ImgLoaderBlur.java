package ru.sbt.les18_DesignPatterns.HomeWork;

import Catalano.Imaging.FastBitmap;
import Catalano.Imaging.Filters.GaussianBlur;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;

public class ImgLoaderBlur extends ImgLoaderDecorator{
    public ImgLoaderBlur(ImgLoader loader) {
        super(loader);
    }

    @Override
    public Image loadImage(String path) {
        Image img = super.loadImage(path);
        BufferedImage bImg = SwingFXUtils.fromFXImage(img, null);
        FastBitmap fastBitmap = new FastBitmap(bImg);
        GaussianBlur b = new GaussianBlur(5.0D);
        b.applyInPlace(fastBitmap);
        return SwingFXUtils.toFXImage(fastBitmap.toBufferedImage(), null);
    }
}
