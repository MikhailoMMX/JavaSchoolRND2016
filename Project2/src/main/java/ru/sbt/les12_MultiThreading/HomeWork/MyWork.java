package ru.sbt.les12_MultiThreading.HomeWork;

import ru.sbt.les11_MultiThreading.HomeWork.CallableWorker;

import javax.swing.text.html.FormSubmitEvent;
import java.util.*;

//класс, который делит задачу на куски, выполняет ее в нашем пуле потоков
//и собирает результат из частей

public class MyWork {
    private MyThreadPool pool;
    public MyWork(int ThreadCount) {
        pool = new MyThreadPool(ThreadCount);
    }

    private List<String>[] PartitionList(List<String> work, int partsNum){
        if (partsNum<1)
            throw new RuntimeException("Invalid argument partsNum in WorkPartitioner");
        List<String>[] result = new List[partsNum];
        int length = work.size();
        int chunkSize = length / partsNum;
        for (int i = 0; i< partsNum-1; ++i)
            result[i] = work.subList(chunkSize*i, chunkSize*(i+1));
        result[partsNum-1] = work.subList((partsNum-1)*chunkSize, work.size());
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

    private List<MyFuture<Map<Character, Long>>> submitWork(List<String>[] work, int threadCount, MyThreadPool pool){
        Thread[] threads = new Thread[threadCount];
        pool.setThreadCount(threadCount);
        List<MyFuture<Map<Character, Long>>> futures = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = number; i<work.length; i += size)
                        futures.add(pool.submit(new CallableWorker(work[i])));
                }
                int size;
                int number;
                public Runnable init(int size, int number){
                    this.size = size;
                    this.number = number;
                    return this;
                }
            }.init(threadCount, i));
            threads[i].start();
        }
        for (int i = 0; i < threadCount; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return futures;
    }

    public Map<Character, Long> DoWork(List<String> work, int threadCount, int partsNum){
        List<String>[] tasks = PartitionList(work, partsNum);
        pool.setThreadCount(threadCount);

        List<MyFuture<Map<Character, Long>>> futures = submitWork(tasks, threadCount, pool);

        Map<Character, Long>[] Maps = new Map[partsNum];
        for (int i = 0; i< partsNum; ++i)
            Maps[i] = futures.get(i).get();
        return JoinMaps(Maps);
    }

    public void shudtownPool(){
        pool.setThreadCount(0);
    }
}
