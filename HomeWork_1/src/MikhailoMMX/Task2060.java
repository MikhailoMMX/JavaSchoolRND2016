package MikhailoMMX;

import javafx.util.Pair;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by MikhailoMMX on 12.07.2016.
 */
public class Task2060 {
    private static final String InputFile = "2060_in.txt";
    private static final String OutputFile = "2060_out.txt";
    public static void main(String[] args){
        try {
            //Ключ - имя ученика, значение - пара: сумма и количество оценок
            Hashtable<String, Pair<Integer, Integer>> hashtable = new Hashtable<String, Pair<Integer, Integer>>();

            Scanner scanner = new Scanner(Paths.get(InputFile));
            int n = scanner.nextInt();
            scanner.nextLine();
            for (int i = 0; i < n; ++i){
                String[] line = scanner.nextLine().split(" ");
                if (line.length <2)
                    throw new Exception("Incorrect input file");
                if (!hashtable.containsKey(line[0]))
                    hashtable.put(line[0], new Pair<Integer, Integer>(Integer.parseInt(line[1]),1));
                else {
                    Pair<Integer, Integer> oldValue = hashtable.get(line[0]);
                    Pair<Integer, Integer> newValue = new Pair<Integer, Integer>(oldValue.getKey()+Integer.parseInt(line[1]), oldValue.getValue()+1);
                    hashtable.replace(line[0], newValue);
                }
            }

            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(OutputFile), "windows-1251"));
            writeHashTable(hashtable, writer);
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void writeHashTable(Hashtable<String, Pair<Integer, Integer>> hashtable, Writer writer) throws IOException {
        ArrayList<String> sortedKeys = new ArrayList<String>(hashtable.keySet());
        Collections.sort(sortedKeys);
        for (String n : sortedKeys) {
            writer.write(n + " ");
            writer.write(String.valueOf(Math.round(Math.floor(hashtable.get(n).getKey() / hashtable.get(n).getValue()))) + System.getProperty("line.separator"));
        }
    }
}
