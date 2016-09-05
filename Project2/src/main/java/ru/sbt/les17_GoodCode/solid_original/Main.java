package ru.sbt.les17_GoodCode.solid_original;

public class Main {
    public static void main(String[] args) {
        Document d = new Document();
        d.setUser("User1");

        Account accA = new Account();
        accA.setSaldo(100L);

        Account accB = new Account();
        accB.setSaldo(0L);

        d.setAccA(accA);
        d.setAccB(accB);
        d.setSumma(10L);
        d.exec();
    }
}

