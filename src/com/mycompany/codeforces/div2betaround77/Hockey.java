// package com.mycompany.codeforces.div2betaround77;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.BitSet;
import java.util.StringTokenizer;

public class Hockey {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new Hockey().run();
        out.close();
    }

    void run() {
        int n = readInt();
        String subst[] = new String[n];
        for(int i = 0; i < n; i++) {
            subst[i] = readString();
        }
        String w = readString();
        String letterStr = readString();
        char letter = letterStr.charAt(0);
        char upperLetter = Character.toUpperCase(letter);

        // solution
        int wLen = w.length();
        BitSet bits = new BitSet(wLen);
        for(int i = 0; i < n; i++) {
            int sLen = subst[i].length();
            if (sLen > wLen) continue;
            for(int startPos = 0; startPos < (wLen - sLen + 1); startPos++) {
                if (w.substring(startPos, startPos + sLen).equalsIgnoreCase(subst[i])) {
                    bits.set(startPos, startPos + sLen, true);
                }
            }
        }
        StringBuilder ans = new StringBuilder("");
        for(int i = 0; i < wLen; i++) {
            char ch = w.charAt(i);
            if (bits.get(i)) {
                // replace
                if (Character.toLowerCase(ch) == letter) {
                    char newChar;
                    if (Character.toLowerCase(ch) == 'a') {
                        newChar = Character.isUpperCase(ch) ? 'B' : 'b';
                    } else {
                        newChar = Character.isUpperCase(ch) ? 'A' : 'a';
                    }
                    ans.append(newChar);
                } else {
                    // replace to letter
                    char newChar = Character.isUpperCase(ch) ? upperLetter : letter;
                    ans.append(newChar);
                }
            } else {
                // doesn't replace
                ans.append(ch);
            }
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