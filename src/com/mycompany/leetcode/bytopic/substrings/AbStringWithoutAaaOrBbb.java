package com.mycompany.leetcode.bytopic.substrings;

// https://leetcode.com/problems/string-without-aaa-or-bbb/editorial/?envType=problem-list-v2&envId=string&difficulty=MEDIUM
public class AbStringWithoutAaaOrBbb {

    public static void main(String[] args) {
        AbStringWithoutAaaOrBbb runner = new AbStringWithoutAaaOrBbb();
        String  s1 = runner.strWithout3a3b(7, 4);
        String  s2 = runner.strWithout3a3bBtfl(7, 4);
        System.out.println(s1);
        System.out.println(s2);
    }

    // my solution
    public String strWithout3a3b(int a, int b) {
        // a = 5 b = 1    aabaa  diff = 4
        // aabaabaabaabaabaa a = 12 b = 5 diff = 7
        // b   (b+1)*2
        // b = 100 a = 202
        // my solution
        int len = a + b;
        char[] ans = new char[len];
        int pos = 0;
        while (pos < len) {
            int diff = a - b;
            int aWriteCnt = (diff >= 2) ? 2 : 1;
            int bWriteCnt = (diff <= -2) ? 2 : 1;
            char prev = (pos == 0) ? '#' : ans[pos - 1];
            boolean writeA = (a > 0) &&
                (b == 0 || prev == 'b' || (prev == '#' && a >= b));

            if (writeA) {
                writeOneOrTwoSymbols(ans, pos, 'a', aWriteCnt);
                pos += aWriteCnt;
                a -= aWriteCnt;
            } else {
                writeOneOrTwoSymbols(ans, pos, 'b', bWriteCnt);
                pos += bWriteCnt;
                b -= bWriteCnt;
            }
        }
        return new String(ans);
    }

    private void writeOneOrTwoSymbols(char[] str, int pos, char ch, int cnt) {
        str[pos] = ch;
        if (cnt == 2) {
            str[pos+1] = ch;
        }
    }

    // editorial Greedy solution
    /*
        Approach 1: Greedy

        Intuition

        Intuitively, we should write the most common letter first. For example, if we have A = 6, B = 2,
        we want to write 'aabaabaa'. The only time we don't write the most common letter is if
        the last two letters we have written are also the most common letter

        Algorithm

        Let's maintain A, B: the number of 'a' and 'b''s left to write.

        If we have already written the most common letter twice, we'll write the other letter.
        Otherwise, we'll write the most common letter.
     */

    public String strWithout3a3bBtfl(int a, int b) {
        // editorial solution
        StringBuilder ans = new StringBuilder();
        while (a > 0 || b > 0) {
            boolean writeA = false;
            int len = ans.length();
            if (len >= 2 && ans.charAt(len-1) == ans.charAt(len-2)) {
                if (ans.charAt(len-1) == 'b')
                    writeA = true;
            } else {
                if (a >= b)
                    writeA = true;
            }

            if (writeA) {
                a--;
                ans.append('a');
            } else {
                b--;
                ans.append('b');
            }
        }
        return ans.toString();
    }
}
