package com.mycompany.codeforces.div2round915;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/*
  жадное решение которое пытается искать диаметр дерева, затем схлопывать его в 1 вершину,
  далее во вновь полученном дереве опять искать диаметр и тд
  Оно неверное
  Простой контрпример
  Тестовые данные
1
11
2 5
5 10
6 1
8 1
1 4
9 7
7 3
3 4
4 11
10 11

В жадном решение с диаметром получаем ответ 3, а правильный ответ 2

 */

public class Task2 {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new Task2().run();
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
        int t = readInt();
        for(; t > 0; t--) {
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
            int currEdgesCnt = N-1, currVertexCnt = N;
            int ans = 0;
            while(true) {
                // find diameter
                int a = 1;
                int b = BFS(treeAdj, a, currVertexCnt, pred, dist);
                int c = BFS(treeAdj, b, currVertexCnt, pred, dist);
                // diameter = path from b to c
                if (dist[c] == currEdgesCnt) {
                    ans++;
                    break;
                } else {
                    ans++;
                    // fold all vertexes on path from b to c to one new vertex
                    // new vertex num = b
                    Set<Integer> diameterVertexes = new HashSet<>();
                    diameterVertexes.add(c);
                    int curr = c;
                    int minVNum = c;
                    while(true) {
                        curr = pred[curr];
                        diameterVertexes.add(curr);
                        if (curr < minVNum) minVNum = curr;
                        if (curr == b) break;
                    }
                    // modify treeAdj

                    int[] oldToNewNums = new int[currVertexCnt+1];
                    int firstNextNum = minVNum + 1;
                    for(int i = 1; i <= currVertexCnt; i++) {
                        if (i <= minVNum) {
                            oldToNewNums[i] = i;
                        } else {
                            if (diameterVertexes.contains(i)) {
                                oldToNewNums[i] = minVNum;
                            } else {
                                oldToNewNums[i] = firstNextNum++;
                            }
                        }
                    }
                    int newVertexCnt = currVertexCnt - diameterVertexes.size() + 1;
                    List<List<Integer>> newTreeAdj  = new ArrayList<>();
                    for (int i = 0; i <= newVertexCnt; i++) {
                        newTreeAdj.add(new ArrayList<>());
                    }
                    for (int i = 1; i <= currVertexCnt; i++) {
                        int currOldFrom = i;

                        for (int j = 0; j < treeAdj.get(currOldFrom).size(); j++) {
                            int currOldTo = treeAdj.get(currOldFrom).get(j);
                            boolean fromInDiameter = diameterVertexes.contains(currOldFrom);
                            boolean toInDiameter = diameterVertexes.contains(currOldTo);
                            if (fromInDiameter && toInDiameter) {
                                continue;
                            }
                            newTreeAdj.get(oldToNewNums[currOldFrom]).add(oldToNewNums[currOldTo]);
                        }
                    }

                    treeAdj = newTreeAdj;
                    currVertexCnt = newVertexCnt;
                    currEdgesCnt = newVertexCnt - 1;
                }
            }
            out.println(ans);
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
