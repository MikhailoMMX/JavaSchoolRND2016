package ru.sbertech.lesson6.HomeWork;

import java.util.List;

/**
 * Created by MikhailoMMX on 31.07.2016.
 */
public class SomeClassA {
    private int count;
    private String name;
    private List<SomeClassA> subNodes;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SomeClassA> getSubNodes() {
        return subNodes;
    }

    public void setSubNodes(List<SomeClassA> subNodes) {
        this.subNodes = subNodes;
    }

    public int getRandomNumber(){
        return 4;   //chosen by fair dice roll
                    //guaranteed to be random
                                    //XKCD #221
    }
}
