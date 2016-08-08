package ru.sbt.les9_Serialization;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class ExternalizeaableTest implements Externalizable{

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        //можно описать свою логику сохранения.
        //компактнее, быстрее, без рефлексии
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        //но нужно писать много кода
        //и внимательно следить за всем
    }
}
