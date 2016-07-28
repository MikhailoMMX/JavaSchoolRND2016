package ru.sbertech.lesson6;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by Student on 28.07.2016.
 */
public class InvocationHandlerImpl implements InvocationHandler{
    private final Object delegate;
    public InvocationHandlerImpl(Object delegate) {
        this.delegate = delegate;
    }
    @Override
    public Object invoke(Object proxy, Method method,Object[]args) throws Throwable {
        System.out.println("Started " + method.getName() + ", params " + Arrays.toString(args));
        Object result = null;
        if (args[0] != "42")
            result = method.invoke(delegate, args);
        else {
            System.out.println("You shall not pass!!!");
            result = false;
        }
        System.out.println("Finished " + method.getName() + ". Result " + result);
        return result;
    }
}
