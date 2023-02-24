package com.mycompany.yandex.training.stacks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Locale;
import java.util.StringTokenizer;

public class RightBracketsSequence {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new RightBracketsSequence().run();
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
        Deque<Character> myStack = new ArrayDeque<>();
        String input = readString();
        if (input == null) {
            out.println("no");
            return;
        }
        for(int i = 0; i < input.length(); i++) {
            char currChar = input.charAt(i);
            if(currChar == '(' || currChar == '[' || currChar == '{') {
                myStack.push(currChar);
            } else {
                if (myStack.isEmpty()) {
                    out.println("no");
                    return;
                } else {
                    char lastChar = myStack.pop();
                    boolean isSameBrackets = (currChar == ')' && lastChar == '(') ||
                            (currChar == ']' && lastChar == '[') || (currChar == '}' && lastChar == '{');
                    if (!isSameBrackets) {
                        out.println("no");
                        return;
                    }
                }
            }
        }
        if (myStack.isEmpty()) {
            out.println("yes");
        } else {
            out.println("no");
        }
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
