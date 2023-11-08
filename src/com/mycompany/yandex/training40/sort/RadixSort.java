package com.mycompany.yandex.training40.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class RadixSort {
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final PrintWriter out = new PrintWriter(System.out);
    private StringTokenizer tok = new StringTokenizer("");

    private final Random rand = new Random();

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new RadixSort().run();
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

    private int digitCharToInt(char digit) {
        return (int) digit - 48;
    }

    private void sortByDigit(String a[], String[] sorted, int digitPos, int phaseNumber) {
        int[] count = new int[10];
        int[] pos = new int[10];
        for(int i = 0; i < a.length; i++) {
            char digitChar = a[i].charAt(digitPos);
            int digit = digitCharToInt(digitChar);
            count[digit]++;
        }
        // 00111333344
        // count: [0] = 2 [1] = 3 [2] = 0 [3] = 4 [4] = 2
        // pos:   [0] = 0 [1] = 2 [2] = 5 [3] = 5 [4] = 9
        for(int i = 1; i <= 9; i++) {
            pos[i] = count[i-1] + pos[i-1];
        }

        Map<Integer, List<String>> buckets = new HashMap<>();
        for(int i = 0; i <= 9; i++)
            buckets.put(i, new ArrayList<>());

        for(int i = 0; i < a.length; i++) {
            char digitChar = a[i].charAt(digitPos);
            int digit = digitCharToInt(digitChar);
            buckets.get(digit).add(a[i]);

            int currPos = pos[digit];
            sorted[currPos] = a[i];
            pos[digit]++;
        }

        out.println("Phase " + phaseNumber);
        for(int i = 0; i <= 9; i++) {
            String prefix = String.format("Bucket %d: ", i);
            StringBuilder elems = new StringBuilder();
            for(int j = 0; j < buckets.get(i).size(); j++) {
                elems.append(buckets.get(i).get(j));
                if (j < buckets.get(i).size()-1) elems.append(", ");
            }
            String content = buckets.get(i).isEmpty() ? "empty" : elems.toString();
            out.println(prefix + content);
        }
        out.println("**********");
    }

    private String[] sort(String[] a) {
        int len = a[0].length();
        String[] b = new String[a.length];
        boolean changeArrays = false;
        int phaseNumber = 1;
        for(int i = len-1; i >= 0; i--) {
            sortByDigit(changeArrays ? b : a, changeArrays ? a : b, i, phaseNumber++);
            changeArrays = !changeArrays;
        }
        return changeArrays ? b : a;
    }

    private void solve() {
        int n = readInt();

        String[] arr = new String[n];
        for(int i = 0; i < n; i++) {
            arr[i] = readString();
        }

        printArray(arr, "Initial array:");
        out.println("**********");
        String[] sorted = sort(arr);
        printArray(sorted, "Sorted array:");
    }

    void printArray(String[] arr, String header) {
        out.println(header);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) sb.append(", ");
        }
        out.println(sb);
    }

    private int readInt() {
        return Integer.parseInt(readString());
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
