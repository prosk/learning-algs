package com.mycompany.yandex.training10.lesson8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class HeightCount {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new HeightCount().run();
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
        // calculation
        Map<String, Integer> ans = new HashMap<>();
        countOfHeight(root, childsMap, ans, 0);
        List<String> sorted = new ArrayList<>();
        for (String s : ans.keySet()) {
            sorted.add(s);
        }
        sorted.sort(null);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < sorted.size(); i++) {
            if (i > 0) sb.append('\n');
            sb.append(sorted.get(i));
            sb.append(' ');
            sb.append(ans.get(sorted.get(i)));
        }
        out.println(sb);
    }

    void countOfHeight(String parent, Map<String, List<String>> childsMap,
                            Map<String, Integer> ans, int height) {
        ans.put(parent, height);
        List<String> childs = childsMap.get(parent);
        if (childs != null) {
            for(String child: childs) {
                countOfHeight(child, childsMap, ans, height+1);
            }
        }
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