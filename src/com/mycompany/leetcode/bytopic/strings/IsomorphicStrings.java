package com.mycompany.leetcode.bytopic.strings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/isomorphic-strings/?envType=company&envId=yandex&favoriteSlug=yandex-thirty-days
public class IsomorphicStrings {

    // my first solution
    public boolean isIsomorphic(String s, String t) {
        int len = s.length();
        Map<Character, Character> replaces = new HashMap<>();
        Map<Character, Character> backReplaces = new HashMap<>();

        for(int i = 0; i < len; i++) {
            Character first = s.charAt(i);
            Character second = t.charAt(i);

            Character replace = replaces.get(first);

            if (replace == null) {
                Character mappedChar = backReplaces.get(second);
                if (mappedChar != null && mappedChar != first) {
                    return false;
                }
                replaces.put(first, second);
                backReplaces.put(second, first);
            } else {
                if (second != replace) {
                    return false;
                }
            }
        }
        return true;
    }

    // editorial solution with array
    public boolean isIsomorphicEdWithArray(String s, String t) {
        int[] mappingDictStoT = new int[256];
        Arrays.fill(mappingDictStoT, -1);

        int[] mappingDictTtoS = new int[256];
        Arrays.fill(mappingDictTtoS, -1);

        for (int i = 0; i < s.length(); ++i) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            // Case 1: No mapping exists in either of the dictionaries
            if (mappingDictStoT[c1] == -1 && mappingDictTtoS[c2] == -1) {
                mappingDictStoT[c1] = c2;
                mappingDictTtoS[c2] = c1;
            }
            // Case 2: Either mapping doesn't exist in one of the dictionaries or Mapping exists and
            // it doesn't match in either of the dictionaries or both
            else if (
                !(mappingDictStoT[c1] == c2 && mappingDictTtoS[c2] == c1)
            ) {
                return false;
            }
        }
        return true;
    }

    // editorial solution with transform

    private String transformString(String s) {
        Map<Character, Integer> indexMapping = new HashMap<>();
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < s.length(); ++i) {
            char c1 = s.charAt(i);

            if (!indexMapping.containsKey(c1)) {
                indexMapping.put(c1, i);
            }

            builder.append(Integer.toString(indexMapping.get(c1)));
            builder.append(" ");
        }
        return builder.toString();
    }

    public boolean isIsomorphicEdWithTransform(String s, String t) {
        return transformString(s).equals(transformString(t));
    }

    // beautiful public solution
    public boolean isIsomorphicBtfl(String s, String t) {
        int map1[]=new int[200];
        int map2[]=new int[200];

        if(s.length()!=t.length())
            return false;

        for(int i = 0; i < s.length(); i++) {
            if (map1[s.charAt(i)] != map2[t.charAt(i)]) {
                return false;
            }
            map1[s.charAt(i)] = i + 1;
            map2[t.charAt(i)] = i + 1;
        }
        return true;
    }


}
