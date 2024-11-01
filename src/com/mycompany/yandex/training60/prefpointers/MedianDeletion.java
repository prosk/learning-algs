//package com.mycompany.yandex.training60.prefpointers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class MedianDeletion {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new MedianDeletion().run();
        out.close();
    }

    void run() {
        int n = readInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = readInt();
        }
        Arrays.sort(arr);
        List<Integer> ans = new ArrayList<>();
        int i, j, mid = n/2, len = n;
        if (n % 2 == 1) {
            i = mid; j = mid;
        } else {
            i = mid-1; j = mid;
        }
        // инвариант i <= j , индексы i и j "в середине" списка
        // так как массив упорядоченный то всегда при i != j будет arr[i] <= arr[j]
        int leftUndeleted = i-1;
        while(len-- > 0) {
            // добавление и сдвиг
            if (i == j) {
                ans.add(arr[i]);
                i = leftUndeleted;
                j++;
            } else {
                ans.add(arr[i]);
                leftUndeleted = i-1;
                i = j;
            }
        }
        StringBuilder sb = new StringBuilder("");
        for(int k = 0; k < n; k++) {
            sb.append(ans.get(k));
            if (k < n-1) sb.append(' ');
        }
        out.println(sb);
    }

    int readInt() {
        return Integer.parseInt(readString());
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
}