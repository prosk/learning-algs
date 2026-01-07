package com.mycompany.codeforces.hello2026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class YetAnotherMexTask {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new YetAnotherMexTask().run();
        out.close();
    }

    void run() {
        int t = readInt();
        while (t-- > 0) {
            solve();
        }
    }

    void solve() {
        int n = readInt();
        int k = readInt();

        int[] freq = new int[n+1];
        for(int i = 0; i < n; i++) {
            int elem = readInt();
            freq[elem]++;
        }
        // solution
        int toDeleteCnt = n - k + 1;
        int currMax = 0;
        boolean zeroFreq = false;
        for(int i = 0; i <= n; i++) {
            if (freq[i] == 0 && !zeroFreq) {
                currMax = i;
                zeroFreq = true;
            }
            if (freq[i] > 1 && !zeroFreq) {
                int canDelete = Math.min(freq[i] - 1, toDeleteCnt);
                toDeleteCnt -= canDelete;
                freq[i] -= canDelete;
            }
            if (zeroFreq && freq[i] > 0) {
                int canDelete = Math.min(freq[i], toDeleteCnt);
                toDeleteCnt -= canDelete;
                freq[i] -= canDelete;
            }
        }
        // либо toDeleteCnt = 0 либо перебраны все n
        int ans = 0;
        if (toDeleteCnt > 0) {
            // удаляем от большего к меньшему
            for(int i = n; i >= 0; i--) {
                if (freq[i] > 0) {
                    int canDelete = Math.min(freq[i], toDeleteCnt);
                    toDeleteCnt -= canDelete;
                    freq[i] -= canDelete;
                    if (freq[i] == 0) {
                        currMax = Math.min(currMax, i);
                    }
                }
                if (toDeleteCnt == 0) break;
            }
            ans = Math.min(currMax, k-1);
        } else {
            ans = Math.min(currMax, k-1);
        }
        out.println(ans);
    }

    int readInt() {
        return Integer.parseInt(readString());
    }

    String readString() {
        while (!tok.hasMoreTokens()) {
            String nextLine = readLine();
            if (null == nextLine) return null;
            tok = new StringTokenizer(nextLine);
        }
        return tok.nextToken();
    }

    String readLine() {
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}