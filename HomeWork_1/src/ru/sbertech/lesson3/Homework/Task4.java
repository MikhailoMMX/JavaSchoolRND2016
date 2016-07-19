package ru.sbertech.lesson3.Homework;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by MikhailoMMX on 19.07.2016.
 */
public class Task4 {
    public static final String inputFile = "HomeWork_1\\inputFiles\\HW3.txt";
    public static void  main(String [] args){
        if (!Files.exists(Paths.get(inputFile))){
            System.out.println("Error: input file does not exist");
            return;
        }
        try {
            List<String> inputStrings = Files.readAllLines(Paths.get(inputFile));

            System.out.println("Lines number: " + inputStrings.size());

            for (int i = inputStrings.size()-1; i>=0; --i)
                System.out.println(inputStrings.get(i));
        }
        catch (Exception e)
        {
            System.out.println("Exception: " + e.toString());
        }
    }
}
