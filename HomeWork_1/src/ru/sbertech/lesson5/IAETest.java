package ru.sbertech.lesson5;

class SBTEmployee{
    int grade;
    public SBTEmployee(int grade) {
        if (grade < 0 || grade > 16)
            throw new IllegalArgumentException();
        this.grade = grade;
    }
}

public class IAETest {
    public static void main(String[] args) {
        SBTEmployee sbtEmployee1 = new SBTEmployee(2);
        SBTEmployee sbtEmployee2 = new SBTEmployee(20);
    }
}
