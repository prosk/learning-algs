package com.mycompany.edaacademy.fullcourse.graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class ProfessorFloyd {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new ProfessorFloyd().run();
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
            adj.get(v).add(u);
        }

        // http://e-maxx.ru/algo/bipartite_checking
        Boolean[] part = new Boolean[n+1];
        boolean ok = true;
        int[] q = new int[n+1];
        for (int st = 1; st <= n; st++) {
            if (part[st] == null) {
                int h = 0, t = 0;
                q[t++] = st;
                part[st] = false;
                while (h < t) {
                    int v = q[h++];
                    for (int i = 0; i < adj.get(v).size(); i++) {
                        int to = adj.get(v).get(i);
                        if (part[to] == null) {
                            part[to] = !part[v];
                            q[t++] = to;
                        } else {
                            ok &= part[to] != part[v];
                        }
                    }
                }
            }
        }

        out.println(ok ? "YES" : "NO");
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
