package ru.sbertech.lesson6.HomeWork;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

public class InvocationHandlerCaching implements InvocationHandler {
    private final Object delegate;
    private HashMap<MyTuple<String>, Integer> cache = new HashMap<>();
    public InvocationHandlerCaching(Object delegate) {
        this.delegate = delegate;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //проверяем, тот ли метод вызывается, правильные ли параметры передаются
        if (method.getName().equals("CalculateDistance")
                && method.getParameterCount() == 2
                && method.getReturnType() == int.class
                && method.getParameterTypes()[0] == String.class
                && method.getParameterTypes()[1] == String.class
                && args.length == 2
                && args[0].getClass().equals(String.class)
                && args[1].getClass().equals(String.class))
        {
            MyTuple<String> myTuple = new MyTuple<String>((String)args[0], (String)args[1]);
            if (cache.containsKey(myTuple)) {
                System.out.print("Cached: ");
                return cache.get(myTuple);
            }
            int result = (int) method.invoke(delegate, args);
            cache.put(myTuple, result);
            return result;
        }
        return method.invoke(delegate, args);
    }
}
