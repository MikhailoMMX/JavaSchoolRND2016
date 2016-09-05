package ru.sbt.les17_GoodCode.solid_refactored;

public class RealDocExecImpl implements ExecBehavior{
    @Override
    public void exec(Document document) {
        document.getAccA().setSaldo(document.getAccA().getSaldo() - document.getSumma());
        document.getAccB().setSaldo(document.getAccB().getSaldo() + document.getSumma());
        System.out.println("From account A to account B: " + document.getSumma());
    }
}
