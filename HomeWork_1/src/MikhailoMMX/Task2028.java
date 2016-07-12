package MikhailoMMX;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by MikhailoMMX on 12.07.2016.
 */
public class Task2028 {
    private static final String InputFile = "2028_in.txt";
    private static final String OutputFile = "2028_out.txt";
    private static final int SIZE = 5;
    public static void main(String[] args){
        try {
            Scanner scanner = new Scanner(Paths.get(InputFile));
            int[] array = new int[SIZE];

            int i;
            for (i = 0; i < SIZE; ++i)
                array[i] = 0;

            int n = scanner.nextInt();
            for (i = 0; i<n; ++i)
                array[scanner.nextInt()]++;

            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(OutputFile), "windows-1251"));
            for (i = 0; i<SIZE; ++i)
                if (array[i] != 0)
                    writer.write(i + " " + array[i] + "\r\n");
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getCause());
        }
    }
}
