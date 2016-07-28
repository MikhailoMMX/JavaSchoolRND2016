package ru.sbertech.lesson6;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ReflectionTest1 {
    public static void main(String[] args) throws Exception {
        //Arrays.asList(B.class.getDeclaredMethods()).forEach(System.out::println);
        //Test1();
        //Test2();
        Test3();
        //Test4();
    }

    private static void Test4() throws Exception {
        WithPrivateFinalField pf = new WithPrivateFinalField();

        Field f = pf.getClass().getDeclaredField("i");
        f.setAccessible(true);
        f.setInt(pf, 47);
        System.out.println(pf);

        f = pf.getClass().getDeclaredField("s");
        f.setAccessible(true);
        f.set(pf, "MODIFY S");
        System.out.println(pf);


        f = pf.getClass().getDeclaredField("s2");
        f.setAccessible(true);
        f.set(pf, "MODIFY S2");
        System.out.println(pf);
    }

    private static void Test3() {
        B b = new B();
        //b.fin = "Changed";    // нельзя
        try {
            Field f = B.class.getDeclaredField("fin");
            f.setAccessible(true);
            //Object ob = B.class.newInstance();
            System.out.println("Old value: " + f.get(b));
            f.set(b, "Changed");
            //System.out.println("New value: " + (b.fin));
            System.out.println("New value: " + f.get(b));
            b.writeFin();
            f.get(b);
            //можно изменить значение final полей
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static void Test2() {
        System.out.println(A.class.getSuperclass().toString());
        System.out.println(Object.class.getSuperclass());
    }

    private static void Test1(){
        try {
            Method m = A.class.getDeclaredMethod("pA", String.class);
            //System.out.println(m.toString());
            //System.out.println(B.class.getDeclaredField("a"));

            //m.setAccessible(true);  //для вызова приватного метода
            //m.invoke(A.class.newInstance(), "string");

            Method m2 = I.class.getDeclaredMethod("someMethod", String.class);
            m.invoke(B.class.newInstance(), "string");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}

class A{
    public void DoSomething(){}
    public void pA(String str){
        System.out.println("From A " + str);
    }
    public static void psA(){
        System.out.println("Hello, world");
    }


}
class B extends A implements I {
    public void DoSomethingElse(){}
    private void DoSomethingSecret(){}

    private A a = new A();
    public Object object;

    private final String fin ;//= "Change me";

    public B(){
        if (true)
            fin = "Change me now!";
        else
            fin = "Change me";
    }
    public void writeFin(){
        System.out.println(fin);
    }

    @Override
    public void someMethod(String param) {
        a.pA(param);
    }
}
class WithPrivateFinalField {
    private int i = 1;
    private final String s = "String S";
    private String s2 = "String S2";

    public String toString() {
        return "i = " + i + ", " + s + ", " + s2;
    }
}