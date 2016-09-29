package ru.microbyte.simple;

public class TerminatorQuotes implements Quotes{
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public void sayQuotes() {
        System.out.println(message);
    }
}
