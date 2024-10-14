// package com.mycompany.codeforces.div2eduround170;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NewGame {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new NewGame().run();
        out.close();
    }

    void run() {
        int t = readInt();
        while (t > 0) {
            solve();
            t--;
        }
    }

    /*
    Solution from jiangly

    void solve() {
    int n, k;
    std::cin >> n >> k;

    std::vector<int> a(n);
    for (int i = 0; i < n; i++) {
        std::cin >> a[i];
    }
    std::sort(a.begin(), a.end());

    int ans = 0;

    for (int i = 0, j = 0; i < n; i++) {
        j = std::max(j, i);
        while (j + 1 < n && a[j + 1] <= a[j] + 1 && a[j + 1] < a[i] + k) {
            j++;
        }
        ans = std::max(ans, j - i + 1);
    }
    std::cout << ans << "\n";
    }
     */

    void solve() {
        int n = readInt();
        int k = readInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = readInt();
        }
        // two pointers
        Arrays.sort(arr);
        int diffCnt = 1;
        int prevDiff = 0;
        int right = 0, left = 0;
        int currCnt = 0, maxCnt = 0;
        while(left < n) {
            while(right < n && prevDiff <= 1 && diffCnt <= k) {
                currCnt = right - left + 1;
                maxCnt = Math.max(maxCnt, currCnt);
                right++;
                if (right == n) {
                    break;
                }
                prevDiff = arr[right] - arr[right-1];
                diffCnt += (arr[right] == arr[right-1] ? 0 : 1);
            }
            if (right == n) {
                break;
            }
            if (prevDiff <= 1 && diffCnt > k) {
                while(arr[left] == arr[left+1]) {
                    left++;
                }
                left = left + 1;
                diffCnt--;
            } else if (prevDiff > 1) {
                left = right;
                prevDiff = 0;
                diffCnt = 1;
            }
        }
        out.println(maxCnt);
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