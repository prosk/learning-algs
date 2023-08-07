package com.mycompany.yandex.training10.lesson2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

public class AbcSortedList {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new AbcSortedList().run();
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
        List<Integer> list = readIntList();
        String ans = "YES";
        int prev = list.get(0);
        for(int i = 1; i < list.size(); i++) {
            int curr = list.get(i);
            if (curr <= prev) {
                ans = "NO";
                break;
            }
            prev = curr;
        }
        out.println(ans);
    }

    List <Integer> readIntList() {
        List<Integer> resList = new ArrayList<>();
        String currStr = readString();
        while(currStr != null) {
            resList.add(Integer.valueOf(currStr));
            currStr = readString();
        }
        return resList;
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
