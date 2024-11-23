package com.mycompany.yandex.training60.trees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class WoodpeckersOpt {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    static int SIZE = 0;
    static int[] fact;

    int isolatedCnt = 0;
    int componentsCnt = 0;

    int allLeftHouseCnt = 0;
    int allRightHouseCnt = 0;

    static int MODULO = 0;

    List<Component> cmps = new ArrayList<>();

    public static void main(String[] args) {
        new WoodpeckersOpt().run();
        out.close();
    }

    void run() {
        int N = readInt();
        int M = readInt();
        int K = readInt();
        MODULO = K;
        SIZE = N;
        fact = new int[SIZE + 1];
        preFactorial();

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
                cmps.add(cmp);
                hasCorrectStructure = dfs(visited, adjList, vNum, 0, true, cmp);
                if (!hasCorrectStructure) break;
                allLeftHouseCnt += cmp.leftHouseCnt;
                allRightHouseCnt += cmp.rightHouseCnt;
                // учет симметрии и поворота "сверху вниз"
                if (cmp.leftHouseCnt == 1 || cmp.rightHouseCnt == 1) {
                    // цепочка a--b либо пучок от 1 к N
                    cmp.statesCnt = mult(cmp.statesCnt, 2);
                } else {
                    // возможны и симметрия и поворот сверху вниз
                    cmp.statesCnt = mult(cmp.statesCnt, 4);
                }
            }
        }
        if (!hasCorrectStructure) {
            out.println("0");
            return;
        }
        // перебираем порядок компонентов и для каждого порядка все комбинации состояний
        int cmpOrdersCnt = getFactorial(componentsCnt);
        int statesCompCnt = 1;
        for(Component cmp: cmps) {
            statesCompCnt = mult(statesCompCnt, cmp.statesCnt);
        }
        int combForConnected = mult(cmpOrdersCnt, statesCompCnt);
        // к зафиксированному порядку компонентов и состоянию каждого компонента
        // добавляем перебор добавления к размещению изолированных дятлов
        if (isolatedCnt > 0) {
            int slots = isolatedCnt;
            int delimiters = allLeftHouseCnt + allRightHouseCnt + 1;
            int isolatedOrderedComb = multSeq(delimiters+1, delimiters+slots);
            combForConnected = mult(combForConnected, isolatedOrderedComb);
        }
        out.println(combForConnected);
    }

    private boolean dfs(boolean[] visited, List<List<Integer>> adj,
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
                boolean childHasOwnChilds = adj.get(childNode).size() > 1;
                if (childHasOwnChilds) {
                    childWithOwnChildCnt++;
                } else {
                    freeChildCnt++;
                }
                if (childWithOwnChildCnt > maxChildWithOwnChildCnt) {
                    return false;
                }
                boolean hasCorrectStructure = dfs(visited, adj, childNode, fromNode, !isLeftHouse, cmp);
                if (!hasCorrectStructure) return false;
            }
        }
        // save local parent--childs combinations count
        if (freeChildCnt > 0) {
            cmp.statesCnt = mult(cmp.statesCnt, getFactorial(freeChildCnt));
        }
        // if all checks is OK then hasCorrectStructure = true
        return true;
    }

    private static int mult(long a, long b) { return (int)((a * b) % MODULO); }

    private static int multSeq(int from, int to) {
        int res = 1;
        for(int cur = from; cur <= to; cur++) {
            res = mult(res, cur);
        }
        return res;
    }

    public static void preFactorial() {
        fact[0] = 1;
        for (int i = 1; i <= SIZE; i++) {
            fact[i] = mult(i, fact[i - 1]);
        }
    }

    public static int getFactorial(int a) {
        if (a <= SIZE) {
            return fact[a];
        }
        int res = fact[SIZE];
        for (int i = SIZE+1; i <= a; i++) {
            res = mult(i, res);
        }
        return res;
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
        int leftHouseCnt;
        int rightHouseCnt;
        int statesCnt = 1;
    }

}