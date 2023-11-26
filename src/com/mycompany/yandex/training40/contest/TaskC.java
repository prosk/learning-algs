package com.mycompany.yandex.training40.contest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class TaskC {
    List<List<int[]>> adj;
    private long[] distT;
    private Long[] distM;
    PriorityQueue<VertexDist> pq;
    private boolean[] visited;

    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new TaskC().run();
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

        if (vertexCnt == 1 && edgesCnt == 0) {
            out.println(10_000_000);
            return;
        }
        if (edgesCnt == 0 && vertexCnt > 1) {
            out.println(0);
            return;
        }

        distT = new long[vertexCnt + 1];
        distM = new Long[vertexCnt + 1];
        visited = new boolean[vertexCnt + 1];
        pq = new PriorityQueue<>(edgesCnt);
        // graph ds
        adj = new ArrayList<>();
        for (int i = 0; i <= vertexCnt; i++) {
            adj.add(new ArrayList<>());
            distM[i] = null;
        }

        // getting edges (u, v) and filling adj
        for (int i = 1; i <= edgesCnt; i++) {
            int u = readInt();
            int v = readInt();
            int weightT = readInt();
            int weightM = readInt();

            adj.get(u).add(new int[]{v, weightT, weightM});
            adj.get(v).add(new int[]{u, weightT, weightM});
        }

        int startVertex = 1;
        int endVertex = vertexCnt;

        distT[startVertex] = 0;
        distM[startVertex] = Long.MAX_VALUE;
        pq.add(new VertexDist(startVertex, 0));

        while (!pq.isEmpty()) {
            VertexDist v = pq.poll();

            if (v.num == endVertex) break;
            if (visited[v.num] || distM[v.num] == null) continue;
            visited[v.num] = true;

            for (int[] u : adj.get(v.num)) {
                int uNum = u[0], uTime = u[1], uMass = u[2];
                if (distT[v.num] + uTime <= 1440) {
                    long currDistM = distM[uNum] == null ? 0 : distM[uNum];
                    long newDistM = Math.min(distM[v.num], uMass);
                    if (currDistM <= newDistM) {
                        distT[uNum] = distT[v.num] + uTime;
                        distM[uNum] = newDistM;
                        pq.add(new VertexDist(uNum, newDistM));
                    }
                }
            }
        }
        if (distM[endVertex] == null) {
            out.println(0);
        } else {
            long resMaxWeight = distM[endVertex] - 3 * 1_000_000;
            if (resMaxWeight < 0) {
                out.println(0);
            } else {
                out.println(resMaxWeight/100);
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

    public static class VertexDist implements Comparable<VertexDist> {
        int num;
        long dist;

        public VertexDist(int num, long dist) {
            this.num = num;
            this.dist = dist;
        }


        @Override
        public int compareTo(VertexDist o) {
            return -1 * Long.compare(this.dist, o.dist);
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
