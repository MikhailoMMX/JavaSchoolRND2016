package ru.sbertech.lesson4;

/**
 * Created by Student on 21.07.2016.
 */
public class A<T> /* extends Throwable нельзя параметризовать */{
    T param;

    public T getParam() {
        return param;
    }

    public void setParam(T param) {
        this.param = param;
    }
     public String getType(){
         return param.getClass().getTypeName();
     }
    public A(T param) {
        this.param = param;
    }
    public A() {

    }

}
