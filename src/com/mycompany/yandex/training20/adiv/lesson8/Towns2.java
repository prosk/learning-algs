package com.mycompany.yandex.training20.adiv.lesson8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Towns2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    long ans;
    List<Integer> ansNodes = new ArrayList<>();

    public static void main(String[] args) {
        new Towns2().run();
        out.close();
    }

    void run() {
        int N = readInt();
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
        NodeData[] nodes = new NodeData[N+1];
        // first DFS
        dfsFromLeaves(nodes, treeAdj, 1, 0);
        // DFS for finding answer
        ans = nodes[1].lowerDistSum;
        dfsFromRoot(nodes, treeAdj, 1, 0);

        // debug
        /*for(int i = 1; i <= N; i++) {
            out.println(String.format("%d : %s", i, nodes[i]));
        }*/
        Collections.sort(ansNodes);
        StringBuilder sb = new StringBuilder();
        sb.append(ans);
        sb.append(' ');
        sb.append(ansNodes.size());
        sb.append(' ');
        for(int i = 0; i < ansNodes.size(); i++) {
            sb.append(ansNodes.get(i));
            if (i < ansNodes.size() - 1) sb.append(' ');
        }
        out.println(sb);
    }

    // идя от корня к листьям, считает для данного узла дерева
    // 1) сумму расстояний и число узлов "выше" по дереву (равны нулю для корня)
    // 2) суммарное расстояние от данного узла до всех других (которые выше + которые ниже)
    // которые выше - это все кроме самого узла и узлов ниже него
    private void dfsFromRoot(NodeData[] nodes, List<List<Integer>> adj,
                               int fromNode, int parentNode) {
        int adjCnt = adj.get(fromNode).size();
        long upperCnt = 0;
        long upperDistSum = 0;
        if (parentNode > 0) {
            // calc upper metrics
            upperCnt = 1L + nodes[parentNode].upperCnt;
            upperDistSum =  1L + nodes[parentNode].upperDistSum + nodes[parentNode].upperCnt;
            long siblingsCnt = nodes[parentNode].childCnt - 1;
            if (siblingsCnt > 0) {
                long lowerSubtreeSum = 1L + nodes[fromNode].lowerDistSum + nodes[fromNode].lowerCnt;
                long lowerSubtreeCnt = 1L + nodes[fromNode].lowerCnt;
                long siblingsTreeSum = nodes[parentNode].lowerDistSum - lowerSubtreeSum;
                long siblingsTreeCnt = nodes[parentNode].lowerCnt - lowerSubtreeCnt;
                upperCnt += siblingsTreeCnt;
                upperDistSum += siblingsTreeSum + siblingsTreeCnt;
            }
        }
        nodes[fromNode].upperCnt = upperCnt;
        nodes[fromNode].upperDistSum = upperDistSum;
        long allDistSum = nodes[fromNode].lowerDistSum + nodes[fromNode].upperDistSum;
        if (allDistSum < ans) {
            ansNodes = new ArrayList<>();
            ansNodes.add(fromNode);
            ans = allDistSum;
        } else if (allDistSum == ans) {
            ansNodes.add(fromNode);
        }
        for (int i = 0; i < adjCnt; i++) {
            int childNode = adj.get(fromNode).get(i);
            if (childNode != parentNode) {
                dfsFromRoot(nodes, adj, childNode, fromNode);
            }
        }
    }

    // идя от листьев к корню, считает для данного узла дерева
    // 1) кол-во узлов "ниже"
    // 2) сумму расстояний до узлов "ниже"
    // для листьев оба параметра равны нулю
    private void dfsFromLeaves(NodeData[] nodes, List<List<Integer>> adj,
                               int fromNode, int parentNode) {
        int adjCnt = adj.get(fromNode).size();
        int childCnt = parentNode > 0 ? adjCnt-1 : adjCnt;
        NodeData nodeData = new NodeData();
        nodeData.childCnt = childCnt;
        nodeData.upperCnt = 0;
        nodeData.upperDistSum = 0;
        nodeData.lowerCnt = 0;
        nodeData.lowerDistSum = 0;
        for (int i = 0; i < adjCnt; i++) {
            int childNode = adj.get(fromNode).get(i);
            if (childNode != parentNode) {
                dfsFromLeaves(nodes, adj, childNode, fromNode);
                nodeData.lowerCnt += nodes[childNode].lowerCnt + 1L;
                nodeData.lowerDistSum += nodes[childNode].lowerDistSum + nodes[childNode].lowerCnt + 1L;
            }
        }
        nodes[fromNode] = nodeData;
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

    public static class NodeData {
        public long childCnt;
        public long upperCnt;
        public long upperDistSum;

        public long lowerCnt;
        public long lowerDistSum;

        @Override
        public String toString() {
            return "NodeData{" +
                    "childCnt=" + childCnt +
                    ", upperCnt=" + upperCnt +
                    ", upperDistSum=" + upperDistSum +
                    ", lowerCnt=" + lowerCnt +
                    ", lowerDistSum=" + lowerDistSum +
                    '}';
        }
    }
}