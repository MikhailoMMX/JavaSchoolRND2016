package ru.sbt.les14_JavaUtilConcurrent.HomeWork;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class InvocationHandlerCaching2 implements InvocationHandler {
    private final Object delegate;
    private ConcurrentHashMap<MyTuple<String>, Integer> cache = new ConcurrentHashMap<>();
    public InvocationHandlerCaching2(Object delegate) {
        this.delegate = delegate;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //проверяем, тот ли метод вызывается, правильные ли параметры передаются
        if (method.getName().equals("CalculateDistance")
//                && method.getParameterCount() == 2
//                && method.getReturnType() == int.class
//                && method.getParameterTypes()[0] == String.class
//                && method.getParameterTypes()[1] == String.class
//                && args.length == 2
//                && args[0].getClass().equals(String.class)
//                && args[1].getClass().equals(String.class)
           )
        {
            MyTuple<String> params = new MyTuple<>((String)args[0], (String)args[1]);
            if (cache.containsKey(params))
                return cache.get(params);

            //первый поток
            int result = (int) method.invoke(delegate, args);
            cache.putIfAbsent(params, result);
            return result;
        }
        return method.invoke(delegate, args);
    }
}
