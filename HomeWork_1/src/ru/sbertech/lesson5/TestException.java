package ru.sbertech.lesson5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestException {
    public static void main(String[] args) {
        try {
            a();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    private static void a() throws IOException {
        try {
            b();
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private static void b() throws IOException {
        c();
    }

    private static void c() throws IOException {
        FileInputStream in = new FileInputStream("1.txt");
    }
}
