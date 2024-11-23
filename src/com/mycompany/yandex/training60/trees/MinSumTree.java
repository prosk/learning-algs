package com.mycompany.yandex.training60.trees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MinSumTree {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    List<List<Integer>> adj = new ArrayList<>();
    int[] cost;
    long[][] dp;

    List<Integer> markedNodes = new ArrayList<>();

    public static void main(String[] args) {
        new MinSumTree().run();
        out.close();
    }

    void run() {
        int N = readInt();
        for (int i = 0; i <= N; i++) {
            adj.add(new ArrayList<>());
        }
        // getting edges (u, v) and filling adj
        for (int i = 1; i <= N-1; i++) {
            int from = readInt();
            int to = readInt();
            addEdge(from, to);
        }
        // getting cost array
        cost = new int[N+1];
        for(int i = 1; i <= N; i++) {
            cost[i] = readInt();
        }
        // solve
        if (N == 1) {
            out.println(cost[1] + " 1");
            out.println("1");
            return;
        }

        dp = new long[N+1][2];
        int rootNum = 1;
        // dfs from leaves to calc dp array
        dfsFromLeaves(rootNum, 0);

        // now we know min cost
        long minCost = Math.min(dp[rootNum][0], dp[rootNum][1]);
        // dfs for marked nodes list filling
        boolean isRootMarked = dp[rootNum][1] < dp[rootNum][0];
        dfsFromRoot(rootNum, 0, isRootMarked);
        // print answer
        int markedNodesCnt = markedNodes.size();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < markedNodesCnt; i++) {
            if (i > 0) sb.append(' ');
            sb.append(markedNodes.get(i));
        }
        out.println(minCost + " " + markedNodesCnt);
        out.println(sb);
    }

    private void dfsFromRoot(int fromNode, int parentNode, boolean isMarked) {
        if (isMarked) {
            markedNodes.add(fromNode);
        }
        int adjCnt = adj.get(fromNode).size();
        for (int i = 0; i < adjCnt; i++) {
            int childNode = adj.get(fromNode).get(i);
            if (childNode != parentNode) {
                boolean childIsMarked;
                if (isMarked) {
                    childIsMarked = dp[childNode][1] < dp[childNode][0];
                } else {
                    childIsMarked = true;
                }
                dfsFromRoot(childNode, fromNode, childIsMarked);
            }
        }
    }

    private void dfsFromLeaves(int fromNode, int parentNode) {
        int adjCnt = adj.get(fromNode).size();
        int childCnt = parentNode > 0 ? adjCnt-1 : adjCnt;
        if (childCnt == 0) {
            // leaf
            dp[fromNode][0] = 0;
            dp[fromNode][1] = cost[fromNode];
        } else {
            dp[fromNode][0] = 0;
            dp[fromNode][1] = cost[fromNode];
            for (int i = 0; i < adjCnt; i++) {
                int childNode = adj.get(fromNode).get(i);
                if (childNode != parentNode) {
                    dfsFromLeaves(childNode, fromNode);
                    dp[fromNode][0] += dp[childNode][1];
                    dp[fromNode][1] += Math.min(dp[childNode][0], dp[childNode][1]);
                }
            }
        }
    }

    private void addEdge(int i, int j)
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