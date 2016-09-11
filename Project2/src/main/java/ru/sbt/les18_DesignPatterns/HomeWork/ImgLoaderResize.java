package ru.sbt.les18_DesignPatterns.HomeWork;

import Catalano.Imaging.FastBitmap;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import Catalano.Imaging.Filters.ResizeBicubic;

import java.awt.image.BufferedImage;

public class ImgLoaderResize extends ImgLoaderDecorator {
    public ImgLoaderResize(ImgLoader loader) {
        super(loader);
    }

    @Override
    public Image loadImage(String path) {
        Image img = super.loadImage(path);
        BufferedImage bImg = SwingFXUtils.fromFXImage(img, null);
        FastBitmap fastBitmap = new FastBitmap(bImg);
        ResizeBicubic resizeBicubic = new ResizeBicubic(fastBitmap.getWidth()/2, fastBitmap.getHeight()/2);
        resizeBicubic.applyInPlace(fastBitmap);
        return SwingFXUtils.toFXImage(fastBitmap.toBufferedImage(), null);
    }
}
