package ru.sbertech.lesson5.HomeWork;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class ContentReader {
    private static void readContent(String url) throws IOException {
        try(InputStream is = new URL(url).openStream()) {
            Scanner scanner = new Scanner(is, "UTF-8");
            scanner.useDelimiter("\\A");
            while (scanner.hasNext())
                System.out.println(scanner.next());
        }
    }

    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String inputLine;
            System.out.print("Enter URL to display (or \"exit\" to quit application): ");
            while (!(inputLine = br.readLine()).equalsIgnoreCase("exit")) {
                try {
                    readContent(inputLine);
                } catch (MalformedURLException e) {
                    System.out.println("Wrong url format");
                } catch(FileNotFoundException e) {
                    System.out.println("Page does not exist: " + e.getMessage());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                System.out.print("Enter URL to display (or \"exit\" to quit application): ");
            }
        }
        catch (IOException e){
            System.out.println(e.toString());
        }
    }
}
