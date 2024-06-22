
package org.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AnagramExample {
    private String[] str;

    public AnagramExample(String[] str) {
        this.str = str;
    }

    public Map<String, Integer> test() {
        String[] lowerCaseWords = convertArrayToLowercase(str);
        System.out.println("Lowercase array: " + Arrays.toString(lowerCaseWords));

        String[] sortedWords = sortEachWord(lowerCaseWords);
        System.out.println("Sorted array: " + Arrays.toString(sortedWords));

        return createFrequencyMap(sortedWords);
    }

    public static String[] convertArrayToLowercase(String[] words) {
        String[] lowercaseWords = new String[words.length];
        for (int i = 0; i < words.length; i++) {
            lowercaseWords[i] = words[i].toLowerCase();
        }
        return lowercaseWords;
    }

    public static String[] sortEachWord(String[] words) {
        String[] sortedWords = new String[words.length];
        for (int i = 0; i < words.length; i++) {
            char[] charArray = words[i].toCharArray();
            Arrays.sort(charArray);
            sortedWords[i] = new String(charArray);
        }
        return sortedWords;
    }

    public static Map<String, Integer> createFrequencyMap(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        return map;
    }
}
