package com.mycompany.mytests.numbers;

import java.util.ArrayList;
import java.util.List;

public class BinomCoeffAlg {

    public static void main(String[] args) {
        int res = binomCoeffModulo(1_000_000, 545_877, 1_000_007);
        System.out.println(res);
    }

    public static int binomCoeffModulo(int n, int k, int mod) {
        List<Integer> primes = findPrimeFactors(mod);
        List<Integer> rem = new ArrayList<>();

        for (int m : primes)
            rem.add(Lucas(n, k, m));

        int min_x = 0;
        while (true) {
            boolean found = true;
            for (int i = 0; i < primes.size(); i++) {
                if (min_x % primes.get(i) != rem.get(i)) {
                    found = false;
                    break;
                }
            }
            if (found) {
                return min_x;
            }
            min_x++;
        }
    }

    // Function to utilize the Lucas theorem
    private static int Lucas(int n, int r, int m) {
        // If (r > n) return 0;
        if (r == 0)
            return 1;

        int ni = n % m;
        int ri = r % m;
        return (pascal(ni, ri, m)
                * Lucas(n / m, r / m, m))
                % m;
    }

    // Pascal triangle method to find nCr
    private static int pascal(int n, int r, int m) {
        if (r == 0 || r == n)
            return 1;

        // r = Math.min(r, n - r);
        int[] nCr = new int[r + 1];
        nCr[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = Math.min(r, i); j > 0; j--)
                nCr[j] = (nCr[j] + nCr[j - 1]) % m;
        }
        return nCr[r];
    }

    private static List<Integer> findPrimeFactors(int n) {
        List<Integer> primes = new ArrayList<>();
        if (n % 2 == 0) {
            primes.add(2);
            while (n % 2 == 0)
                n >>= 1;
        }
        for (int i = 3; n > 1; i += 2) {
            if (n % i == 0) {
                primes.add(i);
                while (n % i == 0)
                    n /= i;
            }
        }
        return primes;
    }
}
