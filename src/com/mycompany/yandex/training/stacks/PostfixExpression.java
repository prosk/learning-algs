package com.mycompany.yandex.training.stacks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Locale;
import java.util.StringTokenizer;

public class PostfixExpression {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new PostfixExpression().run();
    }

    private void run() {
        try {
            long timeStart = System.currentTimeMillis();
            solve();
            out.close();
            long timeEnd = System.currentTimeMillis();
            System.err.println("Time(ms) = " + (timeEnd - timeStart));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void solve() {
        Deque<Integer> exprStack = new ArrayDeque<>();
        String currToken = readString();
        while (currToken != null) {
            if (currToken.equals("*") || currToken.equals("+")|| currToken.equals("-")) {
                int secondOp = exprStack.pop();
                int firstOp = exprStack.pop();
                int res = currToken.equals("*") ? firstOp*secondOp :
                        (currToken.equals("+") ? firstOp+secondOp : firstOp-secondOp);
                exprStack.push(res);
            } else {
                int currOp = Integer.parseInt(currToken);
                exprStack.push(currOp);
            }
            currToken = readString();
        }
        int exprValue = exprStack.pop();
        out.println(exprValue);
    }

    private int readInt() {
        return Integer.parseInt(readString());
    }

    private String readString() {
        while (!tok.hasMoreTokens()) {
            String nextLine = readLine();
            if (null == nextLine) return null;
            tok = new StringTokenizer(nextLine);
        }

        return tok.nextToken();
    }

    private String readLine() {
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

