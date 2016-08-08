package ru.sbt.les9_Serialization;

import java.io.*;

/*class Person implements Serializable {
    private String firstName;
    private int age;

    public Person() {
        firstName = "John";
        age = 21;
        System.out.println("constructor person");
    }

    public Person(String firstName, int age) {
        this.firstName = firstName;
        this.age = age;
        System.out.println("constructor person");
    }
}*/

public class Student /*extends Person*/ implements Serializable {
    private String group;
    private int avgMark;
    transient private String password;                  //выключаем сериализацию этого поля
    final static long serialVersionUID = 1L;            //вручную задаем версию
        //если не указано явно - вычисляется как SHA-1

    public Student() {
        group = "default";
        avgMark = 3;
        password = "My very good Pa$$W0ЯD";
        System.out.println("constructor student");      //вызывается только при создании, но не при десериализации
    }

    @Override
    public String toString() {
        return "Student{" +
                "group='" + group + '\'' +
                ", avgMark=" + avgMark +
                '}';
    }

//    public String getGroup() {
//        return group;
//    }
    //добавление метода нарушает serialVersionUID - десериализовать старый файл уже не получится

    private void writeObject(ObjectOutputStream out) throws IOException {
        //throw new NotSerializableException();           //если нужно запретить сериализацию данного класса

        //avgMark = avgMark ^ 0xFFFFFFFF;
        out.defaultWriteObject();
        out.writeLong(System.currentTimeMillis());
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        //avgMark = avgMark ^ 0xFFFFFFFF;
        long value = in.readLong();
        System.out.println(value);
    }

    //еще есть writeReplace()
    //и        readResolve()
}
