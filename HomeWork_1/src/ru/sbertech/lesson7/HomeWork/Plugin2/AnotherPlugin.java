package ru.sbertech.lesson7.HomeWork.Plugin2;

import ru.sbertech.lesson7.HomeWork.CommonClass;
import ru.sbertech.lesson7.HomeWork.Plugin;

public class AnotherPlugin implements Plugin {
    @Override
    public void doUseful() {
        System.out.println("Running Plugin 2");
        CommonClass.info();
    }
}
