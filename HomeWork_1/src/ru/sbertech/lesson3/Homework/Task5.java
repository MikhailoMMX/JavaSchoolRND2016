package ru.sbertech.lesson3.Homework;

import javax.swing.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by MikhailoMMX on 19.07.2016.
 */
public class Task5 {
    public static final String inputFile = "HomeWork_1\\inputFiles\\HW3.txt";
    private static class backwardIterator implements Iterator<String>{
        private int pos;
        private List<String> list;
        public backwardIterator(List<String> list){
            this.list = list;
            pos = list.size()-1;
        }
        @Override
        public boolean hasNext() {
            return pos >= 0;
        }

        @Override
        public String next() {
            return list.get(pos--);
        }
    }
    public static void  main(String [] args){
        if (!Files.exists(Paths.get(inputFile))){
            System.out.println("Error: input file does not exist");
            return;
        }
        try {
            List<String> inputStrings = Files.readAllLines(Paths.get(inputFile));

            System.out.println("Lines number: " + inputStrings.size());

            Iterator<String> iterator = new backwardIterator(inputStrings);
            while (iterator.hasNext())
                System.out.println(iterator.next().toString());
        }
        catch (Exception e)
        {
            System.out.println("Exception: " + e.toString());
        }
    }
}
