package MikhailoMMX;

import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by MikhailoMMX on 12.07.2016.
 */
public class Task2027 {
    private static final String InputFile = "2027_in.txt";
    private static final String OutputFile = "2027_out.txt";
    public static void main(String[] args){
        try {
            Scanner scanner = new Scanner(Paths.get(InputFile));
            int n = scanner.nextInt();
            int[] array = new int[n];
            int i;
            for (i = 0; i < n; ++i)
                array[i] = scanner.nextInt();

            for (i = 0; i<2; ++i){
                int from = scanner.nextInt();
                int to = scanner.nextInt();
                reverseSubArray(array, from-1, to-1);
            }

            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(OutputFile), "windows-1251"));
            for (i = 0; i<n; ++i)
                writer.write(array[i] + " ");
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private static void reverseSubArray(int[] array,  int from, int to) throws Exception {
        if (from <0 || to <0 || from >= array.length || to >= array.length)
            throw new Exception("Index out of range");
        int mid = from + (to - from)/2;
        for(int i = from; i<= mid; ++i)
        {
            int ind2 = to - (i-from);
            int t = array[i];
            array[i] = array[ind2];
            array[ind2] = t;
        }
    }
}
