package com.mycompany.edaacademy.fullcourse.graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

public class CoursesSchedule {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new CoursesSchedule().run();
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

        int[] color = new int[n+1];
        List<Integer> ans = new ArrayList<>();
        boolean hasCycle = false;
        for(int v = 1; v <= n; v++) {
            if (color[v] == 0) {
                hasCycle = dfs(v, adj, color, ans);
                if (hasCycle) break;
            }
        }

        if (hasCycle) {
            out.println("IMPOSSIBLE");
        } else {
            StringBuilder sb = new StringBuilder("");
            for(int i = ans.size()-1; i >= 0; i--) {
                sb.append(ans.get(i));
                if (i > 0) sb.append(' ');
            }
            out.println(sb);
        }
    }

    private boolean dfs(int v, List<List<Integer>> adj, int[] color, List<Integer> ans) {
        color[v] = 1;
        for(int u: adj.get(v)) {
            if (color[u] == 0) {
                boolean hasCycle = dfs(u, adj, color, ans);
                if (hasCycle) return true;
            } else if (color[u] == 1) {
                return true;
            }
        }
        color[v] = 2;
        ans.add(v);
        return false;
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
