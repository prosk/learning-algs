package com.mycompany.yandex.training10.lesson8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class DescendantCount {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new DescendantCount().run();
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
        countOfDescendants(root, childsMap, ans);
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

    void countOfDescendants(String parent, Map<String, List<String>> childsMap,
                            Map<String, Integer> ans) {
        List<String> childs = childsMap.get(parent);
        if (childs == null) {
            ans.put(parent, 0);
        } else {
            int cnt = 0;
            for(String child: childs) {
                countOfDescendants(child, childsMap, ans);
                cnt += ans.getOrDefault(child, 0) + 1;
            }
            ans.put(parent, cnt);
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