package MikhailoMMX;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by MikhailoMMX on 12.07.2016.
 */
public class Task2041 {
    private static final String InputFile = "2041_in.txt";
    private static final String OutputFile = "2041_out.txt";
    public static void main(String[] args){
        try {
            String input = Files.readAllLines(Paths.get(InputFile)).get(0);

            boolean flag = isPalindrom(input);
            if (!flag)
                for (int i = 1; i< input.length(); ++i)
                {
                    String s1 = input.substring(0, i);
                    String s2 = input.substring(i);
                    if (isPalindrom(s1) && isPalindrom(s2)){
                        flag = true;
                        break;
                    }
                }

            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(OutputFile), "windows-1251"));
            writer.write(flag ? "YES" : "NO");
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private static boolean isPalindrom(String str){
        int mid = str.length()/2;
        for(int i = 0; i< mid; ++i)
            if (str.charAt(i) != str.charAt(str.length()-1 - i ))
                return false;
        return true;
    }
}
