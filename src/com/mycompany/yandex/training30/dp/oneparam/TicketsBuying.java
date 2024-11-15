package com.mycompany.yandex.training30.dp.oneparam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class TicketsBuying {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new TicketsBuying().run();
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
        int buyersCnt = readInt();

        int dp[] = new int[buyersCnt + 1 + 3];
        int A[] = new int[buyersCnt + 1 + 3];
        int B[] = new int[buyersCnt + 1 + 3];
        int C[] = new int[buyersCnt + 1 + 3];

        for(int i = 1; i <= buyersCnt; i++) {
            A[i+3] = readInt();
            B[i+3] = readInt();
            C[i+3] = readInt();
        }

        for(int i = 1; i <= 3; i++) {
            A[i] = Integer.MAX_VALUE;
            B[i] = Integer.MAX_VALUE;
            C[i] = Integer.MAX_VALUE;
            dp[i] = 0;
        }

        for(int i = 1; i <= buyersCnt; i++) {
            int j = i + 3;
            long oneTicket = dp[j-1] + (long) A[j];
            long twoTicket = dp[j-2] + (long) B[j-1];
            long threeTicket = dp[j-3] + (long) C[j-2];
            dp[j] = (int) Math.min(Math.min(oneTicket, twoTicket), threeTicket);
        }
        out.println(dp[buyersCnt+3]);
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

