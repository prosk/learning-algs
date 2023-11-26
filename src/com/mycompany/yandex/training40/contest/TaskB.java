package com.mycompany.yandex.training40.contest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class TaskB {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new TaskB().run();
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
        String inpStringArr = readString();
        int len = N;

        long[][] hashParams = {{ 257, 1_000_000_007}, {31, 433_494_437}};

        HashPrefix hpForward = new HashPrefix(inpStringArr, hashParams[0][0], hashParams[0][1], false);
        HashPrefix hpReverse = new HashPrefix(inpStringArr, hashParams[0][0], hashParams[0][1], true);

        int[] z = new int[len + 1];
        z[1] = 1;
        for (int startPos = 2; startPos <= len; startPos++) {
            z[startPos] = calcZFunction(hpForward, hpReverse, startPos, len);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= len; i++) {
            sb.append(z[i]);
            if (i < len) sb.append(' ');
        }
        out.println(sb);


    }

    private int calcZFunction(HashPrefix hpForward, HashPrefix hpReverse, int startPos, int len) {
        int ans = 0;
        int l = 1, r = startPos;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (isSubstringEquals(hpForward, hpReverse, 1, len-startPos+1, mid)) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
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

    public boolean isSubstringEquals(HashPrefix hpForward, HashPrefix hpReverse, int forwardFrom1, int reverseFrom2,
                                     int slen) {
        long v1 = (hpForward.h[forwardFrom1 + slen - 1] + hpReverse.h[reverseFrom2 - 1] * hpReverse.x[slen]) % hpReverse.modulo;

        long v2 = (hpReverse.h[reverseFrom2 + slen - 1] + hpForward.h[forwardFrom1 - 1] * hpForward.x[slen]) % hpForward.modulo;

        return v1 == v2;
    }

    public static class HashPrefix {
        public long modulo;
        public long[] x;
        public long[] h;

        public HashPrefix(String stringArr, long point, long modulo, boolean isReverse) {
            this.modulo = modulo;
            int len = stringArr.length();
            x = new long[len + 1];
            h = new long[len + 1];
            x[0] = 1;
            for (int i = 1; i <= len; i++) {
                int stringInd = isReverse ? len - i : i - 1;
                h[i] = (h[i - 1] * point + (long) stringArr.charAt(stringInd)) % modulo;
                x[i] = (x[i - 1] * point) % modulo;
            }
        }
    }

}
