package com.mycompany.yandex.training20.adiv.lesson8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Towns1 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new Towns1().run();
        out.close();
    }

    void run() {
        int N = readInt();
        int[] dist = new int[N+1];
        int[] pred = new int[N+1];
        List<List<Integer>> treeAdj  = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            treeAdj.add(new ArrayList<>());
        }
        // getting edges (u, v) and filling adj
        for (int i = 1; i <= N-1; i++) {
            int from = readInt();
            int to = readInt();
            addEdge(treeAdj, from, to);
        }
        // solve
        if (N == 1) {
            out.println("0 1 1");
            return;
        }
        // find diameter
        int a = 1;
        int b = BFS(treeAdj, a, N, pred, dist);
        int c = BFS(treeAdj, b, N, pred, dist);
        // diameter = path from b to c
        List<Integer> diameterVertexes = new ArrayList<>();
        int curr = c;
        diameterVertexes.add(c);
        while(curr != b) {
            curr = pred[curr];
            diameterVertexes.add(curr);
        }
        // output answer
        int diameterSize = diameterVertexes.size();
        int minDist = diameterSize / 2;
        if (diameterSize % 2 == 0) {
            int first = diameterVertexes.get(minDist-1);
            int second = diameterVertexes.get(minDist);
            out.println(minDist + " 2 " + Math.min(first, second) + " " + Math.max(first, second));
        } else {
            out.println(minDist + " 1 " + diameterVertexes.get(minDist));
        }

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

    private void addEdge(List<List<Integer>> adj, int i, int j)
    {
        adj.get(i).add(j);
        adj.get(j).add(i);
    }

    // возвращает номер вершины самой далекой от src
    private int BFS(List<List<Integer>> adj, int src, int vertexCnt, int pred[], int dist[])
    {
        Deque<Integer> queue = new ArrayDeque<>();
        boolean visited[] = new boolean[vertexCnt+1];

        for (int i = 1; i <= vertexCnt; i++) {
            visited[i] = false;
            dist[i] = Integer.MAX_VALUE;
            pred[i] = -1;
        }

        // now source is first to be visited and
        // distance from source to itself should be 0
        visited[src] = true;
        dist[src] = 0;
        queue.addLast(src);

        int maxDist = 0, maxDistVertexNum = 0;

        // bfs Algorithm
        while (!queue.isEmpty()) {
            int u = queue.pollFirst();
            for (int i = 0; i < adj.get(u).size(); i++) {
                int v = adj.get(u).get(i);
                if (visited[v] == false) {
                    visited[v] = true;
                    dist[v] = dist[u] + 1;
                    pred[v] = u;
                    queue.addLast(v);

                    if (dist[v] > maxDist) {
                        maxDist = dist[v];
                        maxDistVertexNum = v;
                    }
                }
            }
        }
        return maxDistVertexNum;
    }
}