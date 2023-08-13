package com.mycompany.yandex.training10.lesson2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class SequenceType {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new SequenceType().run();
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
        int size = list.size();
        int eqCnt = 0, lessCnt = 0, moreCnt = 0;

        int prev = list.get(0);
        for(int i = 1; i < size; i++) {
            int curr = list.get(i);
            if (curr < prev) {
                lessCnt++;
            } else if (curr > prev) {
                moreCnt++;
            } else {
                eqCnt++;
            }
            prev = curr;
        }

        if (eqCnt == size-1 && lessCnt == 0 && moreCnt == 0) {
            out.println("CONSTANT");
        } else if (eqCnt == 0 && lessCnt == 0 && moreCnt == size-1) {
            out.println("ASCENDING");
        } else if (eqCnt == 0 && lessCnt == size-1 && moreCnt == 0) {
            out.println("DESCENDING");
        } else if ((eqCnt + moreCnt) == size-1 && lessCnt == 0) {
            out.println("WEAKLY ASCENDING");
        } else if ((eqCnt + lessCnt) == size-1 && moreCnt == 0) {
            out.println("WEAKLY DESCENDING");
        } else {
            out.println("RANDOM");
        }
    }

    List <Integer> readIntList() {
        List<Integer> resList = new ArrayList<>();
        String currStr = readLine();
        while(currStr != null) {
            int val = Integer.valueOf(currStr);
            if (val == -2_000_000_000) {
                break;
            }
            resList.add(val);
            currStr = readLine();
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
