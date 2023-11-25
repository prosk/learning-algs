package com.mycompany.yandex.training40.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class SimpleTSP {
    private int bestCost = Integer.MAX_VALUE;
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new SimpleTSP().run();
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
        int N = readInt();

        // graph ds
        List<List<int[]>> adj = new ArrayList<>();
        for(int i = 0; i <= N; i++) {
            adj.add(new ArrayList<>());
        }

        // getting edges (u, v) and filling adj
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                int weight = readInt();
                if(i != j && weight != 0)
                    adj.get(i).add(new int[] {j, weight});
            }
        }

        boolean[] visited = new boolean[N+1];
        Set<Integer> pathVertexes = new HashSet<>();
        pathVertexes.add(1);
        visited[1] = true; // visited[1] always equals true
        dfs(N, 1, adj, visited, 0, pathVertexes);
        out.println(bestCost == Integer.MAX_VALUE ? -1 : bestCost);
    }

    private void dfs(int N, int v, List<List<int[]>> adj, boolean[] visited, int currCost, Set<Integer> pathVertexes) {
        // инвариант - вершина v уже отмечена как посещенная, включена в pathVertexes и для нее посчитан currCost
        if (v == 1 && pathVertexes.size() == N) {
            bestCost = Math.min(currCost, bestCost);
            return;
        }

        for(int[] u: adj.get(v)) {
            int uNum = u[0], uWeight = u[1];
            if (!visited[uNum]) {
                visited[uNum] = true;
                pathVertexes.add(uNum);
                dfs(N, uNum, adj, visited, currCost + uWeight, pathVertexes);
                visited[uNum] = false;
                pathVertexes.remove(uNum);
            } else if (uNum == 1 && pathVertexes.size() == N) {
                dfs(N, uNum, adj, visited, currCost + uWeight, pathVertexes);
            }
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
