package com.mycompany.codeforces.random.div2betaround4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MysteriousPresent {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new MysteriousPresent().run();
        out.close();
    }

    void run() {
        int n = readInt();
        int w = readInt();
        int h = readInt();
        List<Envelope> envList = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            int wi = readInt();
            int hi = readInt();
            if (w < wi && h < hi) {
                Envelope e = new Envelope();
                e.num = i + 1;
                e.w = wi;
                e.h = hi;
                e.sum = e.w + e.h;
                envList.add(e);
            }
        }
        if (envList.isEmpty()) {
            out.println("0");
            return;
        }
        // список конвертов куда помещяется открытка
        envList.sort(null);
        int cnt = envList.size();
        int[] dp = new int[cnt]; // dp[i] = максимальное кол-во конвертов включенных в i-ый
        int[] prev = new int[cnt];
        for(int i = 0; i < cnt; i++) prev[i] = -1;

        int maxInd = 0, maxLen = 0;
        for(int i = 0; i < cnt; i++) {
            // calc of dp[i]
            for(int j = 0; j < i; j++) {
                if (envList.get(i).w > envList.get(j).w &&
                        envList.get(i).h > envList.get(j).h &&
                        (dp[j] + 1) > dp[i]) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }
            if (dp[i] > maxLen) {
                maxInd = i;
                maxLen = dp[i];
            }
        }
        // output
        out.println(maxLen+1);
        StringBuilder sb = new StringBuilder("");
        for(int i = maxInd; i != -1; i = prev[i]) {
            if (i != maxInd) {
                sb.insert(0, ' ');
            }
            sb.insert(0, envList.get(i).num);
        }
        out.println(sb);
    }

    int readInt() {
        return Integer.parseInt(readString());
    }

    String readString() {
        while (!tok.hasMoreTokens()) {
            String nextLine = readLine();
            if (null == nextLine) return null;
            tok = new StringTokenizer(nextLine);
        }

        return tok.nextToken();
    }

    String readLine() {
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static class Envelope implements Comparable<Envelope> {
        public int num;
        public int w;
        public int h;
        public int sum;

        @Override
        public int compareTo(Envelope o) {
            return Integer.compare(this.sum, o.sum);
        }
    }
}

