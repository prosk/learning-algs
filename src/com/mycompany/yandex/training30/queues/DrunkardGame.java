package com.mycompany.yandex.training30.queues;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Locale;
import java.util.StringTokenizer;

public class DrunkardGame {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new DrunkardGame().run();
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
        Deque<Integer> firstQueue = new ArrayDeque<>();
        Deque<Integer> secondQueue = new ArrayDeque<>();

        for(int i = 0; i < 5; i++) {
            firstQueue.addLast(readInt());
        }

        for(int i = 0; i < 5; i++) {
            secondQueue.addLast(readInt());
        }

        int moveCount = 1, topFirst, topSecond;
        while(moveCount <= 1_000_000) {
            topFirst = firstQueue.pollFirst();
            topSecond = secondQueue.pollFirst();

            if ((topFirst > topSecond && !(topFirst == 9 && topSecond == 0)) || (topFirst == 0 && topSecond == 9)) {
                firstQueue.addLast(topFirst);
                firstQueue.addLast(topSecond);
            } else {
                secondQueue.addLast(topFirst);
                secondQueue.addLast(topSecond);
            }

            if(firstQueue.isEmpty()) {
                out.println("second " + moveCount);
                return;
            }

            if(secondQueue.isEmpty()) {
                out.println("first " + moveCount);
                return;
            }

            moveCount++;
        }
        out.println("botva");
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

