package com.mycompany.yandex.training40.graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class BusSchedule {
    List<List<int[]>> adj;
    private int[] dist;
    PriorityQueue<VertexDist> pq;
    private boolean[] visited;

    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new BusSchedule().run();
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
        int startVertex = readInt();
        int endVertex = readInt();
        int edgesCnt = readInt();

        // graph ds
        adj = new ArrayList<>();
        dist = new int[vertexCnt+1]; // dist[i] = минимальное время прибытия в вершину i
        for(int i = 0; i <= vertexCnt; i++) {
            adj.add(new ArrayList<>());
            dist[i] = Integer.MAX_VALUE;
        }

        visited = new boolean[vertexCnt+1];
        pq = new PriorityQueue<>(edgesCnt);

        // getting edges (u, v) and filling adj
        for (int i = 1; i <= edgesCnt; i++) {
            int from = readInt();
            int fromTime = readInt();
            int to = readInt();
            int toTime = readInt();

            adj.get(from).add(new int[]{to, fromTime, toTime});
        }

        dist[startVertex] = 0;
        pq.add(new VertexDist(startVertex, 0));

        while (!pq.isEmpty()) {
            VertexDist v = pq.poll();

            if (v.num == endVertex) break;
            if (visited[v.num]) continue;
            visited[v.num] = true;

            int from = v.num;
            int departureTime = dist[from];
            for(int[] u: adj.get(v.num)) {
                int to = u[0];
                int fromTime = u[1];
                int destTime = u[2];
                if (fromTime >= departureTime && destTime < dist[to]) {
                    dist[to] = destTime;
                }
                pq.add(new VertexDist(to, dist[to]));
            }
        }
        out.println(dist[endVertex] == Integer.MAX_VALUE ? -1 : dist[endVertex]);
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
        int dist;

        public VertexDist(int num, int dist) {
            this.num = num;
            this.dist = dist;
        }


        @Override
        public int compareTo(VertexDist o) {
            return Integer.compare(this.dist, o.dist);
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
