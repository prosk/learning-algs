package com.mycompany.postupashki.algs2023.binsearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

public class OnlyEqualsArray {

    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new OnlyEqualsArray().run();
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

    // пусть ans = минимальное количесто чисел в массиве a, которые следует удалить, таким образом, чтобы в массиве a
    // оставщиеся числа были равны
    // тогда удалив ans+1, ans+2, ans+3 чисел, мы тоже точно сможем получить массив с равнымии числами
    // но, удалив ans-1, ans-2 и т.д. чисел, наоборот, уже нельзя получить массив с равными числами,
    // иначе ans не было бы минимальным кол-вом
    // поэтому можно бинарить ответ ans
    private void solve() {
        int n = readInt();
        int[] a = readIntArray(n);

        int l = 0; // если все числа уже равны в массиве, можно ничего не удалять
        int r = n - 1; // удалить все элементы бессмысленно, поэтому максимально можно удалить n-1
        int ans = 0; // начальное значение неважно, т.к. в любом случае будет переприсваивание, если n > 0

        while (l <= r) {
            int mid = (l + r) / 2;
            if (isOk(mid, a)) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        out.println(ans);
    }

    // функция должна проверить, можно ли получить все одинаковые числа в массиве a,
    // удалив из него deletedCnt чисел
    // Это можно сделать, если массив a содержтит элемент, который повторяется (n - deletedCnt) раз
    private boolean isOk(int deletedCnt, int[] a) {
        // мапа для просмотренного элемента a[i] хранит его текущее кол-во повторений в массиве
        Map<Integer, Integer> elemCnts = new HashMap<>();
        int repeatedCnt = a.length - deletedCnt;
        for(int i = 0; i < a.length; i++) {
            // если ключа, равного a[i], нет в мапе, добавляем его туда  со значением 1,
            // а если такой ключ есть, прибавляем к значению 1
            int currElemCnt = elemCnts.merge(a[i], 1, Integer::sum);
            if (currElemCnt == repeatedCnt) {
                // если у элемента набралось repeatedCnt повторений, нашли то, что нужно
                return true;
            }
        }
        return false;
    }

    private int[] readIntArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; ++i) a[i] = readInt();
        return a;
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
