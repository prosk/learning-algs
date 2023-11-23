package com.mycompany.yandex.training40.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class MaxCut {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new MaxCut().run();
    }

    private void run() {
        try {
            solve();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void solve() {
        int N = readInt();

        // graph ds
        List<List<int[]>> adj = new ArrayList<>();
        for(int i = 0; i <= N; i++) {
            adj.add(new ArrayList<>());
        }

        // getting edges (u, v) and filling adj
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                int weight = readInt();
                if(i != j && weight != 0)
                    adj.get(i).add(new int[] {j, weight});
            }
        }

        int prevSum = 0;
        int maxSum = 0;
        int maxBitMask = 0;
        int maxGrayNum = ((1 << N) - 1) / 2;
        int prevGrayNum = 0; // all vertexes are in part = 0
        for(int i = 1; i <= maxGrayNum; i++) {
            int currGrayNum = i ^ (i >> 1);
            // out.println(Integer.toBinaryString(currGrayNum));
            int modifiedNum = prevGrayNum ^ currGrayNum;
            int prevPart = (prevGrayNum & modifiedNum) > 0 ? 1 : 0;
            int newPart = (currGrayNum & modifiedNum) > 0 ? 1 : 0;
            int modifiedVertex = findPosition(modifiedNum);
            // out.println("modified vertex = " + modifiedVertex + " prevPart " + prevPart + " newPart " + newPart);

            // recalc newSum from prevSum
            int newSum = prevSum;
            for(int[] u: adj.get(modifiedVertex)) {
                int currAdjPart = (currGrayNum & (1 << (u[0] - 1))) > 0 ? 1 : 0;
                if (currAdjPart == prevPart)
                    newSum += u[1];
                else
                    newSum -= u[1];
            }
            if (newSum > maxSum) {
                maxSum = newSum;
                maxBitMask = currGrayNum;
            }

            prevGrayNum = currGrayNum;
            prevSum = newSum;
        }
        out.println(maxSum);
        StringBuilder sb = new StringBuilder();
        int bitMask = 1;
        for(int i = 1; i <= N; i++) {
            int currPart = (maxBitMask & bitMask) > 0 ? 1 : 0;
            sb.append(currPart+1);
            if (i < N) sb.append(' ');
            bitMask = bitMask << 1;
        }
        out.println(sb);
    }

    private int findPosition(int n) {
        int i = 1, pos = 1;
        // Iterate through bits of n till we find a set bit
        // i&n will be non-zero only when 'i' and 'n' have a set bit
        // at same position
        while ((i & n) == 0) {
            // Unset current bit and set the next bit in 'i'
            i = i << 1;
            // increment position
            ++pos;
        }
        return pos;
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
