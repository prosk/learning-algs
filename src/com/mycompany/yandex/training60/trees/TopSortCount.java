package com.mycompany.yandex.training60.trees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TopSortCount {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    List<List<Integer>> adj = new ArrayList<>();

    static long MODULO = (long) 1e9 + 7;

    static int SIZE = 0;
    static int[] fact;

    int[] dp;
    int[] subtreeSizes;
    int N;

    int[] inDegree;

    public static void main(String[] args) {
        new TopSortCount().run();
        out.close();
    }

    void run() {
        N = readInt();
        for (int i = 0; i <= N; i++) {
            adj.add(new ArrayList<>());
        }
        // getting oriented edges (u, v) and filling adj
        inDegree = new int[N+1];
        for (int i = 1; i <= N - 1; i++) {
            int from = readInt();
            int to = readInt();
            addEdge(from, to);
        }
        // solve
        if (N == 1) {
            out.println(1);
            return;
        }
        // precalc factorials
        SIZE = N + 2;
        fact = new int[SIZE + 1];
        preFactorial();

        // adding fake node
        int rootNum = 0;
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                addEdge(rootNum, i);
            }
        }
        // dfs
        dp = new int[N + 1];
        subtreeSizes = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            dp[i] = 1;
        }
        boolean[] visited = new boolean[N+1];
        dfsFromLeaves(rootNum, visited);
        // print answer
        out.println(dp[rootNum]);
    }

    private void dfsFromLeaves(int fromNode, boolean[] visited) {
        visited[fromNode] = true;
        int adjCnt = adj.get(fromNode).size();
        dp[fromNode] = 1;
        subtreeSizes[fromNode] = 1;
        int childSizesFactorialMult = 1;
        for (int i = 0; i < adjCnt; i++) {
            int childNode = adj.get(fromNode).get(i);
            if (!visited[childNode]) {
                dfsFromLeaves(childNode, visited);
            }
            subtreeSizes[fromNode] += subtreeSizes[childNode];
            dp[fromNode] = mult(dp[fromNode], dp[childNode]);
            childSizesFactorialMult = mult(childSizesFactorialMult, getFactorial(subtreeSizes[childNode]));
        }
        if (fromNode == 0) {
            subtreeSizes[fromNode] = N + 1;
        }
        int allSubtreesSizeFactorial = getFactorial(subtreeSizes[fromNode] - 1);
        dp[fromNode] = mult(dp[fromNode], allSubtreesSizeFactorial);
        dp[fromNode] = mult(dp[fromNode], inv(childSizesFactorialMult));
    }

    private void addEdge(int from, int to) {
        adj.get(from).add(to);
        inDegree[to]++;
    }

    public static long inv(long a) {
        return powerMod(a, MODULO - 2);
    }

    public static long powerMod(long a, long b) {
        long ans = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                ans *= a;
                ans %= MODULO;
            }
            a *= a;
            a %= MODULO;
            b >>= 1;
        }
        return ans;
    }

    private static int mult(long a, long b) {
        return (int) ((a * b) % MODULO);
    }

    public static void preFactorial() {
        fact[0] = 1;
        for (int i = 1; i <= SIZE; i++) {
            fact[i] = mult(i, fact[i - 1]);
        }
    }

    public static int getFactorial(int a) {
        if (a <= SIZE) {
            return fact[a];
        }
        int res = fact[SIZE];
        for (int i = SIZE + 1; i <= a; i++) {
            res = mult(i, res);
        }
        return res;
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
}