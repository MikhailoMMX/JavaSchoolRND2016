package ru.sbt.les10_LambdaStream.HomeWork;

public class MyFuncInterfDemo {
    public static void main(String[] args) {
        String Input = "  == Some text ==  ";
        System.out.println(DoSomething(Input,  s -> s));                //Лямбда-функция
        System.out.println(DoSomething(Input,  String::trim));          //Экземплярный метод без параметров
        System.out.println(DoSomething(Input,  WordTrimmer::trim));     //Статический метод с одним параметром
    }
    private static String DoSomething(String s, MyFuncInterface<String> func){
        return "["+func.Convert(s)+"]";
    }
}
