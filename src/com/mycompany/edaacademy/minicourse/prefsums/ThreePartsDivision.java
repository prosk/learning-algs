package com.mycompany.edaacademy.minicourse.prefsums;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;
import java.util.StringTokenizer;

public class ThreePartsDivision {
    final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final static PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new ThreePartsDivision().runTests();
        out.close();
    }

    private void run() {
        // data input
        int len = readInt();
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = readInt();
        }

        int ans;
        ans = optSolve(len, arr);
        out.println(ans);

        ans = simpleSolve(len, arr);
        out.println(ans);
    }

    private void runTests() {
        int testCount = 500;
        int arrSize = 100_000; // 30;
        int maxValue = 1000;
        int arr[] = new int[arrSize];
        int ans1, ans2;
        Random rnd = new Random();
        out.println("Start testing");
        for(int i = 0; i < testCount; i++) {
            // Generate test array
            for(int j = 0; j < arrSize; j++) {
                arr[j] = -maxValue/2 + rnd.nextInt(maxValue) + 1;
            }
            ans1 = optSolve(arr.length, arr);
            ans2 = simpleSolve(arr.length, arr);
            // out.println(Arrays.toString(arr) + "  ansOpt = " + ans1 + " ansSimple = " + ans2);
            out.println("  ansOpt = " + ans1 + " ansSimple = " + ans2);
            if (ans1 != ans2) {
                out.println("Different answers for array: ");
                out.println(Arrays.toString(arr));
            }
        }
        out.println("Testing has completed!");
    }

    private int optSolve(int len, int[] arr) {
        // prefix sum calculation
        long[] pref = new long[len + 1];
        pref[0] = 0;
        for (int i = 1; i <= len; i++) {
            pref[i] = pref[i - 1] + arr[i - 1];
        }
        // result calculation
        long onePartSum = pref[len] / 3;
        if (onePartSum*3 != pref[len]) {
            return 0;
        }

        int leftCnt = 0;
        int ans = 0;
        for(int i = 1; i <= len-2; i++) {
            long leftSum = pref[i];
            if (leftSum == onePartSum) {
                leftCnt++;
            }
            long rightSum = pref[len] - pref[i+1];
            if (rightSum == onePartSum) {
                ans += leftCnt;
            }
        }
        return ans;
    }

    private int simpleSolve(int len, int[] arr) {
        // prefix sum calculation
        int[] pref = new int[len+1];
        pref[0] = 0;
        for(int i = 1; i <= len; i++) {
            pref[i] = pref[i-1] + arr[i-1];
        }
        // result calculation
        int onePartSum = pref[len] / 3;
        if (onePartSum*3 != pref[len]) {
            return 0;
        }

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
}
