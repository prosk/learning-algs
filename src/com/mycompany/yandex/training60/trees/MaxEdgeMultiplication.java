package com.mycompany.yandex.training60.trees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MaxEdgeMultiplication {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    List<List<Integer>> adj = new ArrayList<>();

    long maxEdgeMult = 0;

    int[] maxDiameters;
    int[] maxLens;

    public static void main(String[] args) {
        new MaxEdgeMultiplication().run();
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
        // solve
        if (N == 2) {
            out.println("0");
            return;
        }
        // finding a root for N > 2
        int i = 1;
        while(adj.get(i).size() < 2) i++;
        int rootNum = i;

        // first dfs to calc subtree diameters and lens
        maxDiameters = new int[N+1];
        maxLens = new int[N+1];
        dfsFromLeaves(rootNum, 0);
        // second dfs for getting answer - like "in out dp"
        dfsFromRoot(rootNum, 0);
        // print answer
        out.println(maxEdgeMult);
    }

    private void dfsFromRoot(int fromNode, int parentNode) {
        List<NodeData> adjNodes = new ArrayList<>();
        int adjCnt = adj.get(fromNode).size();
        // all adj nodes - with "upper" subtree !!!
        for (int i = 0; i < adjCnt; i++) {
            int adjNodeNum = adj.get(fromNode).get(i);
            adjNodes.add(new NodeData(adjNodeNum, maxDiameters[adjNodeNum], maxLens[adjNodeNum]+1));
        }
        // recalc for every deleted edge
        maxDiameters[fromNode] = 0;
        maxLens[fromNode] = 0;
        for (int i = 0; i < adjCnt; i++) {
            int childNode = adj.get(fromNode).get(i);
            if (childNode != parentNode) {
                // delete edge [fromNode -- childNode], excludedNodeNum = childNode
                MaximumPair[] maximumPairs = getNodesMaximumPairs(adjNodes, childNode);
                MaximumPair diameterPair = maximumPairs[0];
                MaximumPair lenPair = maximumPairs[1];
                maxDiameters[fromNode] = Math.max(diameterPair.first, lenPair.getSum());
                maxLens[fromNode] = lenPair.first;
                // update answer
                long diametersMult = (long)maxDiameters[childNode] * (long)maxDiameters[fromNode];
                maxEdgeMult = Math.max(maxEdgeMult, diametersMult);
                // go to child node
                dfsFromRoot(childNode, fromNode);
                // set to zero for next deleted edge processing
                maxDiameters[fromNode] = 0;
                maxLens[fromNode] = 0;
            }
        }
    }

    private MaximumPair[] getNodesMaximumPairs(List<NodeData> adjNodes, int excludedNodeNum) {
        MaximumPair diameterPair = new MaximumPair(0, 0);
        MaximumPair lenPair = new MaximumPair(0, 0);
        for(NodeData node: adjNodes) {
            if (node.num == excludedNodeNum) {
                continue;
            }
            // diameter - only one maximum
            if (node.maxDiameter > diameterPair.first) {
                diameterPair.first = node.maxDiameter;
            }
            // len - two maximums
            if (node.maxLen > lenPair.first) {
                lenPair.second = lenPair.first;
                lenPair.first = node.maxLen;
            } else if (node.maxLen > lenPair.second) {
                lenPair.second = node.maxLen;
            }
        }
        return new MaximumPair[] {diameterPair, lenPair};
    }

    // dfs from leaves to root for precalculation maxDiameters and maxLens
    private void dfsFromLeaves(int fromNode, int parentNode) {
        int adjCnt = adj.get(fromNode).size();
        int childCnt = parentNode > 0 ? adjCnt-1 : adjCnt;
        List<Integer> childLens = new ArrayList<>();
        if (childCnt == 0) {
            // leaf
            maxDiameters[fromNode] = 0;
            maxLens[fromNode] = 0;
            return;
        } else {
            // not leaf
            for (int i = 0; i < adjCnt; i++) {
                int childNode = adj.get(fromNode).get(i);
                if (childNode != parentNode) {
                    dfsFromLeaves(childNode, fromNode);
                    maxDiameters[fromNode] = Math.max(maxDiameters[fromNode], maxDiameters[childNode]);
                    maxLens[fromNode] = Math.max(maxLens[fromNode], maxLens[childNode] + 1);
                    childLens.add(maxLens[childNode] + 1);
                }
            }
        }
        MaximumPair pair = getMaximumPair(childLens);
        maxDiameters[fromNode] = Math.max(maxDiameters[fromNode], pair.getSum());
    }

    // getting two maximums
    private MaximumPair getMaximumPair(List<Integer> list) {
        int first = 0, second = 0;
        for(Integer elem: list) {
            if (elem > first) {
                second = first;
                first = elem;
            } else if (elem > second) {
                second = elem;
            }
        }
        return new MaximumPair(first, second);
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

    public static class MaximumPair {
        int first;
        int second;

        public MaximumPair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        public int getSum() {
            return first + second;
        }
    }

    public static class NodeData {
        int num;
        int maxDiameter;
        int maxLen;

        public NodeData(int num, int maxDiameter, int maxLen) {
            this.num = num;
            this.maxDiameter = maxDiameter;
            this.maxLen = maxLen;
        }
    }
}