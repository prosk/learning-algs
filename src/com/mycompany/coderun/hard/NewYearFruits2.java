package com.mycompany.coderun.hard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

// https://coderun.yandex.ru/selections/new-year-adventures/problems/new-year-fruits-2/description
public class NewYearFruits2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new NewYearFruits2().run();
        out.close();
    }

    void run() {
        int k = readInt();
        int n = 2*k - 1;
        FruitBox[] boxesM = new FruitBox[n];
        FruitBox[] boxesA = new FruitBox[n];
        long sumOfM = 0, sumOfA = 0;
        for(int i = 1; i <= n; i++) {
            int mCnt = readInt();
            int aCnt = readInt();
            FruitBox fb = new FruitBox();
            fb.m = mCnt;
            fb.a = aCnt;
            fb.ind = i;
            boxesM[i-1] = fb;
            boxesA[i-1] = fb;
            sumOfM += mCnt;
            sumOfA += aCnt;
        }
        long halfOfM = (sumOfM+1)/2, halfOfA = (sumOfA+1)/2;
        Arrays.sort(boxesM, (b1, b2) -> b1.m == b2.m ? Integer.compare(b2.a, b1.a) : Integer.compare(b2.m, b1.m));
        Arrays.sort(boxesA, (b1, b2) -> b1.a == b2.a ? Integer.compare(b2.m, b1.m) : Integer.compare(b2.a, b1.a));

        //System.out.println(Arrays.toString(boxesM));
        //System.out.println(Arrays.toString(boxesA));

        int indM = 0, indA = 0;
        Set<Integer> selectedIdx = new HashSet<>();
        while(selectedIdx.size() < k) {
            if (halfOfM > halfOfA) {
                // getting mandarin
                while(selectedIdx.contains(boxesM[indM].ind)) indM++;
                halfOfM -= boxesM[indM].m;
                halfOfA -= boxesM[indM].a;
                selectedIdx.add(boxesM[indM].ind);
                indM++;
            } else {
                // getting orange
                while(selectedIdx.contains(boxesA[indA].ind)) indA++;
                halfOfM -= boxesA[indA].m;
                halfOfA -= boxesA[indA].a;
                selectedIdx.add(boxesA[indA].ind);
                indA++;
            }
        }
        StringBuilder sb = new StringBuilder();
        int j = 0;
        for(int ind: selectedIdx) {
            if (j > 0) sb.append(' ');
            sb.append(ind);
            j++;
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

    public static class FruitBox {
        int m;
        int a;
        int ind;

        @Override
        public String toString() {
            return "FruitBox{" +
                "m=" + m +
                ", a=" + a +
                ", ind=" + ind +
                '}';
        }
    }
}