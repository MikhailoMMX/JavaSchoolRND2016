package ru.sbt.les17_GoodCode.solid_refactored;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        DocumentExecutor documentExecutor = new DocumentExecutor();
        Document infoDoc = new Document(new InfoDocExecImpl());
        infoDoc.setUser("User1");

        Account accA = new Account();
        accA.setSaldo(100L);

        Account accB = new Account();
        accB.setSaldo(0L);

        infoDoc.setAccA(accA);
        infoDoc.setAccB(accB);
        infoDoc.setSumma(10L);
        documentExecutor.exec(infoDoc);
    }
}

