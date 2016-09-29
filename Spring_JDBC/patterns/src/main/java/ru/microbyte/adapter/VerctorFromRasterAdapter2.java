package ru.microbyte.adapter;

public class VerctorFromRasterAdapter2 implements VectorGraphicsInterface{

    private JavaRaster javaRaster;

    public VerctorFromRasterAdapter2() {
        javaRaster = new JavaRaster();
    }

    public void drawLine() {
        javaRaster.drawRasterLine();
    }

    public void drawSquare() {
        javaRaster.drawRasterSquare();
    }
}
