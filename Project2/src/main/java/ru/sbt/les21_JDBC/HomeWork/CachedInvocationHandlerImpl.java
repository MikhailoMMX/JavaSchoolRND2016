package ru.sbt.les21_JDBC.HomeWork;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class CachedInvocationHandlerImpl implements InvocationHandler {
    private final Object delegate;
    private CacheHolder cacheHolder;
    private ConcurrentHashMap<Tuple<String>, Integer> cache = new ConcurrentHashMap<>();

    public CachedInvocationHandlerImpl(Object delegate, CacheHolder cacheHolder) {
        this.cacheHolder = cacheHolder;
        this.delegate = delegate;
        readCacheFromHolder();
    }

    private void readCacheFromHolder() {
        Map<Tuple<String>, Integer> loadedCache = cacheHolder.getCache();
        for (Map.Entry<Tuple<String>, Integer> entry : loadedCache.entrySet())
            this.cache.put(entry.getKey(), entry.getValue());
        System.out.println("Loaded " + loadedCache.size() + " entries");
    }

    private void putNewResult(Tuple<String> key, int value){
        cache.putIfAbsent(key, value);
        cacheHolder.putNewValue(key, value);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("CalculateDistance".equals(method.getName())) {
            Tuple<String> params = new Tuple<>((String) args[0], (String) args[1]);
            if (cache.containsKey(params)) {
                System.out.print("/CACHED/: ");
                return cache.get(params);
            }

            int result = (int) method.invoke(delegate, args);
            putNewResult(params, result);
            return result;
        }
        return method.invoke(delegate, args);
    }
}
