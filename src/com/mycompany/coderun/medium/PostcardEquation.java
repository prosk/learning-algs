package com.mycompany.coderun.medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class PostcardEquation {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new PostcardEquation().run();
        out.close();
    }

    void run() {
        long n = readLong();
        int ans = 0;
        for(int k = 1; k <= 11 && (n-k) > 0; k++) {
            long curr = n - k;
            List<Long> factors = findPrimeFactors(curr);
            if (factors.size() == k) ans++;
        }
        out.println(ans);
    }

    void explore() {
        int n = 1000;
        int[] primes = getPrimes(n);
        System.out.println(primes.length);

        long nMax = 1_000_000_000_000L;

        long mult = 1, maxInd = 0;
        for(int i = 0; i < primes.length; i++) {
            if (mult*primes[i] < nMax) {
                mult = mult*primes[i];
                maxInd = i;
                System.out.println("curr mult = " + mult);
            } else {
                System.out.println("maxInd = " + maxInd + " mult*primes[i] = " + mult*primes[i]);
                break;
            }
        }
        System.out.println("mult = " + mult);
    }

    int readInt() {
        return Integer.parseInt(readString());
    }

    private long readLong() {
        return Long.parseLong(readString());
    }

    String readString() {
        while (!tok.hasMoreTokens()) {
            String nextLine = readLine();
            if (null == nextLine) return null;
            tok = new StringTokenizer(nextLine);
        }
        return tok.nextToken();
    }

    String readLine() {
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int[] getPrimes(int n) {
        boolean[] used = new boolean[n];
        used[0] = used[1] = true;

        int size = 0;
        for (int i = 2; i < n; ++i) {
            if (!used[i]) {
                ++size;
                for (int j = 2 * i; j < n; j += i) {
                    used[j] = true;
                }
            }
        }

        int[] primes = new int[size];
        for (int i = 0, cur = 0; i < n; ++i) {
            if (!used[i]) {
                primes[cur++] = i;
            }
        }

        return primes;
    }

    private static List<Long> findPrimeFactors(long n) {
        List<Long> primes = new ArrayList<>();
        if (n % 2 == 0) {
            primes.add(2L);
            while (n % 2 == 0)
                n >>= 1;
        }
        for (long i = 3; n > 1; i += 2) {
            if (n % i == 0) {
                primes.add(i);
                while (n % i == 0)
                    n /= i;
            }
        }
        return primes;
    }
}