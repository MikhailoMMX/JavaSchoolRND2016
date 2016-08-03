package ru.sbertech.lesson7.HomeWork.Plugin1;

import ru.sbertech.lesson7.HomeWork.Plugin;

public class SomePlugin implements Plugin {
    @Override
    public void doUseful() {
        System.out.println("Running Plugin 1 (modified)");
    }
}
