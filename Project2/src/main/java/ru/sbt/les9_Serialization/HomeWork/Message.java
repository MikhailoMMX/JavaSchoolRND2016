package ru.sbt.les9_Serialization.HomeWork;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Message implements Serializable{
    final static long serialVersionUID = 0L;
    public String From;
    public String To;
    private String FileName;
    private byte[] data;

    public Message(String from, String to, String fileName) throws IOException {
        From = from;
        To = to;
        if (fileName == null || fileName.equals(""))
            return;
        ReadFromFile(fileName);
    }

    public void ReadFromFile(String fileName) throws IOException {
        if (!Files.exists(Paths.get(fileName)))
            throw new IOException("File does not exist");
        data = Files.readAllBytes(Paths.get(fileName));
        FileName = fileName;
    }

    public String getFileName() {
        return FileName;
    }

    public void WriteToFile(String fileName) throws IOException {
//        if (Files.exists(Paths.get(fileName)))
//            throw new IOException("File already exists");
        Files.write(Paths.get(fileName), data);
    }
}
