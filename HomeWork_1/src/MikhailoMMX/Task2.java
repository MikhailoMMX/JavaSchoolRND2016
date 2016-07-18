package MikhailoMMX;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by MikhailoMMX on 17.07.2016.
 */
public class Task2 {
    public static final String inputFile = "2061_in.txt";
    public static void  main(String [] args){
        if (!Files.exists(Paths.get(inputFile))){
            System.out.println("Error: input file does not exist");
            return;
        }
        try {
            List<String> inputStrings = Files.readAllLines(Paths.get(inputFile));
            ArrayList<String> outputStrings = new ArrayList<>();
            for (String str: inputStrings)
                outputStrings.add(str.replaceAll(" ", ""));
            Collections.sort(outputStrings);
            for (String str: outputStrings)
                System.out.println(str);
        }
        catch (Exception e)
        {
            System.out.println("Exception: " + e.toString());
        }
    }
}
