package com.mycompany.codeforces.cp31sheet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CoverInWater {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new CoverInWater().runSimpleLogicWithObviousTimeComplexity();
        out.close();
    }

    void run() {
        int t = readInt();
        while(t-- > 0) {
            int n = readInt();
            String s = readString();
            String[] parts = s.split("#+");
            int ans = 0;
            for(String part: parts) {
                if (part.length() <= 2) {
                    ans += part.length();
                } else {
                    ans = 2;
                    break;
                }
            }
            out.println(ans);
        }
    }

    /*
       If there are 3 consecutive empty cells i−1, i, i+1, we can place water in cells i−1 and i+1
       and then move water from cell i to all other cells.
       If there are no such cells, we have to place water on every empty cell.

       So if we find substring ''...'' in the array, the answer is 2,
       otherwise the answer is the number of empty cells.

       Time and memory complexities are O(N).
     */
    void runSimpleLogicWithObviousTimeComplexity() {
        int t = readInt();
        while(t-- > 0) {
            int n = readInt();
            String s = readString();
            int ans = s.contains("...") ? 2 : charCnt(s, '.');
            out.println(ans);
        }
    }

    int charCnt(String s, char ch) {
        int cnt = 0;
        for(int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ch) cnt++;
        }
        return cnt;
    }

    boolean isArraySorted(int[] arr) {
        for(int i = 0; i < arr.length-1; i++) {
            if (arr[i] > arr[i+1]) return false;
        }
        return true;
    }

    int readInt() {
        return Integer.parseInt(readString());
    }

    String readString() {
        while(!tok.hasMoreTokens()) {
            String line = readLine();
            if (line == null) return null;
            tok = new StringTokenizer(line);
        }
        return tok.nextToken();
    }

    String readLine() {
        try {
            return br.readLine();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
