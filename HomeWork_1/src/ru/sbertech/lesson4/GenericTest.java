package ru.sbertech.lesson4;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Student on 21.07.2016.
 */
public class GenericTest {
    public static void main(String[] args){
        ArrayList<String> strList = new ArrayList<String>();
        //strList.add(new Integer(1)); // Illegal

        // Java 1.7 diamond operator <>
        //ArrayList<String> strList = new ArrayList<>();
        //List<Integer> iList = new ArrayList();    //можно даже так

        A<String> a = new A<>();
        a.setParam("Hello");
        A<List> aList = new A<>();
        aList.setParam(new ArrayList<String>());
        System.out.println(a.getType());
        System.out.println(aList.getType());
        B<Long, Short> b = new B<>();
        b.setParam("s");
        System.out.println(b.getType());

        //нельзя параметризировать анонимные классы (если A не был параметризован)
        System.out.println(new A<String>(new String()) {
            @Override
            public String getType() {
              this.param = "";
                return "Abstract " + param.getClass().getTypeName();
            }
        }.getType());

        System.out.println(GetType(1L));
        System.out.println(GenericTest.<Short>GetType(new Short("41")));
        //System.out.println(GenericTest.<Short>GetType(new Integer("41")));    //ошибка

        //Забавно
        System.out.println(GenericTest.<String>GetType(GenericTest.<String>GetInteger()));
        //classCastException
        //System.out.println(GenericTest.<String>GetInteger().toString());

        //нельзя
        //List<Number> l1 = new ArrayList<Integer>();
        //List<Integer> l2 = new ArrayList<Number>();

        List<?> lq = new ArrayList<Integer>();
        lq.add(null);           // - можно
        //lq.add(new Object()); // - нельзя

        //PECS - Producer Extends Consumer Super
        //Extends - можно считывать
        //super - только для записи, без гарантий какой тип объекта можно считать
        List<? super Integer> li = new ArrayList<>();

        System.out.println("--  extends --");

        A<? extends Number> a2 = new A<>();
        //a2.setParam(10);  //нельзя

        A<? extends Integer> a3 = new A<>(new Integer(10));     // можно
        //a3.setParam(new Integer(10));  //нельзя
        System.out.println(a3.getParam());          //можно

        A<? extends B> a4 = new A<>(new B());
        System.out.println(a.getType());
        B b4 = a4.getParam();
        //C c4 = a4.getParam(); //нельзя

        System.out.println("--  super --");
        List<? super Number> lnum = new ArrayList<>();
        lnum.add(new Integer(10));
        lnum.add(new Long(20));
        lnum.add(new Double(3.14));
        //Integer i = lnum.get(0); //ошибка
        //Number n = lnum.get(0);   //тоже ошибка
        Object o = lnum.get(0); //можно
        lnum.forEach(System.out::println);
    }

    //параметризация метода
    public static <T> String GetType(T param){
        return param.getClass().getTypeName();
    }
    public static <T> T GetInteger(){
        return (T) new Integer(10);
    }
}
