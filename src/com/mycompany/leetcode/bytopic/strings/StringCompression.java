package com.mycompany.leetcode.bytopic.strings;

// 443 String Compression

/*
Given an array of characters chars, compress it using the following algorithm:

Begin with an empty string s. For each group of consecutive repeating characters in chars:

If the group's length is 1, append the character to s.
Otherwise, append the character followed by the group's length.
The compressed string s should not be returned separately, but instead, be stored in the input character array chars. Note that group lengths that are 10 or longer will be split into multiple characters in chars.

After you are done modifying the input array, return the new length of the array.

You must write an algorithm that uses only constant extra space.

Example 1:

Input: chars = ["a","a","b","b","c","c","c"]
Output: Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
Explanation: The groups are "aa", "bb", and "ccc". This compresses to "a2b2c3".
Example 2:

Input: chars = ["a"]
Output: Return 1, and the first character of the input array should be: ["a"]
Explanation: The only group is "a", which remains uncompressed since it's a single character.
Example 3:

Input: chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
Output: Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].
Explanation: The groups are "a" and "bbbbbbbbbbbb". This compresses to "ab12".
 */

public class StringCompression {
    public int compress(char[] chars) {
        // мое верное решение
        int prevChar = -1, currLen = 0, writePos = 0;
        for(int readPos = 0; readPos <= chars.length; readPos++) {
            int currChar = (readPos == chars.length) ? -2 : (int)chars[readPos];
            if (currChar == prevChar || currLen == 0) {
                currLen++;
            } else {
                if (currLen == 1) {
                    chars[writePos] = (char)prevChar;
                    writePos++;
                } else {
                    String compressed = (char)prevChar + String.valueOf(currLen);
                    System.arraycopy(compressed.toCharArray(), 0, chars, writePos, compressed.length());
                    writePos += compressed.length();
                }
                currLen = 1;
            }
            prevChar = currChar;
        }
        return writePos;
    }

    public int compressEd(char[] chars) {
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
