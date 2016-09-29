package ru.microbyte.adapter;

public class VerctorFromRasterAdapter extends JavaRaster implements VectorGraphicsInterface{

    public void drawLine() {
        drawRasterLine();
    }

    public void drawSquare() {
        drawRasterSquare();
    }
}
