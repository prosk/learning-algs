package com.mycompany.edaacademy.minicourse.prefsums;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class SegmentSum {
    final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final static PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new SegmentSum().solve();
        out.close();
    }

    private void solve() {
        // data input
        int len = readInt();
        int targetSum = readInt();
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
        int left = 0, right = 0;
        int ans = 0;
        // invariant: left <= right
        while(right < len) {
            int currSum = pref[right+1] - pref[left];
            if (currSum == targetSum) {
                ans++;
                left++;
                right++;
            } else if (currSum < targetSum) {
                right++;
            } else { // currSum > targetSum
                left++;
                if (left > right) {
                    right = left;
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
