package com.mycompany.postupashki.algs2023.binsearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class BinaryStringModification {

    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new BinaryStringModification().run();
    }

    private void run() {
        try {
            solve();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    // пусть ans = минимальное количество изменений после которого будет существовать подстрока длины
    // k состоящая из одинаковых символов
    // аналогично 1-ой задаче, можно бинарить ответ ans
    private void solve() {
        String inputStr = readString();
        int k = readInt();

        int len = inputStr.length();

        int l = 0; // если строка уже подходит, можно ничего не менять
        int r = len; // более len замен сделать точно нельзя
        int ans = 0; // начальное значение неважно, т.к. в любом случае будет переприсваивание

        while (l <= r) {
            int mid = (l + r) / 2;
            if (isOk(mid, inputStr, k)) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        out.println(ans);
    }

    private boolean isOk(int replaceCnt, String inputStr, int k) {
        int zeroCnt = 0, oneCnt = 0, len = inputStr.length();
        for(int i = 0; i < k; i++) {
            if (inputStr.charAt(i) == '0') zeroCnt++;
            if (inputStr.charAt(i) == '1') oneCnt++;
        }
        int maxCnt = Math.max(zeroCnt, oneCnt);
        int neededReplaceCnt = k - maxCnt;
        if (neededReplaceCnt <= replaceCnt) {
            return true;
        }
        for(int j = k; j < len; j++) {
            char addedSymbol = inputStr.charAt(j);
            char deletedSymbol = inputStr.charAt(j-k);
            if (addedSymbol == '0') {
                zeroCnt++;
            } else {
                oneCnt++;
            }
            if (deletedSymbol == '0') {
                zeroCnt--;
            } else {
                oneCnt--;
            }
            maxCnt = Math.max(zeroCnt, oneCnt);
            neededReplaceCnt = k - maxCnt;
            if (neededReplaceCnt <= replaceCnt) {
                return true;
            }
        }
        return false;
    }

    private int readInt() {
        return Integer.parseInt(readString());
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


