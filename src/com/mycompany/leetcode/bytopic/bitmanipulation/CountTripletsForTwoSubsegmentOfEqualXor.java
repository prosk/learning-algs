package com.mycompany.leetcode.bytopic.bitmanipulation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 1442. Count Triplets That Can Form Two Arrays of Equal XOR
// https://leetcode.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/description/
public class CountTripletsForTwoSubsegmentOfEqualXor {

    /*
                       xor prefix
   000101   5      000101             5
   000111   7      000010 b2          2
   111000   56     111010 begin       58
   111101   61     000111             7
   111001   57     111110             62
   000100   4      111010 end         58
   101000   40     010010             18
   010000   16     000010 e2          2
     */
    public static void main(String[] args) {
        CountTripletsForTwoSubsegmentOfEqualXor runner = new CountTripletsForTwoSubsegmentOfEqualXor();
        int[] testArr = {5, 7, 56, 61, 57, 4, 40, 16};
        int res = runner.countTriplets(testArr);
        System.out.println("res1 = " + res);

        int res1 = runner.countTripletsEd(testArr);
        System.out.println("res1 = " + res1);

        int res2 = runner.countTripletsOpt(testArr);
        System.out.println("res1 = " + res2);
    }

    // simple O(N*N) solution
    public int countTriplets(int[] arr) {
        int n = arr.length + 1, res = 0, prefix[] = new int[n];
        for (int i = 1; i < n; ++i)
            prefix[i] = arr[i - 1] ^ prefix[i - 1];
        System.out.println("Prefix array is " + Arrays.toString(prefix));
        for (int i = 0; i < n-2; ++i)
            for (int j = i + 2; j < n; ++j)
                if (prefix[i] == prefix[j])
                    res += j - i - 1;
        return res;
    }

    // opt editorial solution when pref[i] = XOR(arr[0]...arr[i-1])
    /*
      вывод формулы

      pref=  X        X          X        X
      [][][][*][][][][*][][][][][*][][][][*][][][][][][][][]
      ind=   j1       j2  ....   jn       i

      pref[j1] = pref[j2] = ... = pref[jn] = pref[i] = X
      Triplet sum for segment with end ind = (i-1) and start ind = ji
      Sum = (i -1 - j1) + (i -1 - j2) + ... + (i -1 - jn) =
          = n * (i - 1) - Sum(j1..jn)

      countMap = n
      totalMap = Sum(j1..jn)

      => the formula is
      count +=
                countMap.getOrDefault(prefixXOR[i], 0) * (i - 1) -
                    totalMap.getOrDefault(prefixXOR[i], 0);

     */
    public int countTripletsEd(int[] arr) {
        int[] prefixXOR = new int[arr.length + 1];
        prefixXOR[0] = 0;
        System.arraycopy(arr, 0, prefixXOR, 1, arr.length);
        int size = prefixXOR.length;
        int count = 0;

        // Performing XOR operation on the array elements
        for (int i = 1; i < size; ++i) prefixXOR[i] ^= prefixXOR[i - 1];

        // Maps to store counts and totals of XOR values encountered
        HashMap<Integer, Integer> countMap = new HashMap<>();
        HashMap<Integer, Integer> totalMap = new HashMap<>();

        // Iterating through the array
        for (int i = 0; i < size; ++i) {
            // Calculating contribution of current element to the result
            count +=
                countMap.getOrDefault(prefixXOR[i], 0) * (i - 1) -
                    totalMap.getOrDefault(prefixXOR[i], 0);

            // Updating total count of current XOR value
            totalMap.put(
                prefixXOR[i],
                totalMap.getOrDefault(prefixXOR[i], 0) + i
            );
            countMap.put(
                prefixXOR[i],
                countMap.getOrDefault(prefixXOR[i], 0) + 1
            );
        }

        return count;
    }


    // opt solution when pref[i] = XOR(arr[0]...arr[i])
    /*
      вывод формулы

      pref=  X        X          X        X
      [][][][*][][][][*][][][][][*][][][][*][][][][][][][][]
      ind=   j1       j2  ....   jn       i

      pref[j1] = pref[j2] = ... = pref[jn] = pref[i] = X
      Triplet sum for segment with end ind = i and start ind = ji + 1
      Sum = (i - (j1 + 1)) + (i - (j2 + 1)) + ... + (i - (jn + 1)) =
          = n * i  - Sum(j1+1..jn+1)

      countMap = n
      totalMap = Sum(j1+1..jn+1)

      => the formula is
      c = count.getOrDefault(prefix, 0);
      t = total.getOrDefault(prefix, 0);
      res += c * i - t;
      count.put(prefix, c + 1);
      total.put(prefix, t + i + 1);

     */
    public int countTripletsOpt(int[] arr) {
        int n = arr.length, res = 0, prefix = 0, c, t;
        Map<Integer, Integer> count = new HashMap<>(), total = new HashMap<>();
        count.put(0, 1);
        for (int i = 0; i < n; i++) {
            prefix ^= arr[i];
            c = count.getOrDefault(prefix, 0);
            t = total.getOrDefault(prefix, 0);
            res += c * i - t;
            count.put(prefix, c + 1);
            total.put(prefix, t + i + 1);
        }
        return res;
    }

}
