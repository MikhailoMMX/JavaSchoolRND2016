package ru.sbertech.lesson3.Homework;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by MikhailoMMX on 19.07.2016.
 */
public class Task1_2 {
    private static class LengthComparator implements Comparator<String>{
        @Override
        public int compare(String o1, String o2) {
            if (o1.length() != o2.length())
                return o1.length() - o2.length();
            return o1.compareTo(o2);
        }
    }

    public static final String inputFile = "HomeWork_1\\inputFiles\\HW3.txt";
    public static void  main(String [] args){
        if (!Files.exists(Paths.get(inputFile))){
            System.out.println("Error: input file does not exist");
            return;
        }
        try {
            List<String> inputStrings = Files.readAllLines(Paths.get(inputFile));

            HashSet<String> dictionary = new HashSet<>();
            for(String str: inputStrings)
                dictionary.addAll(Arrays.asList(str.split(" ")));

            int Size = dictionary.size();
            System.out.println("Distinct words: " + Size);

            String[] dictArray = dictionary.toArray(new String[Size]);

            Arrays.sort(dictArray, new LengthComparator());

            System.out.println("Sorted array: ");
            for(String str : dictArray)
                System.out.println(str);
        }
        catch (Exception e)
        {
            System.out.println("Exception: " + e.toString());
        }
    }
}
