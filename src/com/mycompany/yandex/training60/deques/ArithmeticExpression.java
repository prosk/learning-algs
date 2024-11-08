package com.mycompany.yandex.training60.deques;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class ArithmeticExpression {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new ArithmeticExpression().run();
        out.close();
    }

    void run() {
        String inputString = readLine().trim();
        int pos = 0;
        int balance = 0;
        // унарный плюс - можно игнорировать
        // унарный минус - добавить 0 слева
        List<String> tokens = new ArrayList<>();
        boolean isWrong = false;
        String prevToken = "";
        while(pos < inputString.length() && !isWrong) {
            char ch = inputString.charAt(pos);
            if (ch == '*') {
                tokens.add("*");
            } else if (ch == '+') {
                if (!"".equals(prevToken) && !"(".equals(prevToken)) {
                    // not unary plus
                    tokens.add("+");
                }
            } else if (ch == '-') {
                if ("".equals(prevToken) || "(".equals(prevToken)) {
                    // unary minus
                    tokens.add("0");
                }
                tokens.add("-");
            } else if (ch == '(') {
                balance++;
                tokens.add("(");
            } else if (ch == ')') {
                balance--;
                tokens.add(")");
            } else {
                // it must be a number without of spaces in it
                if (Character.isDigit(ch)) {
                    StringBuilder numToken = new StringBuilder();
                    numToken.append(ch);
                    while(pos+1 < inputString.length() && Character.isDigit(inputString.charAt(pos+1))) {
                        numToken.append(inputString.charAt(pos+1));
                        pos++;
                    }
                    tokens.add(numToken.toString());
                    isWrong = isWrong || isDigitToken(prevToken);
                } else if (ch != ' ') {
                    // incorrect token
                    isWrong = true;
                }
            }
            pos++;
            prevToken = tokens.isEmpty() ? "" : tokens.get(tokens.size()-1);
            isWrong = isWrong || (balance < 0);
        }
        if (isWrong || balance > 0) {
            out.println("WRONG");
            return;
        }
        for(String token: tokens) {
            out.println(token);
        }

    }

    boolean isDigitToken(String s) {
        return s.length() > 0 && Character.isDigit(s.charAt(0));
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