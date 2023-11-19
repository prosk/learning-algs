package com.mycompany.yandex.training40.stringhash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

public class CubesInMirror {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new CubesInMirror().run();
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
        int N = readInt();
        int M = readInt();
        int[] inpStringArr = new int[N];

        for(int i = 0; i < N; i++)
            inpStringArr[i] = readInt();

        long[][] hashParams = {{ 1_046_527, 1_000_000_007}, {31, 433_494_437}};

        HashPrefix hpForward = new HashPrefix(inpStringArr, hashParams[0][0], hashParams[0][1], false);
        HashPrefix hpReverse = new HashPrefix(inpStringArr, hashParams[0][0], hashParams[0][1], true);

        List<Integer> ans = new ArrayList<>();
        ans.add(N);
        for(int leftCnt = 1; leftCnt <= N/2; leftCnt++) {
            if (isSubstringEquals(hpForward, hpReverse, leftCnt, N)) {
                ans.add(N - leftCnt);
            }
        }

        StringBuilder ansStr = new StringBuilder();
        for(int i = ans.size()-1; i >= 0; i--) {
            ansStr.append(ans.get(i));
            if (i > 0) ansStr.append(' ');
        }
        out.println(ansStr);
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

    public boolean isSubstringEquals(HashPrefix hpForward, HashPrefix hpReverse, int leftCnt, int N) {
        int slen = leftCnt;
        int forwardFrom1 = leftCnt + 1;
        int reverseFrom2 = N - leftCnt + 1;
        long v1 = (hpForward.h[forwardFrom1 + slen - 1] + hpReverse.h[reverseFrom2 - 1] * hpReverse.x[slen]) % hpReverse.modulo;

        long v2 = (hpReverse.h[reverseFrom2 + slen - 1] + hpForward.h[forwardFrom1 - 1] * hpForward.x[slen]) % hpForward.modulo;

        return v1 == v2;
    }

    public static class HashPrefix {
        public long modulo;
        public long[] x;
        public long[] h;

        public HashPrefix(int[] stringArr, long point, long modulo, boolean isReverse) {
            this.modulo = modulo;
            int len = stringArr.length;
            x = new long[len + 1];
            h = new long[len + 1];
            x[0] = 1;
            for (int i = 1; i <= len; i++) {
                int stringInd = isReverse ? len - i : i - 1;
                h[i] = (h[i - 1] * point + (long) stringArr[stringInd]) % modulo;
                x[i] = (x[i - 1] * point) % modulo;
            }
        }
    }

}
