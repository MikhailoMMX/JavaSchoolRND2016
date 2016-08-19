package ru.sbt.les12_MultiThreading.HomeWork;

import ru.sbt.les11_MultiThreading.HomeWork.IOUtils;
import ru.sbt.les11_MultiThreading.HomeWork.Worker;

import java.io.IOException;
import java.util.List;
import java.util.Map;

//Задача - считать файл, разбить на слова и заполнить ими список
//После этого список в многопоточном режиме разбирается на частотный словарь букв
//Замеряется время обработки большого файла в последовательном режиме
//и с использованием пула с разным количеством потоков

public class Main {
    //Входной файл
    private static String PATH_IN = "Input\\EragonFullShuffle.txt";
    //private static String PATH_IN = "Input\\EragonFull.txt";
    //private static String PATH_IN = "Input\\EragonSample.txt";
    //private static String PATH_IN = "Input\\La_disparition.txt";

    public static void main(String[] args) throws IOException {
        long progStart = System.currentTimeMillis();
        List<String> Words = IOUtils.ReadInputFile(PATH_IN);
        long FilesRead = System.currentTimeMillis();
        System.out.println("Reading file time: " + (FilesRead-progStart)+ " ms");

        Map<Character, Long> map = Worker.ConvertWordsToMap(Words);
        long NoTP = System.currentTimeMillis();
        System.out.println("No threadPool: " + (NoTP-FilesRead)+ " ms, checksum = " + IOUtils.Sum(map));

        MyWork myWork = new MyWork(1);
        map = myWork.DoWork(Words, 1, 100);
        System.out.println("Warm-up, 1 thread: " + (System.currentTimeMillis()-NoTP)+ " ms, checksum = " + IOUtils.Sum(map));

        for (int i = 1; i < 16; i++) {
            long workStarted = System.currentTimeMillis();
            map = myWork.DoWork(Words, i, 100);
            long workFinished = System.currentTimeMillis();
            System.out.println("Processing file with "+i+" threads: " + (workFinished - workStarted) + " ms, checksum = " + IOUtils.Sum(map));
        }
        myWork.shudtownPool();
    }
}