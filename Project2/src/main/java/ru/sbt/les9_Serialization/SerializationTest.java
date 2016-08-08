package ru.sbt.les9_Serialization;

import java.io.*;

public class SerializationTest {

    public static void main(String[] args) {
        Student student = new Student();
        System.out.println(student.toString());
        String fileName = "C:\\MikhailoMMX\\student.bin";

//        try (FileOutputStream fos = new FileOutputStream(fileName);
//        ObjectOutputStream oos = new ObjectOutputStream(fos);) {
//            oos.writeObject(student);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        System.out.println("== serialisation ==");

        Student student2 = null;
        try (FileInputStream fos = new FileInputStream(fileName);
             ObjectInputStream oos = new ObjectInputStream(fos);) {
            student2 = (Student) oos.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(student2);
    }
}
