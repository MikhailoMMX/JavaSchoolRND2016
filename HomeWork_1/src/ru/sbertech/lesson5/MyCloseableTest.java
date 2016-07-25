package ru.sbertech.lesson5;

class Ok implements AutoCloseable{
    private String message;

    public Ok(String message) {
        this.message = message;
        System.err.println("I've been created " + message);
    }

    @Override
    public void close() throws Exception {
        System.err.println("All resources were released for " + message);
    }
}

class Fail implements AutoCloseable{
    private String message;

    public Fail(String message) {
        this.message = message;
        System.err.println("I'm creating " + message);
        throw new NullPointerException();
    }

    @Override
    public void close() throws Exception {
        System.err.println("All resources were released for " + message);
    }
}

class FailClose implements AutoCloseable{
    private String message;

    public FailClose(String message) {
        this.message = message;
        System.err.println("I'm creating " + message);
    }

    @Override
    public void close() throws Exception {
        System.err.println("Releasing resources for " + message);
        throw new NullPointerException();
    }
}

public class MyCloseableTest {
    public static void main(String[] args) throws Exception {
//        try (Ok x = new Ok("x"); Ok y = new Ok("y")){
//            System.err.println("Serious business");
//        }
//
//        try (Ok x = new Ok("x"); Fail y = new Fail("y")){
//            System.err.println("Serious business");
//        }

        try (Ok y = new Ok("y"); FailClose fc = new FailClose("x")){
            System.err.println("Serious business");
            throw new Error();
        }
//        catch (Exception e){
//            System.err.println("Caught exception");
//        }
    }
}
