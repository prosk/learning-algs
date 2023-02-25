package com.mycompany.yandex.training.stacks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class LaylandMigration {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new LaylandMigration().run();
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

        int townsCnt = readInt();
        int[] towns = readIntArray(townsCnt);

        int[] res = new int[townsCnt];

        int i = 1;
        myStack.push(0);
        while (i < townsCnt) {
            int currElem = towns[i];
            int onTopElemIndex = myStack.peek();

            if (currElem < towns[onTopElemIndex]) {
                int currOnTopElemIndex;
                while(!myStack.isEmpty()) {
                    currOnTopElemIndex = myStack.peek();
                    if (currElem < towns[currOnTopElemIndex]) {
                        res[currOnTopElemIndex] = i;
                        myStack.pop();
                    } else {
                        break;
                    }
                }
            }
            myStack.push(i);
            i++;
        }

        /*out.print(res[0] == 0 ? -1 : res[0]);
        for(int j = 1; j < townsCnt; j++) {
            out.printf(" %d", res[j] == 0 ? -1 : res[j]);
        }*/

        StringBuilder sb = new StringBuilder("");
        sb.append(res[0] == 0 ? "-1" : res[0]);
        for(int j = 1; j < townsCnt; j++) {
            sb.append(" ");
            sb.append(res[j] == 0 ? "-1" : res[j]);
        }
        out.println(sb);
    }

    private int[] readIntArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; ++i) a[i] = readInt();
        return a;
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
