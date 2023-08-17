package com.mycompany.edaacademy.minicourse.prefsums;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class ThreePartsDivision {
    final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final static PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new ThreePartsDivision().optSolve();
        out.close();
    }

    private void optSolve() {
        // data input
        int len = readInt();
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = readInt();
        }
        // prefix sum calculation
        long[] pref = new long[len + 1];
        pref[0] = 0;
        for (int i = 1; i <= len; i++) {
            pref[i] = pref[i - 1] + arr[i - 1];
        }
        // result calculation
        long onePartSum = pref[len] / 3;
        if (onePartSum*3 != pref[len]) {
            out.println(0);
            return;
        }

        int leftCnt = 0;
        int ans = 0;
        for(int i = 1; i <= len-2; i++) {
            long leftSum = pref[i];
            long leftOtherSum = pref[len] - leftSum;
            if (leftSum == onePartSum /*&& leftOtherSum == 2*onePartSum*/) {
                leftCnt++;
            }
            long rightSum = pref[len] - pref[i+1];
            long rightOtherSum = pref[i+1];
            if (rightSum == onePartSum /*&& rightOtherSum == 2*onePartSum*/) {
                ans += leftCnt;
            }
        }
        out.println(ans);
    }

    private void solve() {
        // data input
        int len = readInt();
        int[] arr = new int[len];
        for(int i = 0; i < len; i++) {
            arr[i] = readInt();
        }
        // prefix sum calculation
        int[] pref = new int[len+1];
        pref[0] = 0;
        for(int i = 1; i <= len; i++) {
            pref[i] = pref[i-1] + arr[i-1];
        }
        // result calculation
        int onePartSum = pref[len] / 3;
        int ans = 0;
        // left = index of the first element for middle part
        // right = index of the last element for middle part
        for(int left = 1; left <= len-2; left++) {
            int currSum1 = pref[left];
            if (currSum1 == onePartSum) {
                for(int right = left; right <= len-2; right++) {
                    int currSum2 = pref[right+1] - pref[left];
                    int currSum3 = pref[len] - pref[right+1];
                    if (currSum2 == onePartSum && currSum3 == onePartSum) {
                        ans++;
                    }
                }
            }
        }
        out.println(ans);
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
