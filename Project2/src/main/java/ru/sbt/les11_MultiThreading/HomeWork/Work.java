package ru.sbt.les11_MultiThreading.HomeWork;

import java.util.*;
import java.util.concurrent.*;

public class Work {
    private static List<String>[] PartitionList(List<String> work, int ThreadNum){
        if (ThreadNum<1)
            throw new RuntimeException("Invalid argument ThreadNum in WorkPartitioner");
        List<String>[] result = new List[ThreadNum];
        int length = work.size();
        int chunkSize = length / ThreadNum;
        for (int i = 0; i< ThreadNum-1; ++i)
            result[i] = work.subList(chunkSize*i, chunkSize*(i+1));
        result[ThreadNum-1] = work.subList((ThreadNum-1)*chunkSize, work.size());
        return result;
    }

    private static Map<Character, Long> JoinMaps(Map<Character, Long>[] Maps) {
        Map<Character, Long> result = new HashMap<>();
        for (Map<Character, Long> map : Maps){
            if (map == null)
                continue;
            for (Map.Entry<Character, Long> e : map.entrySet())
                if (result.containsKey(e.getKey()))
                    result.replace(e.getKey(), result.get(e.getKey()) + e.getValue());
                else
                    result.put(e.getKey(), e.getValue());
        }
        return result;
    }

    public static Map<Character, Long> DoWork(List<String> work, int ThreadNum){
        int Subtasks = ThreadNum * 5;   //чтобы разделить работу на небольшие куски
        List<String>[] tasks = PartitionList(work, Subtasks);
        ExecutorService pool = Executors.newFixedThreadPool(ThreadNum);

        List<Future<Map<Character, Long>>> futures = new ArrayList<>();
        for (List<String> task: tasks) {
            CallableWorker callable = new CallableWorker(task);
            Future<Map<Character, Long>> future = pool.submit(callable);
            futures.add(future);
        }
        Map<Character, Long>[] Maps = new Map[Subtasks];

        for (int i = 0; i< Subtasks; ++i) {
            try {
                Maps[i] = futures.get(i).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        pool.shutdown();
        return JoinMaps(Maps);
    }
}
