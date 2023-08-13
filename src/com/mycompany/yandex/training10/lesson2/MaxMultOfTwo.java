package com.mycompany.yandex.training10.lesson2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

public class MaxMultOfTwo {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new MaxMultOfTwo().run();
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
        List<Integer> arr = readIntList();
        int len = arr.size();

        int max1 = Math.max(arr.get(0), arr.get(1));
        int max2 = Math.min(arr.get(0), arr.get(1));
        int min1 = max2, min2 = max1;

        int curr;
        for(int i = 2; i < len; i++) {
            curr = arr.get(i);
            if (curr > max1) {
                max2 = max1;
                max1 = curr;
            } else if (curr > max2) {
                max2 = curr;
            }

            if (curr < min1) {
                min2 = min1;
                min1 = curr;
            } else if (curr < min2) {
                min2 = curr;
            }
        }
        // out.printf("max1 = %d max2 = %d min1 = %d min2 = %d", max1, max2, min1, min2);

        long max12Mult = (long) max1*max2;
        long min12Mult = (long) min1*min2;

        if (max12Mult > min12Mult) {
            out.println(max2 + " " + max1);
        } else {
            out.println(min1 + " " + min2);
        }

        /*int ans;
        int max12Mult = max1*max2;
        int min12Mult = min1*min2;
        if (max12Mult > 0 && min12Mult > 0) {
            ans = Math.max(max12Mult, min12Mult);
        } else if (max12Mult > 0 && min12Mult < 0) {
            ans = max12Mult;
        } else if (max12Mult < 0) {
            ans = min12Mult;
        } else if (max12Mult == 0 && min12Mult == 0) {
            ans = 0;
        } else if (max12Mult == 0 && min12Mult != 0) {
            ans = Math.max(max12Mult, min12Mult);
        } else if (max12Mult != 0 && min12Mult == 0) {
            ans = Math.max(max12Mult, min12Mult);
        }*/

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


