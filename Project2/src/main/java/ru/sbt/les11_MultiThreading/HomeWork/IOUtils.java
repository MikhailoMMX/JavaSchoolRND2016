package ru.sbt.les11_MultiThreading.HomeWork;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class IOUtils {
    public static int ReadAndSetThreadNum(String Path) throws FileNotFoundException {
        try (Scanner s = new Scanner(new BufferedInputStream(new FileInputStream(Path)))) {
            if (s.hasNextInt())
                return s.nextInt();
        }
        return 1;                          //default value
    }

    public static List<String> ReadInputFile(String FileName) throws IOException {
        List<String> result = new ArrayList<>();
        try (Scanner s = new Scanner(Paths.get(FileName), "windows-1251").useDelimiter("\\s")) {
            while (s.hasNext())
                result.add(s.next());
        }
        return result;
    }

    public static void PrintStats(Map<Character, Long> map){
        long TotalLetters = Sum(map);
        System.out.println("Total letters: " + Sum(map));
        map.entrySet()
                .stream()
                .sorted(Comparator.comparingLong((Map.Entry<Character, Long> e)->e.getValue()).reversed())
                .map(e -> e.getKey() + ": " + (float)e.getValue()/TotalLetters*100 + "%")
                .forEach(System.out::println);
    }
    public static long Sum(Map<Character, Long> map){
        return map.values().stream().reduce(0L, (a, b) -> a+b);
    }
}
