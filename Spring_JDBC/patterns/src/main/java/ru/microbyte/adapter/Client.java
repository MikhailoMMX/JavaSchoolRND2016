package ru.microbyte.adapter;

public class Client {
    public static void main(String[] args) {

        VectorGraphicsInterface graphics = new VerctorFromRasterAdapter();
        graphics.drawLine();
        graphics.drawSquare();

//        VectorGraphicsInterface graphics = new VerctorFromRasterAdapter2();
//        graphics.drawLine();
//        graphics.drawSquare();

    }
}
