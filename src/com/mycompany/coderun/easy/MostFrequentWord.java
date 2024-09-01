package com.mycompany.coderun.easy;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// 50. Самое частое слово
public class MostFrequentWord {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        //String[] words = reader.lines().collect(Collectors.joining("")).split(" ");

        List<String> words = reader.lines()
                .flatMap(line -> Stream.of(line.split(" "))).collect(Collectors.toList());


        Map<String, Integer> freqs = new HashMap<>();
        int maxFreq = 0;
        String ans = "";
        for(String word: words) {
            int freq = freqs.merge(word, 1, Integer::sum);
            if (freq > maxFreq || (freq == maxFreq && word.compareTo(ans) < 0)) {
                maxFreq = freq;
                ans = word;
            }
        }
        writer.write(ans);

        reader.close();
        writer.close();
    }
}
