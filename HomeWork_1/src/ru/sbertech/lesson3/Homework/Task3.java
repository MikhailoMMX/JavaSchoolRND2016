package ru.sbertech.lesson3.Homework;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by MikhailoMMX on 19.07.2016.
 */
public class Task3 {
    public static final String inputFile = "HomeWork_1\\inputFiles\\HW3.txt";
    public static void  main(String [] args){
        if (!Files.exists(Paths.get(inputFile))){
            System.out.println("Error: input file does not exist");
            return;
        }
        try {
            List<String> inputStrings = Files.readAllLines(Paths.get(inputFile));

            HashMap<String, Integer> freqDict = new HashMap<>();
            for(String str: inputStrings)
                for (String word: str.split(" "))
                    if (!freqDict.containsKey(word))
                        freqDict.put(word, 1);
                    else
                        freqDict.replace(word, freqDict.get(word)+1);

            int Size = freqDict.size();
            System.out.println("Distinct words: " + Size);

            Iterator<Map.Entry<String, Integer>> iterator = freqDict.entrySet().iterator();

            System.out.println("Frequency dictionary: ");
            while (iterator.hasNext())
                System.out.println(iterator.next().toString());
        }
        catch (Exception e)
        {
            System.out.println("Exception: " + e.toString());
        }
    }
}
