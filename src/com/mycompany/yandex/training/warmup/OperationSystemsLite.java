package com.mycompany.yandex.training.warmup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;


// В разборе задач показано, как эту же задачу решить не в Lite-варианте, когда N может быть велико
// за NlogN, с использованием, сбалансированного дерева, например, в Java можно взять TreeMap
// надо попробовать применить этот альтернативный подход
// см. https://www.youtube.com/watch?v=O26-2-94BDk&t=159s
public class OperationSystemsLite {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new OperationSystemsLite().run();
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
        // Ввод данных
        int sectorsCount = readInt();
        int segmentsCount = readInt();

        if (segmentsCount == 0) {
            out.println("0");
            return;
        }

        int[][] segments = new int[segmentsCount][2];
        for(int i = 0; i < segmentsCount; i++) {
            segments[i] = new int[2];
            segments[i][0] = readInt();
            segments[i][1] = readInt();
        }

        int res = 0;
        // brute force
        for(int i = 0; i < segmentsCount; i++) {
            boolean isIntersectionFound = false;
            for(int j = i+1; j < segmentsCount; j++) {
                if (isIntersected(segments, i, j)) {
                    isIntersectionFound = true;
                    break;
                }
            }
            if (!isIntersectionFound) {
                res++;
            }
        }
        out.println(res);
    }

    // Проверку пересечения отрезков можно упростить
    // если есть 2 отрезка [a, b] и [c, d]  (a <= b, c <= d), то отрезки пересекаются, если
    // (a <= d && c <= b)
    private boolean isIntersected(int[][] segments, int i, int j) {
        return (segments[j][0] >= segments[i][0] && segments[j][0] <= segments[i][1]) ||
                (segments[i][0] >= segments[j][0] && segments[i][0] <= segments[j][1]);
    }

    private int[] readIntArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; ++i) a[i] = readInt();
        return a;
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
