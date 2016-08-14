package ru.sbt.les10_LambdaStream.HomeWork;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FreqDicStream {
    private static final String PATH = "..\\HomeWork_1\\inputFiles\\HW3.txt";
    private static final String PATH_OUT = "Dic.txt";

    public static void main(String[] args) throws IOException {
        Files.write(Paths.get(PATH_OUT),
            Files.readAllLines(Paths.get(PATH), Charset.forName("windows-1251"))
                 .stream()
                 .flatMap(s-> Stream.of(s.toLowerCase().split(" ")))
                 .map(WordTrimmer::trim)
                 .filter(s -> !"".equals(s))
                 .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                 .entrySet()
                 .stream()
                 .sorted(Comparator.comparing((Map.Entry<String, Long> M) -> M.getValue()).reversed())
                 .map(E->E.getKey() + ": " + E.getValue())
                 .collect(Collectors.toList()));
    }


}
