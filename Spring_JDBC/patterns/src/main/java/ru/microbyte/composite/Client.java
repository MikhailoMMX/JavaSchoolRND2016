package ru.microbyte.composite;

public class Client {

    public static Shape createTestDate() {
        Frame frame1 = new Frame();
        Frame frame2 = new Frame();
        Frame frame3 = new Frame();

        Shape triangle1 = new Triangle();
        Shape circle1 = new Circle();
        Shape circle2 = new Circle();

        Shape triangle2 = new Triangle();
        Shape square1 = new Square();

        Shape square2 = new Square();
        Shape square3 = new Square();
        Shape square4 = new Square();
        Shape triangle3 = new Triangle();

        frame1.add(triangle1);
        frame1.add(circle1);
        frame1.add(circle2);

        frame2.add(triangle2);
        frame2.add(square1);


        frame3.add(frame1);
        frame3.add(frame2);

        frame3.add(square2);
        frame3.add(square3);
        frame3.add(square4);
        frame3.add(triangle3);

        return frame3;
    }

    public static void main(String[] args) {
        Shape shape = createTestDate();

        shape.draw();
    }
}
