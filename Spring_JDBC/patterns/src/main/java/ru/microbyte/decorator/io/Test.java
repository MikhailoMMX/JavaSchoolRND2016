package ru.microbyte.decorator.io;

import java.io.*;

public class Test {
    public static void main(String[] args) throws IOException {
        //OutputStream out = new SpyOutputStream(new BufferedOutputStream(new FileOutputStream("c:/txt.txt")));
        OutputStream out = new BufferedOutputStream(
                new SpyOutputStream(
                        //new GZIPOutputStream(
                                new FileOutputStream("c:/txt2.txt")
                        //)
                )
        );
        out.write(10);
        out.write(10);
        out.write(10);
        out.write(10);
        out.write(10);

        out.flush();
        out.close();
    }
}
