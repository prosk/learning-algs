package com.mycompany.yandex.training60.trees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Woodpeckers {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    int isolatedCnt = 0;
    int componentsCnt = 0;

    List<Component> cmps = new ArrayList<>();

    public static void main(String[] args) {
        new Woodpeckers().run();
        out.close();
    }

    void run() {
        int N = readInt();
        int M = readInt();
        int K = readInt();
        List<List<Integer>> adjList  = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }
        // getting edges (u, v) and filling adj
        for (int i = 1; i <= M; i++) {
            int from = readInt();
            int to = readInt();
            addEdge(adjList, from, to);
        }
        // solve
        boolean[] visited = new boolean[N+1];
        boolean hasCycle = false;
        for(int i = 1; i <= N; i++) {
            if (adjList.get(i).size() == 0) {
                // изолированный дятел без друзей
                isolatedCnt++;
                continue;
            }
            if (!visited[i]) {
                // непосещенный компонент связности
                componentsCnt++;
                Component cmp = new Component();
                cmp.type = CType.SEQUENCE; // если будет где-то 2 сына переприсвоится на TREE
                cmps.add(cmp);
                hasCycle = dfs(visited, adjList, i, 0, true, cmp);
                if (hasCycle) break;
            }
        }
        if (hasCycle) {
            out.println("0");
            return;
        }
    }

    private boolean dfs(boolean[] visited, List<List<Integer>> adj,
                     int fromNode, int parentNode, boolean isLeftHouse, Component cmp) {
        visited[fromNode] = true;
        // increment house cnt for fromNode
        if (isLeftHouse) cmp.leftHouseCnt++; else cmp.rightHouseCnt++;

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

    public static class Component {
        CType type;
        int leftHouseCnt;
        int rightHouseCnt;
    }

    public enum CType {
        SEQUENCE, TREE
    }
}