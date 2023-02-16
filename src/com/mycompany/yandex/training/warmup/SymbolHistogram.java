package com.mycompany.yandex.training.warmup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class SymbolHistogram {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new SymbolHistogram().run();
    }

    private void run() {
        try {
            long timeStart = System.currentTimeMillis();
            solve();
            out.close();
            long timeEnd = System.currentTimeMillis();
            System.err.println("Time(ms) = " + (timeEnd - timeStart));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void solve() {
        StringBuilder builder = new StringBuilder("");
        String currLine;
        while(true) {
            currLine = readString();
            if (currLine == null) {
                break;
            }
            builder.append(currLine);
        }
        String input = builder.toString();

        Map<Character, Integer> cntMap = new HashMap<>();

        int maxCnt = 0;
        for(int i = 0; i < input.length(); i++) {
            char currChar = input.charAt(i);
            if (currChar != ' ' && currChar != '\n') {
                int currCnt = cntMap.merge(currChar, 1, Integer::sum);
                if (currCnt > maxCnt) {
                    maxCnt = currCnt;
                }
            }
        }

        /*cntMap.forEach((k, v) -> out.printf("%c %d\n", k, v));
        out.println(maxCnt);*/

        List<Character> sortedChars = cntMap.keySet().stream()
                .sorted(Comparator.comparingInt(c -> (int) c.charValue())).collect(Collectors.toList());

        for(int i = maxCnt; i > 0; i--) {
            StringBuilder currStr = new StringBuilder("");
            for(int j = 0; j < sortedChars.size(); j++) {
                char currChar = cntMap.get(sortedChars.get(j)) >= i ? '#' : ' ';
                currStr.append(currChar);
            }
            out.println(currStr);
        }
        sortedChars.forEach((c) -> out.print(c));

    }

    private String readString() {
        while (!tok.hasMoreTokens()) {
            String nextLine = readLine();
            if (null == nextLine) return null;
            tok = new StringTokenizer(nextLine);
        }

        return tok.nextToken();
    }

    private String readLine() {
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
