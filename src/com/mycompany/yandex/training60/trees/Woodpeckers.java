package com.mycompany.yandex.training60.trees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Woodpeckers {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    int isolatedCnt = 0;
    int componentsCnt = 0;

    int allLeftHouseCnt = 0;
    int allRightHouseCnt = 0;

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
        List<Vertex> vertexes = new ArrayList<>();
        adjList.add(new ArrayList<>());
        for (int i = 1; i <= N; i++) {
            adjList.add(new ArrayList<>());
            vertexes.add(new Vertex(i, 0)); // index in list = vertex num - 1
        }
        // getting edges (u, v) and filling adj
        for (int i = 1; i <= M; i++) {
            int from = readInt();
            int to = readInt();
            addEdge(adjList, from, to);
            vertexes.get(from-1).degree++;
            vertexes.get(to-1).degree++;
        }
        // sort vertexes by degree
        Collections.sort(vertexes, (v1, v2) -> Integer.compare(v2.degree, v1.degree));
        // solve
        boolean[] visited = new boolean[N+1];
        boolean hasCorrectStructure = true;
        // цикл по вершинам от максималььной степени к минимальной
        for(int i = 0; i < vertexes.size(); i++) {
            int vNum = vertexes.get(i).num;
            if (adjList.get(vNum).size() == 0) {
                // изолированный дятел без друзей, значит далее только изолированные
                isolatedCnt = N - i;
                break;
            }
            if (!visited[vNum]) {
                // непосещенный компонент связности
                componentsCnt++;
                Component cmp = new Component();
                cmp.type = CType.SEQUENCE; // если будет где-то 2 сына переприсвоится на TREE
                cmps.add(cmp);
                hasCorrectStructure = dfs(visited, adjList, vertexes,
                        vNum, 0, true, cmp);
                if (!hasCorrectStructure) break;
                allLeftHouseCnt += cmp.leftHouseCnt;
                allRightHouseCnt += cmp.rightHouseCnt;
            }
        }
        if (!hasCorrectStructure) {
            out.println("0");
            return;
        }
    }

    private boolean dfs(boolean[] visited, List<List<Integer>> adj, List<Vertex> vertexes,
                     int fromNode, int parentNode, boolean isLeftHouse, Component cmp) {
        if (visited[fromNode]) {
            return false; // cycle
        }
        visited[fromNode] = true;
        // increment house cnt for fromNode
        if (isLeftHouse) cmp.leftHouseCnt++; else cmp.rightHouseCnt++;
        int adjCnt = adj.get(fromNode).size();

        // проверяем корректность текущего "пучка" парент - чайлды
        int childCnt = 0;
        int childWithOwnChildCnt = 0;
        int maxChildWithOwnChildCnt = (parentNode == 0) ? 2 : 1;
        int freeChildCnt = 0;
        for (int i = 0; i < adjCnt; i++) {
            int childNode = adj.get(fromNode).get(i);
            if (childNode != parentNode) {
                childCnt++;
                boolean childHasOwnChilds = vertexes.get(childNode-1).degree > 1;
                if (childHasOwnChilds) {
                    childWithOwnChildCnt++;
                } else {
                    freeChildCnt++;
                }
                if (childWithOwnChildCnt > maxChildWithOwnChildCnt) {
                    return false;
                }
                boolean hasCorrectStructure = dfs(visited, adj, vertexes, childNode, fromNode, !isLeftHouse, cmp);
                if (!hasCorrectStructure) return false;
            }
        }
        // save local parent--childs combinations count

        // if all checks is OK then hasCorrectStructure = true
        return true;
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

    public static class Vertex {
        int num;
        int degree;

        public Vertex(int num, int degree) {
            this.num = num;
            this.degree = degree;
        }
    }

    public static class Component {
        CType type;
        int leftHouseCnt;
        int rightHouseCnt;

        int statesCnt;
    }

    public enum CType {
        SEQUENCE, TREE
    }
}