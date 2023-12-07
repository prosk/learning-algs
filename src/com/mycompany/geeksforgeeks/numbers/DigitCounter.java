package com.mycompany.geeksforgeeks.numbers;

// https://www.geeksforgeeks.org/problems/how-many-xs4514/1
// Given two integers L, R, and digit X. Find the number of occurrences of X in all the numbers
// in the range (L, R) excluding L and R.
public class DigitCounter {
    public int simpleCountX(int L, int R, int X) {
        // R <= 100000
        int low = L+1, right = R-1;
        int totalCnt = right - low + 1;

        if (totalCnt <= 0)
            return 0;

        int[] curr = new int[6];
        int startNum = low;
        for(int i = 0; i <= 5; i++) {
            curr[i] = startNum % 10;
            startNum = startNum / 10;
        }

        int ans = 0;
        for(int j = 0; j < totalCnt; j++) {
            // calculation of count of X in curr
            int upper = 5;
            while(curr[upper] == 0) upper--;
            for(int digit = upper; digit >= 0; digit--) {
                if (curr[digit] == X) ans++;
            }
            // curr = curr + 1
            int indToPlus = 0;
            while (curr[indToPlus] == 9) indToPlus++;
            curr[indToPlus]++;
            for(int i = indToPlus-1; i >= 0; i--)
                curr[i] = 0;
        }
        return ans;
    }

    int countXHelper(int N, int X)
    {

        int cnt = 0;

        for (int i = 1; i <= N; i *= 10) {
            int divi = i * 10;
            int quot = N / divi;
            int rem = N % divi;

            if (quot > 0) {
                cnt = cnt + (quot * i);
            }

            if (X == 0) {
                cnt = cnt - i;
            }

            if (rem >= X * i) {
                cnt = cnt + (Math.min(rem - X * i + 1, i));
            }
        }

        return cnt;
    }

    int countX(int L, int R, int X) {
        return countXHelper(R - 1, X) - countXHelper(L, X);
    }

    public static void main(String[] args) {
        DigitCounter digitCounter = new DigitCounter();
        /*System.out.println("Simple solution");
        System.out.println("L=10, R=19, X=1: ans = " + digitCounter.simpleCountX(10, 19, 1));
        System.out.println("L=18, R=81, X=9: ans = " + digitCounter.simpleCountX(18, 81, 9));
        System.out.println("L=504, R=7382, X=0: ans = " + digitCounter.simpleCountX(504, 7382, 0));
        System.out.println("L=73, R=1028, X=5: ans = " + digitCounter.simpleCountX(73, 1028, 5));
        System.out.println("L=70, R=102, X=0: ans = " + digitCounter.simpleCountX(70, 102, 0));

        System.out.println("Optimal solution");
        System.out.println("L=10, R=19, X=1: ans = " + digitCounter.countX(10, 19, 1));
        System.out.println("L=18, R=81, X=9: ans = " + digitCounter.countX(18, 81, 9));
        System.out.println("L=504, R=7382, X=0: ans = " + digitCounter.countX(504, 7382, 0));
        System.out.println("L=73, R=1028, X=5: ans = " + digitCounter.countX(73, 1028, 5));*/
        System.out.println("L=70, R=102, X=0: ans = " + digitCounter.countX(70, 102, 0));
    }

}
