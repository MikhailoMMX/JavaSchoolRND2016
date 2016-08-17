package ru.sbt.les11_MultiThreading.HomeWork;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

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

    private static void ReadAndSetThreadNum(){
        try (Scanner s = new Scanner(new BufferedInputStream(new FileInputStream(PATH_SETTINGS)))) {
            if (s.hasNextInt())
                ThreadNum = s.nextInt();
        } catch (FileNotFoundException e) {
            ThreadNum = 1;                          //default value
        }
    }

    private static List<String> ReadInputFile(String FileName) {
        List<String> result = new ArrayList<>();
        try (Scanner s = new Scanner(Paths.get(FileName), "windows-1251").useDelimiter("\\s")) {
            while (s.hasNext())
                result.add(s.next());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static void PrintStats(Map<Character, Long> map){
        long TotalLetters = map.values().stream().reduce(0L, (a, b) -> a+b);
        System.out.println("Total letters: " + TotalLetters);
        map.entrySet()
                .stream()
                .sorted(Comparator.comparingLong((Map.Entry<Character, Long> e)->e.getValue()).reversed())
                .map(e -> e.getKey() + ": " + (float)e.getValue()/TotalLetters*100 + "%")
                .forEach(System.out::println);

    }

    public static void main(String[] args) {
        ReadAndSetThreadNum();
        long progStart = System.currentTimeMillis();
        List<String> Words = ReadInputFile(PATH_IN);
        long FilesRead = System.currentTimeMillis();

        //последовательная версия
        //Map<Character, Long> map = Worker.ConvertWordsToMap(Words);

        //параллельная версия
        Map<Character, Long> map = Work.DoWork(Words, ThreadNum);

        long ProcessFinished = System.currentTimeMillis();
        PrintStats(map);

        System.out.println("Reading file time: " + (FilesRead-progStart)+ " ms");
        System.out.println("Processing file time: " + (ProcessFinished-FilesRead)+ " ms");
    }
}
