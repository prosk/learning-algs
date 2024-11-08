package com.mycompany.yandex.training60.deques;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class MinimumOnSegment {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new MinimumOnSegment().run();
        out.close();
    }

    void run() {
        int n = readInt();
        int k = readInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) arr[i] = readInt();

        // solution
        Deque<Integer> minIndexes = new ArrayDeque<>();
        List<Integer> ans = new ArrayList<>();
        // for first k elements of arr - adding to deque
        for(int i = 0; i < k; i++) {
            // adding index i as a last element of deque
            while(!minIndexes.isEmpty() && arr[minIndexes.peekLast()] >= arr[i]) {
                minIndexes.pollLast();
            }
            minIndexes.addLast(i);
        }
        ans.add(arr[minIndexes.peekFirst()]);
        for(int i = k; i < n; i++) {
            // remove elems from prev window if it exists
            if (!minIndexes.isEmpty() && minIndexes.peekFirst() == i - k) {
                minIndexes.pollFirst();
            }
            // adding new elem to deque
            while(!minIndexes.isEmpty() && arr[minIndexes.peekLast()] >= arr[i]) {
                minIndexes.pollLast();
            }
            minIndexes.addLast(i);
            // saving first elem to answer
            ans.add(arr[minIndexes.peekFirst()]);
        }
        // generate string with ans
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < ans.size(); i++) {
            if (i > 0) sb.append('\n');
            sb.append(ans.get(i));
        }
        out.println(sb);
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