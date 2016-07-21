package ru.sbertech.lesson3.Homework;

import jdk.nashorn.internal.ir.IfNode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by MikhailoMMX on 19.07.2016.
 */
public class Task6 {
    public static final String inputFile = "HomeWork_1\\inputFiles\\HW3.txt";
    public static void  main(String [] args){
        if (!Files.exists(Paths.get(inputFile))){
            System.out.println("Error: input file does not exist");
            return;
        }
        try {
            List<String> inputStrings = Files.readAllLines(Paths.get(inputFile));

            System.out.print("Введите номера строк (1.." + inputStrings.size() + "), через пробел: ");
            String input;
            try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
                input = br.readLine();
            }
            String[] linesStr = input.split(" ");
            List<Integer> lines = new ArrayList<>();
            for (String string : linesStr)
                try {
                    int n = Integer.parseInt(string);
                    if (n<=0 || n>inputStrings.size())
                        throw  new NumberFormatException(string);
                    lines.add(n);
                } catch (NumberFormatException e) {
                    System.out.println("Строки с номером " + string + " нет в файле");
                }

            if (lines.size() != 0) {
                Collections.shuffle(lines);
                System.out.println("Строки с указанными номерами:");
                for (Integer i : lines)
                    System.out.println(i + ": " + inputStrings.get(i - 1));
            }
        }
        catch (Exception e)
        {
            System.out.println("Exception: " + e.toString());
        }
    }
}
