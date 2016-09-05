package ru.sbt.les17_GoodCode.solid_refactored;

public interface IDocument {
    //Это плохой пример

    void print();
    //это вынести в Printable

    void saveToDB();
    //это вынести в Persistable

    void printUserInfo();
    //это вынести еще во что-то
}
