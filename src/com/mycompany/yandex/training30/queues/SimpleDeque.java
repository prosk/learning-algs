package com.mycompany.yandex.training30.queues;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Locale;
import java.util.StringTokenizer;

public class SimpleDeque {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new SimpleDeque().run();
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
        Deque<Integer> myDeque = new ArrayDeque<>();
        String currCommand;
        do {
            currCommand = readString();

            int newElem;
            switch(currCommand) {
                case "push_front":
                    newElem = readInt();
                    myDeque.addFirst(newElem);
                    out.println("ok");
                    break;
                case "push_back":
                    newElem = readInt();
                    myDeque.addLast(newElem);
                    out.println("ok");
                    break;
                case "pop_front":
                    if (myDeque.isEmpty()) {
                        out.println("error");
                    } else {
                        int elem = myDeque.pollFirst();
                        out.println(elem);
                    }
                    break;
                case "pop_back":
                    if (myDeque.isEmpty()) {
                        out.println("error");
                    } else {
                        int elem = myDeque.pollLast();
                        out.println(elem);
                    }
                    break;
                case "front":
                    if (myDeque.isEmpty()) {
                        out.println("error");
                    } else {
                        int elem = myDeque.peekFirst();
                        out.println(elem);
                    }
                    break;
                case "back":
                    if (myDeque.isEmpty()) {
                        out.println("error");
                    } else {
                        int elem = myDeque.peekLast();
                        out.println(elem);
                    }
                    break;
                case "size":
                    out.println(myDeque.size());
                    break;
                case "clear":
                    myDeque.clear();
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
