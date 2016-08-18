package ru.sbt.les11_MultiThreading.HomeWork;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Worker {
    /**
     * Принимает очередное слово и сохраняет информацию о вхождениях букв в нем
     *
     * @param words список слов
     */
    public static Map<Character, Long> ConvertWordsToMap(List<String> words) {
        Map<Character, Long> result = new HashMap<>();
        for (String word : words) {
            String wordLower = word.toLowerCase();
            for (int i = 0; i < word.length(); ++i) {
                Character c = wordLower.charAt(i);
                if (Character.UnicodeBlock.of(c).equals(Character.UnicodeBlock.CYRILLIC)) {
                    if (result.containsKey(c))
                        result.replace(c, result.get(c) + 1L);
                    else
                        result.put(c, 1L);
                }
            }
        }
        return result;
    }
}
