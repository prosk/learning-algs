package com.mycompany.yandex.training60.deques;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class MinPsp {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new MinPsp().run();
        out.close();
    }

    void run() {
        int n = readInt();
        String w = readString();
        String s = readString();
        if (s == null) {
            s = "";
        }

        int currLeft = 0, currRight = 0;
        Deque<Character> opened = new ArrayDeque<>();

        char firstOpened = '*';
        Map<Character, Integer> charOrder = new HashMap<>();
        for(int i = 0; i < w.length(); i++) {
            char ch = w.charAt(i);
            if ((ch == '(' || ch == '[') && firstOpened == '*') {
                firstOpened = ch;
            }
            charOrder.put(ch, i);
        }

        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(' || ch == '[') {
                currLeft++;
                opened.addLast(ch);
            } else {
                currRight++;
                opened.pollLast();
            }
        }

        StringBuilder ans = new StringBuilder(s);
        char nextSymbol = '*';
        while(ans.length() < n) {
            if (currLeft == currRight) {
                // можем ставить только открывающую скобку
                nextSymbol = firstOpened;
                currLeft++;
                opened.addLast(firstOpened);
            } else if (currLeft > currRight) {
                if (currLeft < n / 2) {
                    // можем ставить любую скобку
                    char lastOpened = opened.peekLast();
                    char nextClosed = (lastOpened == '(') ? ')' : ']';
                    if (charOrder.get(firstOpened) < charOrder.get(nextClosed)) {
                        nextSymbol = firstOpened;
                        currLeft++;
                        opened.addLast(firstOpened);
                    } else {
                        nextSymbol = nextClosed;
                        opened.pollLast();
                        currRight++;
                    }
                } else {
                    // можем ставить только закрывающую скобку
                    char lastOpened = opened.pollLast();
                    nextSymbol = (lastOpened == '(') ? ')' : ']';
                    currRight++;
                }
            }
            ans.append(nextSymbol);
        }
        out.println(ans);
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