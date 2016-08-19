package ru.sbt.les11_MultiThreading.HomeWork;

import java.util.*;

public class Main {
    //Входной файл
    private static String PATH_IN = "Input\\EragonFullShuffle.txt";
    //private static String PATH_IN = "Input\\EragonFull.txt";
    //private static String PATH_IN = "Input\\EragonSample.txt";
    //private static String PATH_IN = "Input\\La_disparition.txt";

    //Файл с количеством потоков
    private static String PATH_SETTINGS = "Input\\Settings.txt";

    //количество потоков
    private static int ThreadNum = 1;

    public static void main(String[] args) throws Exception {
        IOUtils.ReadAndSetThreadNum(PATH_SETTINGS);
        long progStart = System.currentTimeMillis();
        List<String> Words = IOUtils.ReadInputFile(PATH_IN);
        long FilesRead = System.currentTimeMillis();

        //последовательная версия
        //Map<Character, Long> map = Worker.ConvertWordsToMap(Words);

        //параллельная версия
        Map<Character, Long> map = Work.DoWork(Words, ThreadNum);

        long ProcessFinished = System.currentTimeMillis();
        IOUtils.PrintStats(map);

        System.out.println("Reading file time: " + (FilesRead-progStart)+ " ms");
        System.out.println("Processing file time: " + (ProcessFinished-FilesRead)+ " ms");
    }
}
