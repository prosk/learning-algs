package com.mycompany.edaacademy.fullcourse.graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class TreeMaxEdgesSet {
    List<Integer>[] adj;
    private boolean[] visited;
    private int[] dp_with_root;
    private int[] dp_without_root;

    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new TreeMaxEdgesSet().run();
    }

    private void run() {
        try {
            solve();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void solve() {
        int n = readInt();
        int m = n - 1;

        // graph ds
        adj = new List[n+1];

        for(int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        // getting edges (u, v) and filling adj
        for(int i = 1; i <= m; i++) {
            int u = readInt();
            int v = readInt();
            adj[u].add(v);
            adj[v].add(u);
        }

        visited = new boolean[n+1];
        dp_with_root = new int[n+1];
        dp_without_root = new int[n+1];

        dfs(1);
        out.println(Math.max(dp_with_root[1], dp_without_root[1]));
    }

    private void dfs(int v) {
        visited[v] = true;
        int maxDiff = Integer.MIN_VALUE, currDiff, uMaxDiff = -1, uOptSum = 0;
        for(int u: adj[v]) {
            if (!visited[u]) {
                dfs(u);
                int optSum = Math.max(dp_with_root[u], dp_without_root[u]);
                dp_without_root[v] += optSum;
                currDiff = dp_without_root[u] - optSum;
                if (currDiff > maxDiff) {
                    maxDiff = currDiff;
                    uMaxDiff = u;
                    uOptSum = optSum;
                }
            }
        }
        if (uMaxDiff != -1) {
            dp_with_root[v] = 1 + dp_without_root[uMaxDiff] + (dp_without_root[v] - uOptSum);
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