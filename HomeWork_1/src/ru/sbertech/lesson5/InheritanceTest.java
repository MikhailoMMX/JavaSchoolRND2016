package ru.sbertech.lesson5;

import java.io.FileNotFoundException;
import java.io.IOException;

class Parent {
    public void method() throws IOException{

    }
}

class Child extends Parent{
    @Override
    public void method() throws FileNotFoundException {
        //можно уточнять
    }

//    public void method() throws Exception {
//        //нельзя обобщать
//    }
}

public class InheritanceTest {
    public static void main(String[] args) {

    }
}
