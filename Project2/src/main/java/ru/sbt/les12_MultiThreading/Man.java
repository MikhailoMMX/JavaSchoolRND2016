package ru.sbt.les12_MultiThreading;

import java.util.Date;

//immutable объекты
//состояние не изменяется после создания
//все поля - финальные
//ссылка на объект как this никуда не передавалась из конструктора

public class Man {
    private final String name;
    private final Date dob;

    public Man(String name, Date dob) {
        this.name = name;
        //this.dob = dob;     //тут нельзя присваивать - можем изменить переданный объект после передачи параметра где-то извне
        this.dob = new Date(dob.getTime());
    }

    public String getName() {
        return name;
    }

    public Date getDob() {
        //return dob;       // тут тоже нельзя возвращать как есть - можем изменить состояние сложного объекта
        return new Date(dob.getTime());
    }
}
