package com.mycompany.postupashki.yandex;

public class LcTaskStringCompression443 {

    public static void main(String[] args) {
        Solution slt = new Solution();
        EditorialSolution edSlt = new EditorialSolution();
        char[] chars = "aabbbcccccrtggg".toCharArray();
        System.out.println(slt.compress(chars) + " " + new String(chars));

        chars = "aabbbcccccrtggg".toCharArray();
        System.out.println(edSlt.compress(chars) + " " + new String(chars));
    }

    private static class Solution {
        public int compress(char[] chars) {
            int rPos = 0, wPos = 0;
            while(rPos < chars.length) {
                int savedRPos = rPos;
                while(rPos+1 < chars.length && chars[rPos+1] == chars[rPos]) {
                    rPos++;
                }
                wPos = writeGroup(chars, wPos, chars[savedRPos], rPos - savedRPos + 1);
                rPos++;
            }
            return wPos;
        }

        private int writeGroup(char[] chars, int wPos, char ch, int cnt) {
            chars[wPos] = ch;
            if (cnt == 1) return wPos+1;
            String cntStr = String.valueOf(cnt);
            for(int i = 0; i < cntStr.length(); i++) {
                chars[wPos + 1 + i] = cntStr.charAt(i);
            }
            return wPos + 1 + cntStr.length();
        }
    }

    private static class EditorialSolution {
        public int compress(char[] chars) {
            // более лаконичное и красивое решение с той же асимптотикой из Editorial
            // i = индекс первого символа текущей группы
            // res = текущая длина результата, то есть сжатой строки
            int i = 0, res = 0;
            while(i < chars.length) {
                int groupLength = 1;
                while(i + groupLength < chars.length && chars[i + groupLength] == chars[i]) {
                    groupLength++;
                }
                chars[res++] = chars[i];
                if (groupLength > 1) {
                    for(char c: Integer.toString(groupLength).toCharArray()) {
                        chars[res++] = c;
                    }
                }
                i += groupLength;
            }
            return res;
        }
    }
}
