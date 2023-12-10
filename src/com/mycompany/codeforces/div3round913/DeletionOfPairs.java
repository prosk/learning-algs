package com.mycompany.codeforces.div3round913;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class DeletionOfPairs {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    public static void main(String[] args) throws Exception {
        int t = readInt();
        for(; t > 0; t--) {
            int n = readInt();
            String s = br.readLine();
            int[] freq = new int[26];
            for(int i = 0; i < n; i++) {
                freq[(int)s.charAt(i)-97]++;
            }
            int max = Arrays.stream(freq).max().getAsInt();
            int ans = Math.max(n % 2, n - 2*(n - max));
            out.println(ans);
        }
        out.close();
    }

    static int readInt() throws Exception {
        return Integer.parseInt(br.readLine());
    }
}
