package com.mycompany.leetcode.bytopic.strings;

import java.util.HashMap;
import java.util.Map;

public class FirstUniqueCharacter {
    // editorial solution: 2 * s.length()
    public int firstUniqChar(String s) {
        HashMap<Character, Integer> count = new HashMap<>();
        int n = s.length();
        // build hash map : character and how often it appears
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        // find the index
        for (int i = 0; i < n; i++) {
            if (count.get(s.charAt(i)) == 1)
                return i;
        }
        return -1;
    }

    // more optimal solution: s.length() + NumberOfUniqueCharacters
    public int firstUniqCharOpt(String s) {
        // map: ch -> state
        // state = pos + 1 - встретили символ 1-ый раз на позиции pos
        // state = -(pos - 1) - встретили символ 2-ой раз, значение меняем на отриц
        // тогда нам нужно пройтись по мапе и найти где min значение > 0
        Map<Character, Integer> seen = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            seen.merge(s.charAt(i), i + 1, (v1, v2) -> v1 > 0 ? -v1 : v1);
        }
        int minPos = s.length() + 1;
        for(Integer state: seen.values()) {
            if (state > 0 && state < minPos) {
                minPos = state;
            }
        }
        return minPos == s.length() + 1 ? -1 : minPos - 1;
    }
}
