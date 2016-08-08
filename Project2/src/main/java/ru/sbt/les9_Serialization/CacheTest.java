package ru.sbt.les9_Serialization;

import java.io.*;

class DataHolder implements Serializable {
    public String value = "AAAAAAAA";
}

public class CacheTest {
    public static void main(String[] args) {
        String fileName = "C:\\MikhailoMMX\\links.bin";

        DataHolder dataholder = new DataHolder();

        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(dataholder);        // объект хранится в кэше, сборщик мусора не соберет его
            dataholder.value = "BBBBBBBB";
            oos.writeUnshared(dataholder);      // unshared - для игнорирования кэша
            oos.reset();                        // или можно сбросить кэш, но тогда продублируется метаинформация в файле
            dataholder.value = "CCCCCCCC";
            oos.writeObject(dataholder);
        } catch (IOException e) {  e.printStackTrace(); }

        System.out.println("== serialisation ==");

        DataHolder dataholder2 = null;
        DataHolder dataholder3 = null;
        DataHolder dataholder4 = null;

        try (FileInputStream fos = new FileInputStream(fileName);
             ObjectInputStream oos = new ObjectInputStream(fos);) {
            dataholder2 = (DataHolder) oos.readObject();
            dataholder3 = (DataHolder) oos.readObject();
            dataholder4 = (DataHolder) oos.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(dataholder2.value);
        System.out.println(dataholder3.value);
        System.out.println(dataholder4.value);

    }
}
