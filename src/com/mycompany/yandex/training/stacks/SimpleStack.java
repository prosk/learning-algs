package com.mycompany.yandex.training.stacks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Locale;
import java.util.StringTokenizer;

public class SimpleStack {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new SimpleStack().run();
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
        Deque<Integer> myStack = new ArrayDeque<>();
        String currCommand;
        do {
            currCommand = readString();

            switch(currCommand) {
                case "push":
                    int newElem = readInt();
                    myStack.push(newElem);
                    out.println("ok");
                    break;
                case "pop":
                    if (myStack.isEmpty()) {
                        out.println("error");
                    } else {
                        int elem = myStack.pop();
                        out.println(elem);
                    }
                    break;
                case "back":
                    if (myStack.isEmpty()) {
                        out.println("error");
                    } else {
                        int elem = myStack.peek();
                        out.println(elem);
                    }
                    break;
                case "size":
                    out.println(myStack.size());
                    break;
                case "clear":
                    myStack.clear();
                    out.println("ok");
                    break;
                case "exit":
                    out.println("bye");
                    break;
            }

        }while(!currCommand.equals("exit"));
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

