package ru.sbt.les16_JIT;

import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

public class Test2 {
    private static final int SIZE = 1_000_000_000;
    static HashSet<String> set = new HashSet<>();
    public static void main(String[] args) {
        for (int i = 0; i < SIZE; i++) {
            createUnusedObjects();
        }
        System.out.println("Finished; total items: " + set.size());
    }
    private static void createUnusedObjects(){
        Object o = new Task1();
        if (ThreadLocalRandom.current().nextInt(500)==0)
            set.add(o.toString());
    }
}
// serial = ok, 338 collections, 23.6s
// parallel = OutOfMemoryError: GC overhead limit exceeded, 311 collections, 2m 1s
// ParallelOld = too slow...
// ConcMarkSweep = ok, 1589 collections, 32.7s
// G1 = ok, 336 collections, 5.4s

// По скорости:
// быстрее <-> медленнее
// G1, Serial, CMS, Parallel*, ParallelOld
