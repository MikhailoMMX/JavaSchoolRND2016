package ru.microbyte.composite;

import java.util.ArrayList;
import java.util.List;

public class Frame implements Shape{

    private List<Shape> shapes = new ArrayList<Shape>();

    public void draw() {
        System.out.println("------------------ frame start ---------------");
        for (Shape shape: shapes) {
            shape.draw();
        }
        System.out.println("------------------ frame end ---------------");
    }

    public void add(Shape shape) {
        shapes.add(shape);
    }

    public void remove(Shape shape) {
        shapes.remove(shape);
    }
}
