package com.mycompany.yandex.training60.deques;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class StackWithSum {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    static final long[][] stack = new long[100_002][2];

    public static void main(String[] args) {
        new StackWithSum().run();
        out.close();
    }

    void run() {
        stack[0][0] = 0L;
        stack[0][1] = 0L;

        int opsCnt = readInt();
        List<Long> ans = new ArrayList<>();
        int stackCnt = 0;
        while(opsCnt-- > 0) {
            String op = readString();
            char firstChar = op.charAt(0);
            if (firstChar == '+') {
                long num = Long.parseLong(op.substring(1));
                stackCnt++;
                stack[stackCnt][0] = num;
                stack[stackCnt][1] = stack[stackCnt-1][1] + num;
            } else if (firstChar == '-') {
                ans.add(stack[stackCnt][0]);
                stackCnt--;
            } else {
                int k = Integer.parseInt(op.substring(1));
                long sum = stack[stackCnt][1] - stack[stackCnt-k][1];
                ans.add(sum);
            }
        }
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