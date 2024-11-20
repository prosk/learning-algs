package com.mycompany.yandex.training60.trees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class BinaryLiftTreeLCA {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    int currTime = 0;
    int maxPowerOfTwo = 1;

    public static void main(String[] args) {
        new BinaryLiftTreeLCA().run();
        out.close();
    }

    void run() {
        int N = readInt();
        Map<Integer, List<Integer>> childsMap = new HashMap<>();
        Set<Integer> childsSet = new HashSet<>();
        Map<String, Integer> namesToNumbers = new HashMap<>();
        Map<Integer, String> numbersToNames = new HashMap<>();
        int num = 1;
        for(int i = 1; i <= N-1; i++) {
            String child = readString();
            String parent = readString();
            // transform names to numbers
            int parentNum = namesToNumbers.getOrDefault(parent, -1);
            if (parentNum == -1) {
                parentNum = num++;
                namesToNumbers.put(parent, parentNum);
                numbersToNames.put(parentNum, parent);
            }
            int childNum = namesToNumbers.getOrDefault(child, -1);
            if (childNum == -1) {
                childNum = num++;
                namesToNumbers.put(child, childNum);
                numbersToNames.put(childNum, child);
            }

            List<Integer> childList = childsMap.get(parentNum);
            if (childList == null) {
                List<Integer> list = new ArrayList<>();
                list.add(childNum);
                childsMap.put(parentNum, list);
            } else {
                childList.add(childNum);
            }
            childsSet.add(childNum);
        }
        // get root
        int rootNum = childsMap.keySet().stream().filter(x -> !childsSet.contains(x))
                .findFirst().get();
        // getting LCAs queries
        List<LcaQuery> queries = new ArrayList<>();
        String first = readString();
        String second = readString();
        while (first != null && second != null) {
            queries.add(new LcaQuery(namesToNumbers.get(first), namesToNumbers.get(second)));
            first = readString();
            second = readString();
        }
        // calculation by dfs
        int[] timeIn = new int[N+1];
        int[] timeOut = new int[N+1];
        timeIn[0] = 0;
        timeOut[0] = Integer.MAX_VALUE;

        while ((1 << maxPowerOfTwo) <= N) {
            maxPowerOfTwo++;
        }
        int[][] ancestors = new int[N+1][maxPowerOfTwo+1];
        precalcDFS(rootNum, 0, childsMap, timeIn, timeOut, ancestors);

        // getting LCAs
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < queries.size(); i++) {
            LcaQuery query = queries.get(i);
            int lca = getLCA(query.firstNum, query.secondNum, timeIn, timeOut, ancestors);
            sb.append(numbersToNames.get(lca));
            sb.append('\n');
        }
        out.print(sb);
    }

    int getLCA(int firstNum, int secondNum, int[] timeIn, int[] timeOut, int[][] ancestors) {
        if (firstNum == secondNum) {
            return firstNum;
        }
        if (isAncestor(firstNum, secondNum, timeIn, timeOut)) {
            return firstNum;
        }
        if (isAncestor(secondNum, firstNum, timeIn, timeOut)) {
            return secondNum;
        }
        int startNum = firstNum;
        for (int i = maxPowerOfTwo; i >= 0; i--) {
            if (!isAncestor(ancestors[startNum][i], secondNum, timeIn, timeOut))
                startNum = ancestors[startNum][i];
        }
        return ancestors[startNum][0];
    }

    boolean isAncestor(int i, int j, int[] timeIn, int[] timeOut) {
        return timeIn[i] <= timeIn[j] && timeOut[i] >= timeOut[j];
    }

    void precalcDFS(int node, int parent, Map<Integer, List<Integer>> childsMap,
                    int[] timeIn, int[] timeOut, int[][] ancestors) {
        currTime++;
        timeIn[node] = currTime;
        ancestors[node][0] = parent;
        for (int i = 1; i <= maxPowerOfTwo; i++) {
            ancestors[node][i] = ancestors[ancestors[node][i - 1]][i - 1];
        }
        List<Integer> childs = childsMap.get(node);
        if (childs != null) {
            for(Integer child: childs) {
                precalcDFS(child, node, childsMap, timeIn, timeOut, ancestors);
            }
        }
        currTime++;
        timeOut[node] = currTime;
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

    public static class LcaQuery {
        public int firstNum;
        public int secondNum;

        public LcaQuery(int firstNum, int secondNum) {
            this.firstNum = firstNum;
            this.secondNum = secondNum;
        }
    }
}