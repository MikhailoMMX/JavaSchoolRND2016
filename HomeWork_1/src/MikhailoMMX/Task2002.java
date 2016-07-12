package MikhailoMMX;
import java.nio.file.*;
import java.io.*;
import java.util.*;

public class Task2002 {
    private static final String InputFile = "2002_in.txt";
    private static final String OutputFile = "2002_out.txt";
    public static void main(String[] args) {
        try {
            int sum = 0;
            Scanner scanner = new Scanner(Paths.get(InputFile));
            int n = scanner.nextInt();
            for (int i = 0; i < n; ++i)
                sum += scanner.nextInt();
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(OutputFile), "windows-1251"));
            writer.write(Integer.toString(sum));
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
