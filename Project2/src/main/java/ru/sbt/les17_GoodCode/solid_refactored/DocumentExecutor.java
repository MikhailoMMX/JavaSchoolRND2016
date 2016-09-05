package ru.sbt.les17_GoodCode.solid_refactored;

public class DocumentExecutor {
//был такой метод и было два класса, унаследованные от базового
/*    public void exec(Document document) {
        if (document.getClass().getName().equals(InfoDoc.class.getName())) {
            System.out.println("Cannot exec Info Doc");
        } else {
            document.getAccA().setSaldo(document.getAccA().getSaldo() - document.getSumma());
            document.getAccB().setSaldo(document.getAccB().getSaldo() + document.getSumma());
            System.out.println("From account A to account B: " + document.getSumma());
        }
        //нарушен принцип подстановки Лисков
    }*/

    public void exec(Document document) {
        document.getExecBehavior().exec(document);
    }
}
