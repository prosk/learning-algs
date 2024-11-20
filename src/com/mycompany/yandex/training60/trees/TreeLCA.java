package com.mycompany.yandex.training60.trees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class TreeLCA {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    private String lcaNode = "";
    private int maxLevel = 0;

    public static void main(String[] args) {
        new TreeLCA().run();
        out.close();
    }

    void run() {
        int N = readInt();
        Map<String, List<String>> childsMap = new HashMap<>();
        Set<String> childsSet = new HashSet<>();
        for(int i = 1; i <= N-1; i++) {
            String child = readString();
            String parent = readString();
            List<String> childList = childsMap.get(parent);
            if (childList == null) {
                List<String> list = new ArrayList<>();
                list.add(child);
                childsMap.put(parent, list);
            } else {
                childList.add(child);
            }
            childsSet.add(child);
        }
        // get root
        String root = childsMap.keySet().stream().filter(x -> !childsSet.contains(x))
                .findFirst().get();
        // getting LCAs
        String first = readString();
        String second = readString();
        StringBuilder sb = new StringBuilder();
        while (first != null && second != null) {
            if (first.equals(second)) {
                sb.append(first);
            } else {
                maxLevel = 0;
                lcaNode = root;
                int res = getCntInSubtree(root, childsMap, first, second, 1);
                sb.append(lcaNode);
            }
            sb.append('\n');
            first = readString();
            second = readString();
        }
        out.print(sb);
    }

    private int getCntInSubtree(String parent, Map<String, List<String>> childsMap,
                                String first, String second, int level) {
        List<String> childs = childsMap.get(parent);
        int ans = 0;
        if (parent.equals(first) || parent.equals(second)) {
            ans++;
        }
        if (childs != null) {
            for(String child: childs) {
                int cnt = getCntInSubtree(child, childsMap, first, second, level+1);
                ans = ans + cnt;
            }
        }
        if (ans == 2 && level > maxLevel) {
            maxLevel = level;
            lcaNode = parent;
        }
        return ans;
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
}