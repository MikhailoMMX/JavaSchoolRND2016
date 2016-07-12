package MikhailoMMX;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MikhailoMMX on 12.07.2016.
 */
public class Task2042 {
    private static final String InputFile = "2042_in.txt";
    private static final String OutputFile = "2042_out.txt";
    public static void main(String[] args){
        try {
            List<String> input = Files.readAllLines(Paths.get(InputFile));
            assert (input.size()>=2);
            String string = input.get(0);
            String pattern = input.get(1);

            ArrayList<Integer> result = new ArrayList<Integer>();

            if (string.length()>= pattern.length())
                for (int i = 0; i<= string.length()-pattern.length(); ++i){
                    boolean flag = true;
                    for (int j = 0; j< pattern.length(); ++j)
                        if (pattern.charAt(j) != '?' && string.charAt(i+j) != pattern.charAt(j) ){
                            flag = false;
                            break;
                        }
                    if (flag)
                        result.add(i);
                }

            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(OutputFile), "windows-1251"));
            for (int i = 0; i< result.size(); ++i)
                writer.write(result.get(i)+1 + " ");
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
