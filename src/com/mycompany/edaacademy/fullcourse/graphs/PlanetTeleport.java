package com.mycompany.edaacademy.fullcourse.graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class PlanetTeleport {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new PlanetTeleport().run();
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
        int[] t = new int[n+1];
        List<List<Integer>> inv = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            inv.add(new ArrayList<>());
        }

        for(int i = 1; i <= n; i++) {
            t[i] = readInt();
            inv.get(t[i]).add(i);
        }

        int[] ans = new int[n+1];

        for(int i = 1; i <= n; i++) {
            if (ans[i] == 0) {
                processComponent(n, i, t, inv, ans);
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++) {
            sb.append(ans[i]);
            if (i < n) sb.append(' ');
        }
        out.println(sb);
    }

    void processComponent(int n, int start, int[] t, List<List<Integer>> inv, int[] ans) {
        Map<Integer, Integer> prevInd = new HashMap<>();
        Set<Integer> cycleVertexes = new HashSet<>();
        int cycleLen = 0;
        boolean cycleFound = false;
        int curr = start, currInd = 0;
        prevInd.put(start, currInd);
        while (!cycleFound) {
            int newCurr = t[curr];
            Integer ind = prevInd.get(newCurr);
            if (ind != null) {
                // we have found a cycle
                cycleLen = currInd - ind + 1;
                for(int i = newCurr; i != curr; i = t[i]) {
                    ans[i] = cycleLen;
                    cycleVertexes.add(i);
                }
                ans[curr] = cycleLen;
                cycleVertexes.add(curr);
                // we can exit
                cycleFound = true;
            } else  {
                prevInd.put(newCurr, ++currInd);
                curr = newCurr;
            }
        }
        // set ans for paths to cycle
        for(int u: cycleVertexes) {
            for(int toU: inv.get(u)) {
                if (ans[toU] == 0) {
                    dfs(toU, inv, ans, cycleLen+1);
                }
            }
        }

    }

    private void dfs(int v, List<List<Integer>> adj, int[] ans, int startValue) {
        ans[v] = startValue;
        for(int u: adj.get(v)) {
            if (ans[u] == 0)
                dfs(u, adj, ans, startValue+1);
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
