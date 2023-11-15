package com.mycompany.yandex.training40.graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class SledTrip {
    private int N;
    private List<List<int[]>> treeAdj  = new ArrayList<>();
    private double[][][] pathMatrix = new double[2001][2001][2]; // [0] = dist [1] = time
    private int[] t = new int[2001];
    private int[] v = new int[2001];

    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new SledTrip().run();
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
        N = readInt();

        double[] dist = new double[N+1];
        int[] prev = new int[N+1];
        boolean[] visited = new boolean[N+1];
        int startVertex = 1;

        treeAdj.add(new ArrayList<>());
        for(int i = 1; i <= N; i++) {
            t[i] = readInt();
            v[i] = readInt();
            treeAdj.add(new ArrayList<>());
            dist[i] = (i == startVertex) ? 0d : Double.MAX_VALUE;
        }
        // getting edges (u, v) and filling adj
        for (int i = 1; i <= N-1; i++) {
            int from = readInt();
            int to = readInt();
            int distance = readInt();

            treeAdj.get(from).add(new int[]{to, distance});
            treeAdj.get(to).add(new int[]{from, distance});
        }

        // calculation of the times matrix
        for(int i = 1; i <= N; i++) {
            treeBFS(i);
        }

        // Dijkstra on the graph in the pathMatrix
        boolean unvisitedFound = true;
        int currVertex = startVertex;
        while(unvisitedFound) {
            // process currVertex
            visited[currVertex] = true;
            for(int from = 1; from <= N; from++) {
                if (from != currVertex) {
                    double newDist = dist[currVertex] + pathMatrix[from][currVertex][1];
                    if (newDist < dist[from]) {
                        dist[from] = newDist;
                        prev[from] = currVertex;
                    }

                }
            }
            // search of the next currVertex with min dist
            unvisitedFound = false;
            double minDist = Double.MAX_VALUE;
            for(int i = 1; i <= N; i++) {
                if (!visited[i] && dist[i] < minDist) {
                    currVertex = i;
                    minDist = dist[i];
                    unvisitedFound = true;
                }
            }
        }

        double maxDist = -1d;
        int maxDistVertex = 0;
        for(int i = 1; i <= N; i++) {
            if (dist[i] > maxDist) {
                maxDistVertex = i;
                maxDist = dist[i];
            }
        }

        // getting path
        StringBuilder pathStr = new StringBuilder("");
        int curr = maxDistVertex;
        while (curr > 0) {
            if (pathStr.length() > 0) pathStr.append(' ');
            pathStr.append(curr);
            curr = prev[curr];
        }

        // print results
        out.printf("%.9f\n", maxDist);
        out.println(pathStr);
    }

    private void treeBFS(int src)
    {
        Deque<Integer> queue = new ArrayDeque<>();
        boolean visited[] = new boolean[N+1];
        visited[src] = true;
        int prepTime = t[src], velocity = v[src];
        queue.addLast(src);
        // bfs Algorithm
        while (!queue.isEmpty()) {
            int v = queue.pollFirst();
            for(int[] u: treeAdj.get(v)) {
                int uNum = u[0], uDistance = u[1];
                if (!visited[uNum]) {
                    visited[uNum] = true;
                    pathMatrix[src][uNum][0] = pathMatrix[src][v][0] + uDistance;
                    pathMatrix[src][uNum][1] = pathMatrix[src][uNum][0] / velocity + prepTime;
                    queue.addLast(uNum);
                }
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
