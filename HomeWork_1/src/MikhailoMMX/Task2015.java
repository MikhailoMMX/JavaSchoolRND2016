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
public class Task2015 {
    private static final String InputFile = "2015_in.txt";
    private static final String OutputFile = "2015_out.txt";
    public static void main(String[] args){
        try {
            Scanner scanner = new Scanner(Paths.get(InputFile));
            int n = scanner.nextInt();
            boolean[] array = new boolean[n+1];

            int i;
            for (i = 2; i <= n; ++i)
                array[i] = true;

            //Решето Эратосфена
            int to = (int)Math.sqrt(n);
            for (i = 2; i<=to; ++i)
                if (array[i])
                    for(int j = 2*i; j<=n; j+=i)
                        array[j] = false;

            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(OutputFile), "windows-1251"));
            for (i = 2; i<=n; ++i)
                if (array[i])
                    writer.write(i + " ");
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
