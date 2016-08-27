package ru.sbt.les14_JavaUtilConcurrent.HomeWork;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class InvocationHandlerCaching implements InvocationHandler {
    private final Object delegate;
    private ConcurrentHashMap<MyTuple<String>, Integer> cache = new ConcurrentHashMap<>();
    private ConcurrentHashMap<MyTuple<String>, ReentrantReadWriteLock> locks = new ConcurrentHashMap<>();
    public InvocationHandlerCaching(Object delegate) {
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

            ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
            lock.writeLock().lock();
            ReentrantReadWriteLock actualLock = locks.putIfAbsent(params, lock);
            if (actualLock == null){
                //первый поток
                int result = (int) method.invoke(delegate, args);
                cache.put(params, result);
                lock.writeLock().unlock();
                return result;
            }
            else{
                //последующие потоки
                lock.writeLock().unlock();
                actualLock.readLock().lock();
                int result = cache.get(params);
                actualLock.readLock().unlock();
                return result;
            }
        }
        return method.invoke(delegate, args);
    }
}
