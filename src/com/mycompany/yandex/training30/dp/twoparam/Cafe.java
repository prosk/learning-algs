package com.mycompany.yandex.training30.dp.twoparam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class Cafe {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new Cafe().run();
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
        // Ввод данных
        int daysCnt = readInt();

        int[] prices = new int[daysCnt+1];
        for(int i = 1; i <= daysCnt; i++) {
            prices[i] = readInt();
        }

        // dp[i][j] - минимальная заплаченная за обеды сумма, если мы если i дней подряд, и имеем после этого j купонов
        int[][] dp = new int[daysCnt+1][daysCnt+3];
        // нулевой день - мы не если ничего, ничего не потратили, нет купонов
        // индексы по купонам: 0 - соответствует -1, 1 - это 0 купонов, 2 - это 1 купон и т.д.
        // daysCnt+2 это как будто у нас есть daysCnt+1 купон, что невозможно, и мы тратим этот купон, чтобы
        // осталось daysCnt купонов
        // Заполнение бесконечностями, т.к. далее мы будем брать минимум
        for(int i = 0; i <= daysCnt; i++) {
            for(int j = 0; j <= (daysCnt+2); j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[0][1] = 0; // в нулевой день для 0 купонов мы потратили 0 рублей

        // собственно динамика
        for(int i = 1; i <= daysCnt; i++) {
            int currDinnerPrice = prices[i]; // цена обеда в текущем дне
            for(int j = 1; j <= (daysCnt+1); j++) { // индекс 1 это 0 купонов
                // int prevIfUseCoupon = dp[i-1][j+1];
                int prevIfUseCoupon = (currDinnerPrice > 0) ? dp[i-1][j+1] : Integer.MAX_VALUE;
                int prevIfSpendMoney;
                if (currDinnerPrice > 100) {
                    prevIfSpendMoney = (dp[i-1][j-1] == Integer.MAX_VALUE) ? Integer.MAX_VALUE
                            : currDinnerPrice + dp[i-1][j-1];
                } else {
                    prevIfSpendMoney = (dp[i-1][j] == Integer.MAX_VALUE) ? Integer.MAX_VALUE
                            : currDinnerPrice + dp[i-1][j];
                }
                dp[i][j] = Math.min(prevIfUseCoupon, prevIfSpendMoney);
            }
        }

        // ищем для последнего дня, где цена минимальна, если таких несколько - с максимальным индексом
        // т.е. с наибольшим кол-вом купонов
        int minPrice = Integer.MAX_VALUE, couponsCnt = 0;
        for(int j = 1; j <= (daysCnt+1); j++) {
            if (dp[daysCnt][j] <= minPrice) {
                minPrice = dp[daysCnt][j];
                couponsCnt = j - 1;
            }
        }

        // восстанавливаем "путь" в обратном порядке
        // нас интересуют только номера дней, когда Пете следует воспользоваться купонами
        int currDay = daysCnt, currJ = couponsCnt + 1; // ячейка, которую нашли
        int usedCoupons = 0;
        int[] usedCouponDays = new int[daysCnt];
        while (currDay > 1) {
            int prevUseCoupon = dp[currDay-1][currJ+1];
            int prevSpendMoneyWithoutCoupon = dp[currDay-1][currJ];
            int prevSpendMoneyWithCoupon = dp[currDay-1][currJ-1];
            if (dp[currDay][currJ] == prevUseCoupon) {
                usedCouponDays[usedCoupons] = currDay;
                usedCoupons++;
                currJ++;
            } else if (dp[currDay][currJ] == (prices[currDay] + prevSpendMoneyWithCoupon)) {
                currJ--;
            }
            currDay--;
        }

        out.println(minPrice);
        out.printf("%d %d\n", couponsCnt, usedCoupons);
        for(int i = usedCoupons-1; i >= 0; i--) {
            out.println(usedCouponDays[i]);
        }
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

