package com.mycompany.edaacademy.fullcourse.graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class TreeMaxEdgesSet {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new TreeMaxEdgesSet().run();
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
        int n = readInt();
        int m = n - 1;

        // graph ds
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        // getting edges (u, v) and filling adj
        for(int i = 1; i <= m; i++) {
            int u = readInt();
            int v = readInt();
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        boolean[] visited = new boolean[n+1];
        int[] dp_with_root = new int[n+1];
        int[] dp_without_root = new int[n+1];

        dfs(1, adj, visited, dp_with_root, dp_without_root);
        out.println(Math.max(dp_with_root[1], dp_without_root[1]));
    }

    private void dfs(int v, List<List<Integer>> adj, boolean[] visited,
                     int[] dp_with_root, int[] dp_without_root) {
        visited[v] = true;
        List<Integer> calculatedChildes = new ArrayList<>();
        Map<Integer, Integer> optSums = new HashMap<>();
        int maxDiff = Integer.MIN_VALUE, currDiff, uMaxDiff = -1;
        for(int u: adj.get(v)) {
            if (!visited[u]) {
                dfs(u, adj, visited, dp_with_root, dp_without_root);
                int optSum = Math.max(dp_with_root[u], dp_without_root[u]);
                dp_without_root[v] += optSum;
                calculatedChildes.add(u);
                optSums.put(u, optSum);
                currDiff = dp_without_root[u] - optSum;
                if (currDiff > maxDiff) {
                    maxDiff = currDiff;
                    uMaxDiff = u;
                }
            }
        }
        if (uMaxDiff != -1) {
            // calculation of dp_with_root[v]
            dp_with_root[v] = 1 + dp_without_root[uMaxDiff] + (dp_without_root[v] - optSums.get(uMaxDiff));
        }

        // calculation of dp_with_root[v]
        /*for(int u: calculatedChildes) {
            // select edge (v, u)
            int sum = 1 + dp_without_root[u] + (dp_without_root[v] - optSums.get(u));
            dp_with_root[v] = Math.max(dp_with_root[v], sum);
        }*/
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