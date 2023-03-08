package com.mycompany.yandex.training.dp.oneparam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class Carnations {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new Carnations().run();
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
        int carnCnt = readInt();
        int[] coords = new int[carnCnt];

        for(int i = 0; i < carnCnt; i++) {
            coords[i] = readInt();
        }
        Arrays.sort(coords);

        if (carnCnt <= 3) {
            int sumLen = 0;
            for(int j = 1; j < carnCnt; j++) {
                sumLen += coords[j] - coords[j-1];
            }
            out.println(sumLen);
            return;
        }

        // dp[i] - минимальная длина ниток, которая заканчивается i-м гвоздиком, у которого слева есть нитка
        int dp[] = new int[carnCnt];

        // обозначим 0 - нет нитки, 1 - есть нитка
        // тогда, если справа мы хотим добавить 1, т.е. еще один последний гвоздик,
        // у которого слева есть нитка длиной len,
        // то слева от этой единицы возможны варианты:
        // a) 0 0 | 1  - вариант невозможен, т.к. не может быть два 0 подряд (получается гвоздик без ниток)
        // б) 1 0 | 1  - вариант нужно рассмотреть, это dp[i-2] + len
        // в) 0 1 | 1  - варианты в) и г) не нужно рассматривать отдельно, они оба сводятся к dp[i-1] + len
        // г) 1 1 | 1
        // Итого получаем, что dp[i] = min(dp[i-2] + len, dp[i-1] + len)
        // основная фишка понять, что в) и г) не требуют отдельного рассмотрения, т.к. у нас по определению уже
        // dp[i-1] - это минимальная длина ниток, которая заканчивается (i-1)-м гвоздиком
        // корректность формулы доказывается тем, что мы рассмотрели все возможные варианты

        dp[1] = coords[1] - coords[0];
        dp[2] = (coords[1] - coords[0]) + (coords[2] - coords[1]);

        for(int i = 3; i < carnCnt; i++) {
            int len = coords[i] - coords[i-1];
            dp[i] = Math.min(dp[i-2] + len, dp[i-1] + len);
        }
        out.println(dp[carnCnt - 1]);
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
