//package com.mycompany.codeforces.itmo.academy.twopointers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class NumberOfEqual {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new NumberOfEqual().run();
        out.close();
    }

    /*

      Альтернативные решения от v0s7er

      Очевидное решение через мапы и подсчет количеств

      private void solve() {
        int n = ri(), m = ri();
        int[] a = ria(n);
        int[] b = ria(m);
        HashMap<Integer, Integer> num2count = new HashMap<>();
        for(int i = 0; i < n; i++) {
            num2count.put(a[i], num2count.getOrDefault(a[i], 0) + 1);
        }

        long ans = 0;
        for(int i = 0; i < m; i++) {
            ans+=num2count.getOrDefault(b[i], 0);
        }

        out.println(ans);
    }

      Более хитрое решение на С++ тоже через 2 указателя

      Пока двигаем j вперед на равных элеметах в b,
      каждый раз к ответу прибавляем (r - l) -
      где [l, r) границы такого же равного экемента в a

      void solve() {
            int n, m;
            cin >> n >> m;

            vector<int> a(n), b(m);
            for(auto &el : a) cin >> el;
            for(auto &el : b) cin >> el;

            ll ans = 0L;

            int l = 0, r = 0;
            for(int j = 0; j < m; j++) {
                while(l < n && a[l] < b[j]) l++;
                if(l == n) break;
                if(a[l] != b[j]) continue;
                r = max(r, l);
                while(r < n && a[l] == a[r]) r++;
                ans += r - l;
            }

            cout << ans;
        }

     */

    void run() {
        int n = readInt();
        int m = readInt();

        int[] a = new int[n];
        for(int i = 0; i < n; i++) a[i] = readInt();

        int[] b = new int[m];
        for(int j = 0; j < m; j++) b[j] = readInt();

        long ans = 0;

        int i = 0, j = 0;
        while(j < m) {
            int bCnt = 1;
            while (j+1 < m && b[j+1] == b[j]) {
                bCnt++;
                j++;
            }
            while (i < n && a[i] < b[j]) i++;
            int equalsCnt = 0;
            while (i < n && a[i] == b[j]) {
                equalsCnt++;
                i++;
            }
            ans += (long)bCnt * equalsCnt;
            j++;
        }

        out.println(ans);
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