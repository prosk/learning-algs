package com.mycompany.yandex.training40.graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class FastDijkstraAlg {
    List<List<int[]>> adj;
    private long[] dist;
    PriorityQueue<VertexDist> pq;
    private boolean[] visited;

    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new FastDijkstraAlg().run();
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
        int vertexCnt = readInt();
        int edgesCnt = readInt();

        dist = new long[vertexCnt + 1];
        visited = new boolean[vertexCnt + 1];
        pq = new PriorityQueue<>(edgesCnt);
        // graph ds
        adj = new ArrayList<>();
        for (int i = 0; i <= vertexCnt; i++) {
            adj.add(new ArrayList<>());
            dist[i] = Long.MAX_VALUE;
        }

        // getting edges (u, v) and filling adj
        for (int i = 1; i <= edgesCnt; i++) {
            int u = readInt();
            int v = readInt();
            int weight = readInt();

            adj.get(u).add(new int[]{v, weight});
            adj.get(v).add(new int[]{u, weight});
        }

        int startVertex = readInt();
        int endVertex = readInt();

        dist[startVertex] = 0;
        pq.add(new VertexDist(startVertex, 0));

        while (!pq.isEmpty()) {
            VertexDist v = pq.poll();

            if (v.num == endVertex) break;
            if (visited[v.num]) continue;
            visited[v.num] = true;

            for (int[] u : adj.get(v.num)) {
                if (dist[u[0]] > dist[v.num] + u[1]) {
                    dist[u[0]] = dist[v.num] + u[1];
                }
                pq.add(new VertexDist(u[0], dist[u[0]]));
            }
        }
        out.println(dist[endVertex] == Long.MAX_VALUE ? -1 : dist[endVertex]);
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

    public static class VertexDist implements Comparable<VertexDist> {
        int num;
        long dist;

        public VertexDist(int num, long dist) {
            this.num = num;
            this.dist = dist;
        }


        @Override
        public int compareTo(VertexDist o) {
            return Long.compare(this.dist, o.dist);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            VertexDist that = (VertexDist) o;
            return num == that.num && dist == that.dist;
        }

        @Override
        public int hashCode() {
            return Objects.hash(num, dist);
        }
    }

}
