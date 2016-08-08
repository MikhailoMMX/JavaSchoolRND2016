package ru.sbt.les9_Serialization;

import java.io.*;

class A implements Serializable{
    private B b;
    public B getB() { return b; }
    public void setB(B b) { this.b = b; }
}

class B implements Serializable{
    private C c;
    public C getC() { return c; }
    public void setC(C c) { this.c = c; }
}

class C implements Serializable{
    private A a;
    public A getA() { return a; }
    public void setA(A a) { this.a = a; }
}

public class RecursiveLinks {

    public static void main(String[] args) {
        String fileName = "C:\\MikhailoMMX\\links.bin";

        A a = new A();
        B b = new B();
        C c = new C();
        a.setB(b);
        b.setC(c);
        c.setA(a);

        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(a);
            oos.writeObject(b);
            oos.writeObject(c);
        } catch (IOException e) {  e.printStackTrace(); }

        System.out.println("== serialisation ==");

        A a2 = null;
        B b2 = null;
        C c2 = null;
        try (FileInputStream fos = new FileInputStream(fileName);
             ObjectInputStream oos = new ObjectInputStream(fos);) {
            a2 = (A) oos.readObject();
            b2 = (B) oos.readObject();
            c2 = (C) oos.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(a2.getB() == b2);
        System.out.println(b2.getC() == c2);
        System.out.println(c2.getA() == a2);
        //весь граф восстанавливается как надо

        System.out.println(a2.getB());
    }
}
