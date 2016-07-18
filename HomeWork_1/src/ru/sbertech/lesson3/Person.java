package ru.sbertech.lesson3;

/**
 * Created by Student on 18.07.2016.
 */
public class Person implements Comparable{
    Long id;
    String name;
    String phoneNumber;

    @Override
    public boolean equals(Object obj) {
        return this.getId().equals(((Person)obj).getId());
    }


    @Override
    public int hashCode() {
        return (this.getId() + this.getName() + this.getPhoneNumber()).hashCode();
    }

    @Override
    public String toString() {
        return "Person{id="+this.getId()+";name=" + this.getName() + ";phoneNumber=" + this.getPhoneNumber();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Person(Long id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int compareTo(Object o) {
        return this.getName().compareTo(((Person)o).getName());
    }
}
