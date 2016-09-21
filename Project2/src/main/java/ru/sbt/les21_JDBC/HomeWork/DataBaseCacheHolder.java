package ru.sbt.les21_JDBC.HomeWork;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

class DataBaseCacheHolder implements CacheHolder, AutoCloseable {
    private static final String URL = "jdbc:h2:tcp://localhost/~/test";
    private static final int BATCH_SIZE = 2;
    private final Object lockObject = new Object();
    private ExecutorService pool = Executors.newCachedThreadPool();
    private ConcurrentHashMap<Tuple<String>, Integer> newResults = new ConcurrentHashMap<>();

    @Override
    public Map<Tuple<String>, Integer> getCache() {
        HashMap<Tuple<String>, Integer> result = new HashMap<>();
        try (
                Connection connection = DriverManager.getConnection(URL, "sa", "");
                PreparedStatement ps = connection.prepareStatement("select * from distance");
        ) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                result.put(new Tuple<String>(resultSet.getString(1), resultSet.getString(2)), resultSet.getInt(3));
            }
        } catch (SQLException e) {
            Logger.getGlobal().log(Level.WARNING, "Could not read from DB", e);
        }
        return result;
    }

    @Override
    public void putNewValue(Tuple<String> key, Integer value) {
        newResults.putIfAbsent(key, value);
        if (newResults.size()>=BATCH_SIZE)
            synchronized (lockObject){
                if (newResults.size() == 0)
                    return;

                ConcurrentHashMap<Tuple<String>, Integer> tempCache = newResults;
                newResults = new ConcurrentHashMap<>();
                pool.submit(new DBCacheSaver(tempCache));
            }
    }

    private class DBCacheSaver implements Runnable{
        private Map<Tuple<String>, Integer> cache;
        DBCacheSaver(Map<Tuple<String>, Integer> cache){
            this.cache = cache;
        }
        @Override
        public void run() {
            try (
                    Connection connection = DriverManager.getConnection(URL, "sa", "");
                    PreparedStatement ps = connection.prepareStatement("insert into distance values(?, ?, ?)");
            ) {
                for (Map.Entry<Tuple<String>, Integer> entry : cache.entrySet()) {
                    ps.setString(1, entry.getKey().getFirst());
                    ps.setString(2, entry.getKey().getSecond());
                    ps.setInt(3, entry.getValue());
                    ps.addBatch();
                }
                ps.executeBatch();
            } catch (SQLException e) {
                Logger.getGlobal().log(Level.WARNING, "Could not write to DB", e);
            }
        }
    }

    @Override
    public void close() throws Exception {
        pool.submit(new DBCacheSaver(newResults));
        pool.shutdown();
    }
}
