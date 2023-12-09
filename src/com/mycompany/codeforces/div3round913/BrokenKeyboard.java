package com.mycompany.codeforces.div3round913;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class BrokenKeyboard {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new BrokenKeyboard().run();
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
        int t = readInt();
        String[] inp = new String[t];
        for(int i = 0; i < t; i++)
            inp[i] = readString();

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < t; i++) {
            String ans = getAns(inp[i]);
            sb.append(ans);
            sb.append('\n');
        }
        out.print(sb);
    }

    private String getAns(String inp) {
        Deque<Pair> lowerStack = new ArrayDeque<>();
        Deque<Pair> upperStack = new ArrayDeque<>();

        for(int i = 0; i < inp.length(); i++) {
            char curr = inp.charAt(i);
            int currInt = (int)curr;
            if (currInt >= 97 && currInt <= 122 && currInt != 98) {
                // a-z and not b
                lowerStack.addLast(new Pair(i, curr));
            } else if (currInt >= 65 && currInt <= 90 && currInt != 66) {
                // A-Z and not B
                upperStack.addLast(new Pair(i, curr));
            } else if (currInt == 98) {
                // b
                if (!lowerStack.isEmpty())
                    lowerStack.pollLast();
            } else if (currInt == 66) {
                // B
                if (!upperStack.isEmpty())
                    upperStack.pollLast();
            }
        }
        // merge stacks to get answer
        StringBuilder ans = new StringBuilder();
        while (!lowerStack.isEmpty() || !upperStack.isEmpty()) {
            int currLowerPos = !lowerStack.isEmpty() ? lowerStack.peekFirst().idx : Integer.MAX_VALUE;
            int currUpperPos = !upperStack.isEmpty() ? upperStack.peekFirst().idx : Integer.MAX_VALUE;
            if (currLowerPos < currUpperPos) {
                ans.append(lowerStack.pollFirst().chr);
            } else {
                ans.append(upperStack.pollFirst().chr);
            }
        }
        return ans.toString();
    }

    private int chrToNum(char chr) {
        return (int)chr - 96;
    }
    private char numToChr(int num) {
        return (char)(num + 96);
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

    private static class Pair {
        public int idx;
        public char chr;

        public Pair(int idx, char chr) {
            this.idx = idx;
            this.chr = chr;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return idx == pair.idx && chr == pair.chr;
        }

        @Override
        public int hashCode() {
            return Objects.hash(idx, chr);
        }
    }

}
