package com.mycompany.yandex.training.stacks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class CarriageSortLite {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new CarriageSortLite().run();
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
        Map<Integer, Integer> valuesToIndexes = new HashMap<>();

        int carriageCnt = readInt();
        int[] carriages = readIntArray(carriageCnt);

        int i = 1;
        myStack.push(0);
        while (i < carriageCnt) {
            int currElem = carriages[i];
            int onTopElemIndex = myStack.peek();

            if (currElem > carriages[onTopElemIndex]) {
                int currOnTopElemIndex;
                while(!myStack.isEmpty()) {
                    currOnTopElemIndex = myStack.peek();
                    if (currElem > carriages[currOnTopElemIndex]) {
                        valuesToIndexes.put(carriages[currOnTopElemIndex], i);
                        myStack.pop();
                    } else {
                        break;
                    }
                }
            }
            myStack.push(i);
            i++;
        }

        // valuesToIndexes.forEach((k, v) -> out.println("k = " + k + " v = " + v));

        if (!valuesToIndexes.isEmpty()) {
            for(Integer elem: valuesToIndexes.keySet()) {
                int idx = valuesToIndexes.get(elem);
                for(int j = idx+1; j < carriageCnt; j++) {
                    if (carriages[j] < elem) {
                        out.println("NO");
                        return;
                    }
                }
            }
        }
        out.println("YES");
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


