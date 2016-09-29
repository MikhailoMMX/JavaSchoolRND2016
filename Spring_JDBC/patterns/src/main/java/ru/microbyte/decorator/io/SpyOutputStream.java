package ru.microbyte.decorator.io;

import java.io.IOException;
import java.io.OutputStream;

public class SpyOutputStream extends OutputStream {

    OutputStream out;

    public SpyOutputStream(OutputStream out) {
        super();
        this.out = out;
    }

    public void write(int b) throws IOException {
        out.write(b);
        System.out.println("Я слежу за тобой " + b);
    }
}
