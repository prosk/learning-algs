package com.mycompany.yandex.training.dp.twoparam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class MaxCostRoute {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new MaxCostRoute().run();
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
        int rowsCnt = readInt();
        int colsCnt = readInt();

        int[][] matrix = new int[rowsCnt+1][colsCnt+1];
        for(int i = 1; i <= rowsCnt; i++) {
            for(int j = 1; j <= colsCnt; j++) {
                matrix[i][j] = readInt();
            }
        }

        // dp[i][j] - максимальная сумма, которую можно набрать, прийдя в клетку [i][j].
        int[][] dp = new int[rowsCnt+1][colsCnt+1];
        // Заполнение нулевой строки и нулевого столбца
        for(int i = 2; i <= rowsCnt; i++) {
            dp[i][0] = -1;
        }
        for(int j = 2; j <= colsCnt; j++) {
            dp[0][j] = -1;
        }

        for(int i = 1; i <= rowsCnt; i++) {
            for(int j = 1; j <= colsCnt; j++) {
                // в текущую клетку можно попасть только сверху либо слева
                dp[i][j] = matrix[i][j] + Math.max(dp[i][j-1], dp[i-1][j]);
            }
        }
        int res = dp[rowsCnt][colsCnt];

        // собираем маршрут
        char[] route = new char[rowsCnt-1 + colsCnt-1];
        int currRow = rowsCnt, currCol = colsCnt;
        int i = 0;
        while(currRow > 1 || currCol > 1) {
            int left = dp[currRow][currCol-1];
            int up = dp[currRow-1][currCol];
            int max = Math.max(left, up);
            if (left == max) {
                route[i++] = 'R';
                currCol--;
            } else {
                route[i++] = 'D';
                currRow--;
            }
        }

        StringBuilder sb = new StringBuilder("");
        for(int j = i-1; j >= 0; j--) {
            if (j == i-1) {
                sb.append(route[j]);
            } else {
                sb.append(' ');
                sb.append(route[j]);
            }
        }
        String routeStr = sb.toString();

        // Вывод результатов
        out.println(res);
        if (routeStr.length() > 0) {
            out.println(routeStr);
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

