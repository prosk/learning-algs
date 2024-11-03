//package com.mycompany.yandex.training60.prefpointers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class AlgsLearning {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new AlgsLearning().run();
        out.close();
    }

    void run() {
        int n = readInt();
        int[] a = new int[n];
        for(int i = 0; i < n; i++) {
            a[i] = readInt();
        }
        int[] b = new int[n];
        for(int i = 0; i < n; i++) {
            b[i] = readInt();
        }
        int[] p = new int[n];
        for(int i = 0; i < n; i++) {
            p[i] = readInt();
        }

        // solution
        List<AlgTask> listA = new ArrayList<>();
        List<AlgTask> listB = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            AlgTask algTask = new AlgTask(i+1, a[i], b[i]);
            listA.add(algTask);
            listB.add(algTask);
        }
        Comparator<AlgTask> compA = (alg1, alg2) -> {
            if (alg1.a != alg2.a) {
                return Integer.compare(alg2.a, alg1.a);
            }
            if (alg1.b != alg2.b) {
                return Integer.compare(alg2.b, alg1.b);
            }
            return Integer.compare(alg1.num, alg2.num);
        };
        Comparator<AlgTask> compB = (alg1, alg2) -> {
            if (alg1.b != alg2.b) {
                return Integer.compare(alg2.b, alg1.b);
            }
            if (alg1.a != alg2.a) {
                return Integer.compare(alg2.a, alg1.a);
            }
            return Integer.compare(alg1.num, alg2.num);
        };

        Collections.sort(listA, compA);
        Collections.sort(listB, compB);
        Set<Integer> selectedNums = new HashSet<>();
        int posA = 0, posB = 0;
        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            int dayP = p[i];
            if (dayP == 0) {
                // max ai
                while(posA < n && selectedNums.contains(listA.get(posA).num)) {
                    posA++;
                }
                ans.add(listA.get(posA).num);
                selectedNums.add(listA.get(posA).num);
                posA++;
            } else {
                // max bi
                while(posB < n && selectedNums.contains(listB.get(posB).num)) {
                    posB++;
                }
                ans.add(listB.get(posB).num);
                selectedNums.add(listB.get(posB).num);
                posB++;
            }
        }

        StringBuilder sb = new StringBuilder("");
        for(int k = 0; k < n; k++) {
            sb.append(ans.get(k));
            if (k < n-1) sb.append(' ');
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

    public static class AlgTask {
        int num;
        int a;
        int b;

        public AlgTask(int num, int a, int b) {
            this.num = num;
            this.a = a;
            this.b = b;
        }
    }
}