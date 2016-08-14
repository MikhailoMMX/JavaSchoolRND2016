package ru.sbt.les10_LambdaStream.HomeWork;

public class WordTrimmer {
    public static String trim(String str){
        if (str == null || "".equals(str))
            return "";
        int N = str.length();
        int start=0;
        int end = N-1;
        while(start<N && !Character.isLetterOrDigit(str.charAt(start)))
            ++start;
        if (start == N)
            return "";
        while(end>=start && !Character.isLetterOrDigit(str.charAt(end)))
            --end;
        if (start > end)
            return "";
        return str.substring(start, end+1);
    }
}
