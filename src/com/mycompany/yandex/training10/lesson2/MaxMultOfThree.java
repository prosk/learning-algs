package com.mycompany.yandex.training10.lesson2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class MaxMultOfThree {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new MaxMultOfThree().run();
    }

    private void run() {
        try {
            long timeStart = System.currentTimeMillis();
            solve();
            out.close();
            long timeEnd = System.currentTimeMillis();
            System.err.println("Time(ms) = " + (timeEnd - timeStart));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void solve() {
        List<Integer> arr = readIntList();
        int len = arr.size();

        int[] sortedThree = new int[] {arr.get(0), arr.get(1), arr.get(2)};
        Arrays.sort(sortedThree);

        int max1 = sortedThree[2];
        int max2 = sortedThree[1];
        int max3 = sortedThree[0];
        int min1 = max3, min2 = max2, min3 = max1;

        int curr;
        for(int i = 3; i < len; i++) {
            curr = arr.get(i);
            if (curr > max1) {
                max3 = max2;
                max2 = max1;
                max1 = curr;
            } else if (curr > max2) {
                max3 = max2;
                max2 = curr;
            } else if (curr > max3) {
                max3 = curr;
            }

            if (curr < min1) {
                min3 = min2;
                min2 = min1;
                min1 = curr;
            } else if (curr < min2) {
                min3 = min2;
                min2 = curr;
            } else if (curr < min3) {
                min3 = curr;
            }

        }
        /*out.printf("max1 = %d max2 = %d max3 = %d \n min1 = %d min2 = %d min3 = %d \n",
                max1, max2, max3, min1, min2, min3);*/

        /*long max123Mult = (long) max1*max2*max3;
        long min123Mult = (long) min1*min2*min3;*/

        /*if (max123Mult > min123Mult) {
            out.println(max1 + " " + max2 + " " + max3);
        } else {
            out.println(min1 + " " + min2 + " " + min3);
        }*/

        if (len >= 6) {
            // перебираем 3 сомножителей из 6 чисел (min1, min2, min3, max3, max2, max1)
            checkSubsets(new int[]{min1, min2, min3, max3, max2, max1}, 56);
        } else if (len == 5) {
            // перебираем 3 сомножителей из 5 чисел (min1, min2, min3, max2, max1)
            checkSubsets(new int[]{min1, min2, min3, max2, max1}, 28);
        } else if (len == 4) {
            // перебираем 3 сомножителей из 4 чисел (min1, min2, min3, max1)
            checkSubsets(new int[]{min1, min2, min3, max1}, 14);
        } else if (len == 3) {
            // тривиальный случай
            out.println(min1 + " " + min2 + " " + min3);
        }

    }

    private void checkSubsets(int arr[], int maxVal) {
        Set<Integer> posInd = new HashSet<>();
        long maxMult = (long) arr[0]*arr[1]*arr[2];
        Set<Integer> maxPosInd = new HashSet<>(Arrays.asList(0, 1, 2));

        for(int i = 7; i <= maxVal; i++) {
            String binStr = Integer.toBinaryString(i);
            posInd.clear();
            for(int j = 0; j < binStr.length(); j++) {
                if (binStr.charAt(j) == '1') {
                    posInd.add(binStr.length() - 1 - j);
                }
            }
            if (posInd.size() == 3) {
                // 3 единицы в двоичном представлении
                long currMult = 1L;
                for(Integer pos: posInd) {
                    currMult *= (long) arr[pos];
                }
                if (currMult > maxMult) {
                    maxMult = currMult;
                    maxPosInd = new HashSet<>(posInd);
                }
            }
        }
        String ans = "";
        int i = 0;
        for(Integer pos: maxPosInd) {
            ans = ans + arr[pos];
            i++;
            if (i < 3) ans = ans + " ";
        }
        out.println(ans);
    }

    List <Integer> readIntList() {
        List<Integer> resList = new ArrayList<>();
        String currStr = readString();
        while(currStr != null) {
            resList.add(Integer.valueOf(currStr));
            currStr = readString();
        }
        return resList;
    }

    private String readString() {
        while (!tok.hasMoreTokens()) {
            String nextLine = readLine();
            if (null == nextLine) return null;
            tok = new StringTokenizer(nextLine);
        }

        return tok.nextToken();
    }

    private String readLine() {
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
