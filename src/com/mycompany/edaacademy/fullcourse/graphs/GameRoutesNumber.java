package com.mycompany.edaacademy.fullcourse.graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class GameRoutesNumber {
    private static int MODULO = 1_000_000_000 + 7;
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new GameRoutesNumber().run();
    }

    private void run() {
        try {
            long timeStart = System.currentTimeMillis();
            optSolve();
            out.close();
            long timeEnd = System.currentTimeMillis();
            System.err.println("Time(ms) = " + (timeEnd - timeStart));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void optSolve() {
        int n = readInt();
        int m = readInt();

        // graph ds
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        // getting edges (u, v) and filling adj
        for(int i = 1; i <= m; i++) {
            int u = readInt();
            int v = readInt();
            //adj.get(u).add(v);
            // inverted edge
            adj.get(v).add(u);
        }

        Map<Integer, Integer> calculatedPathCnt = new HashMap<>();
        int pathCnt = getPathCnt(n, adj, calculatedPathCnt);
        out.println(pathCnt);
    }

    private int getPathCnt(int v, List<List<Integer>> adj, Map<Integer, Integer> calculatedPathCnt) {
        if (v == 1) {
            return 1;
        } else {
            Integer cnt = calculatedPathCnt.get(v);
            if (cnt != null) return cnt;
            int curr = 0;
            for(int u: adj.get(v)) {
                curr = (curr + getPathCnt(u, adj, calculatedPathCnt)) % MODULO;
            }
            calculatedPathCnt.put(v, curr);
            return curr;
        }
    }

    private void solve() {
        int n = readInt();
        int m = readInt();

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
        }

        int[] pathCnt = new int[n+1];
        dfs(1, adj, pathCnt);

        /*for(int i = 1; i <= n; i++) {
            out.println(i + ": " + pathCnt[i]);
        }*/
        out.println(pathCnt[n]);
    }

    private void dfs(int v, List<List<Integer>> adj, int[] pathCnt) {
        pathCnt[v] = (pathCnt[v] + 1) % MODULO;
        for(int u: adj.get(v)) {
            dfs(u, adj, pathCnt);
        }
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
