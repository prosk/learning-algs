package com.mycompany.yandex.training40.warmup;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AnagramChecker {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();
        int len1 = s1.length();
        int len2 = s2.length();

        if (len1 != len2) {
            System.out.println("NO");
            return;
        }

        int[] diff = new int[26];
        // 97 - код строчной латинской буквы a
        for(int i = 0; i < len1; i++) {
            int c1 = (int) s1.charAt(i) - 97;
            int c2 = (int) s2.charAt(i) - 97;
            diff[c1]++;
            diff[c2]--;
        }

        for(int j = 0; j < 26; j++) {
            if (diff[j] != 0) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }
}
