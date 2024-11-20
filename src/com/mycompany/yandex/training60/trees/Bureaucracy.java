package com.mycompany.yandex.training60.trees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Bureaucracy {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new Bureaucracy().run();
        out.close();
    }

    void run() {
        int N = readInt();
        List<List<Integer>> treeAdj  = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            treeAdj.add(new ArrayList<>());
        }
        // getting edges (u, v) and filling adj
        for (int i = 2; i <= N; i++) {
            int from = i;
            int to = readInt();
            addEdge(treeAdj, from, to);
        }
        // solve
        int[] vertexCnt = new int[N+1];
        long[] coins = new long[N+1];
        dfs(coins, vertexCnt, treeAdj, 1, 0);
        // print
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++) {
            if (i > 1) sb.append(' ');
            sb.append(coins[i]);
        }
        out.println(sb);
    }

    private void dfs(long[] coins, int[] vertexCnt, List<List<Integer>> adj,
                     int fromNode, int parentNode) {
        int adjCnt = adj.get(fromNode).size();
        int lowerCnt = 0;
        long coinsSum = 0;
        for (int i = 0; i < adjCnt; i++) {
            int childNode = adj.get(fromNode).get(i);
            if (childNode != parentNode) {
                dfs(coins, vertexCnt, adj, childNode, fromNode);
                lowerCnt += vertexCnt[childNode];
                coinsSum += coins[childNode];
            }
        }
        vertexCnt[fromNode] = lowerCnt+1;
        coins[fromNode] = coinsSum + vertexCnt[fromNode];
    }

    private void addEdge(List<List<Integer>> adj, int i, int j)
    {
        adj.get(i).add(j);
        adj.get(j).add(i);
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