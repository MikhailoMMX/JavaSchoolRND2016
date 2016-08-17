package ru.sbt.les11_MultiThreading.HomeWork;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Shuffler {
    private static String PATH_IN = "Input\\EragonFull.txt";
    private static String PATH_OUT = "Input\\EragonFullShuffle.txt";

    public static <T> List<T> MyShuffle(List<T> in){
        Collections.shuffle(in);
        return in;
    }

    public static void main(String[] args) throws IOException {
        Random r = new Random();
        Files.write(Paths.get(PATH_OUT),
                Files.readAllLines(Paths.get(PATH_IN), Charset.forName("windows-1251"))
                        .stream()
                        .map(s -> MyShuffle(Arrays.asList(s.split(""))).stream().collect(Collectors.joining()))
                        .collect(Collectors.toList())
        );
    }


}
