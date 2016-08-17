package ru.sbt.les11_MultiThreading.HomeWork;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class CallableWorker implements Callable<Map<Character, Long>> {
    private List<String> work;
    public CallableWorker(List<String> work) {
        this.work = work;
    }

    @Override
    public Map<Character, Long> call() throws Exception {
        return Worker.ConvertWordsToMap(work);
    }
}
