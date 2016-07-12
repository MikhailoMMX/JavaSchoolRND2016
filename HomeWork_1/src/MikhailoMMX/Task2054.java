package MikhailoMMX;
import java.io.*;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by MikhailoMMX on 12.07.2016.
 */
public class Task2054 {
    private static final String InputFile = "2054_in.txt";
    private static final String OutputFile = "2054_out.txt";
    public static void main(String[] args){
        try {
            Hashtable<Integer, HashSet<Integer>> hashtable = new Hashtable<Integer, HashSet<Integer>>();

            Scanner scanner = new Scanner(Paths.get(InputFile));
            int n = scanner.nextInt();
            for (int i = 1; i <= n; ++i)
                processSequence(readSequence(scanner), hashtable, i);

            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(OutputFile), "windows-1251"));
            writeHashTable(hashtable, writer);
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private static ArrayList<Integer> readSequence(Scanner scanner){
        ArrayList<Integer> result = new ArrayList<Integer>();
        int n = scanner.nextInt();
        for (int i = 0; i< n; ++i)
            result.add(scanner.nextInt());
        return result;
    }
    private static void processSequence(ArrayList<Integer> arrayList,  Hashtable<Integer, HashSet<Integer>> hashtable, int n) {
        for (Integer i : arrayList){
            if (hashtable.containsKey(i))
                hashtable.get(i).add(n);
            else
            {
                HashSet<Integer> hashSet = new HashSet<Integer>();
                hashSet.add(n);
                hashtable.put(i, hashSet);
            }
        }
    }
    private static void writeHashTable(Hashtable<Integer, HashSet<Integer>> hashtable, Writer writer) throws IOException {
        ArrayList<Integer> sortedKeys = new ArrayList<Integer>(hashtable.keySet());
        Collections.sort(sortedKeys);
        for (Integer n : sortedKeys) {
            writer.write(n.toString() + " ");
            ArrayList<Integer> sortedList = new ArrayList<Integer>(hashtable.get(n));
            Collections.sort(sortedList);
            for (Integer i : sortedList)
                writer.write(i.toString() + " ");
            writer.write("\r\n");
        }
    }
}
