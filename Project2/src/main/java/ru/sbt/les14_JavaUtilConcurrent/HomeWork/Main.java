package ru.sbt.les14_JavaUtilConcurrent.HomeWork;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    private final static int SIZE = 800;        //количество слов в списке
    //с ростом количества слов эффект от кэширования падает. Даже в последовательном случае

    private final static int RATIO = 1;         //сколько раз добавляется каждое слово
    //при увеличении количества повторов прирост производительности от кэширования существенно растет

    private final static int LENGTH = 10;       //длина слова
    //при увеличении длины слова приросто производительности от кэширования растет
    //при SIZE == 1000; RATIO == 1 :
    //до 3-8 (в зависимости от количества повторов) даже обычное кэширование ухудшает производительность
    //до 12-15 накладные расходы на синхнонизацию влияют сильнее чем распараллеливание

    private static List<String> fillStrings() {
        List<String> entries = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            StringBuilder sb = new StringBuilder();
            int len = LENGTH; //ThreadLocalRandom.current().nextInt(LENGTH)+1;
            for (int j = 0; j<len; ++j)
                sb.append((char)'A' + ThreadLocalRandom.current().nextInt(26));
            for (int j = 0; j<RATIO; ++j)
                entries.add(sb.toString());
        }
        return entries;
    }

    private static Integer[][] calculateAllPairs(List<String> entries, IDistance distanceImpl, int threads) throws ExecutionException, InterruptedException {
        if (threads<1)
            throw new IllegalArgumentException("Threads < 1");
        int size = entries.size();
        Integer[][] result = new Integer[size][size];
        Future<Integer>[][] futures = new Future[size][size];

        ExecutorService pool = Executors.newFixedThreadPool(threads);
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                futures[i][j] = pool.submit(new Worker(entries.get(i), entries.get(j), distanceImpl));

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                result[i][j] = futures[i][j].get();

        pool.shutdown();
        return result;
    }

    private static Integer[][] calculateAllPairsNoThreadPool(List<String> entries, IDistance distanceImpl) throws ExecutionException, InterruptedException {
        int size = entries.size();
        Integer[][] result = new Integer[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                result[i][j] = distanceImpl.CalculateDistance(entries.get(i), entries.get(j));
        return result;
    }

    private static int getTotalDist(Integer[][] matrix){
        int result = 0;
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++)
                result += matrix[i][j];
        return result;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        IDistance levenshteinDistance = new LevenshteinDistance();
        List<String> entries = fillStrings();

        //без кэширования
        long seqStart = System.currentTimeMillis();
        Integer[][] distances = calculateAllPairsNoThreadPool(entries, levenshteinDistance);
        long seqEnd = System.currentTimeMillis();
        int seqRresult = getTotalDist(distances);
        System.out.println("No cache: \t\t\t\t\ttime = " + (seqEnd-seqStart) + " ms \ttotal distance = " + seqRresult);

        //однопоточное вычисление, кэширование из задания 6
        IDistance seqLoggedLD = (IDistance) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                levenshteinDistance.getClass().getInterfaces(),
                new InvocationHandlerCachingSeq(levenshteinDistance)
        );
        long seqCacheStart = System.currentTimeMillis();
        distances = calculateAllPairsNoThreadPool(entries, seqLoggedLD);
        long seqCacheEnd = System.currentTimeMillis();
        int seqCacheRresult = getTotalDist(distances);
        System.out.println("Singe-thread cache: \t\ttime = " + (seqCacheEnd-seqCacheStart) + " ms \ttotal distance = " + seqCacheRresult + (seqCacheRresult == seqRresult? "":" <<<< FAILED!!!"));

        //многопоточный кэш + однопоточное выполнение
        //нет накладных расходов на распараллеливание
        //влияют только накладные расходы на блокировки
        IDistance parSTLoggedLD = (IDistance) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                levenshteinDistance.getClass().getInterfaces(),
                new InvocationHandlerCaching2(levenshteinDistance)
        );
        long parCacheSTStart = System.currentTimeMillis();
        distances = calculateAllPairsNoThreadPool(entries, parSTLoggedLD);
        long parCacheSTEnd = System.currentTimeMillis();
        int parCacheSTRresult = getTotalDist(distances);
        System.out.println("Multi-thread cache (seq): \ttime = " + (parCacheSTEnd-parCacheSTStart) + " ms \ttotal distance = " + parCacheSTRresult + (parCacheSTRresult == seqRresult? "":" <<<< FAILED!!!"));

        //многопоточное вычисление, кэширование
        //накладные расходы в основном приходятся на poo.submit, future.get массивы и их обход
        //блокирующее кэширование еще сильнее ухудшает распараллеливание
        for (int i = 1; i<=8; ++i) {
            IDistance loggedLD = (IDistance) Proxy.newProxyInstance(
                    ClassLoader.getSystemClassLoader(),
                    levenshteinDistance.getClass().getInterfaces(),
                    new InvocationHandlerCaching2(levenshteinDistance)
            );

            long parStart = System.currentTimeMillis();
            distances = calculateAllPairs(entries, loggedLD, i);
            long parEnd = System.currentTimeMillis();
            int result = getTotalDist(distances);
            System.out.println("Multi-thread ("+i+") cache: \ttime = " + (parEnd-parStart) + " ms \ttotal distance = " + result + (result == seqRresult? "":" <<<< FAILED!!!"));
        }

    }
}
