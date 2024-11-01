//package com.mycompany.yandex.training60.prefpointers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class MedianDeletionOpt {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new MedianDeletionOpt().run();
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
            ans.add(arr[mid]); len--;
            i = mid-1; j = mid+1;
        } else {
            i = mid-1; j = mid;
        }
        while(len > 0) {
            ans.add(arr[i]);
            ans.add(arr[j]);
            i--; j++;
            len -= 2;
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