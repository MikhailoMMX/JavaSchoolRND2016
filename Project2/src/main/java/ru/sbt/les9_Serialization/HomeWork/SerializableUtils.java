package ru.sbt.les9_Serialization.HomeWork;

import java.io.*;

public class SerializableUtils {
    public static void serialize(String filename, Object object) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(fos))
        {
            out.writeObject(object);
        }
    }

    public static Object deserialize(String filename) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(fis))
        {
            return in.readObject();
        }
    }
}
