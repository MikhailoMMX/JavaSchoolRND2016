package ru.sbt.les17_GoodCode.solid_original;

public class Document {
    private Client clientA;
    private Account accA;
    private Client clientB;
    private Account accB;
    private Long summa;
    private String user;

    public Client getClientA() {
        return clientA;
    }

    public void setClientA(Client clientA) {
        this.clientA = clientA;
    }

    public Account getAccA() {
        return accA;
    }

    public void setAccA(Account accA) {
        this.accA = accA;
    }

    public Client getClientB() {
        return clientB;
    }

    public void setClientB(Client clientB) {
        this.clientB = clientB;
    }

    public Account getAccB() {
        return accB;
    }

    public void setAccB(Account accB) {
        this.accB = accB;
    }

    public Long getSumma() {
        return summa;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setSumma(Long summa) {
        this.summa = summa;
    }

    public void exec(){
        accA.setSaldo(accA.getSaldo()-summa);
        accB.setSaldo(accB.getSaldo()+summa);
        System.out.println("From account A to account B: " + summa);
    }

    public void print(){
        System.out.println("Summa: "+summa);
    }
    public void printUserInfo(){
        System.out.println("Document User: "+user);
    }

    public void saveToDB(){
        System.out.println("Saved documetn in DataBase");
    }
}
