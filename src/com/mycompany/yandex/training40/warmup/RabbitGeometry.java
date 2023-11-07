package com.mycompany.yandex.training40.warmup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class RabbitGeometry {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new RabbitGeometry().run();
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

    private void optSolve() {
        // можно решить динамикой за O(N*M)
        // dp[i][j] - максимальная сторона квадрата ез единиц, у которого правый нижний угол в клетке i, j
        // в разборе 40-ая минута видео
        // if matrix[i, j] == 1 then dp[i, j] = min(трех клеток сверху и слева) + 1 else dp[i, j] = 0
        // так как мы можем сделать вкадрат со стороной (a+1) только есть во ВСЕХ этих трех клетках находится a
        // нужно нарисовать чтобы понять
    }

    private void solve() {
        // Ввод данных
        int rowsCnt = readInt();
        int colsCnt = readInt();

        int[][] matrix = new int[rowsCnt+1][colsCnt+1];

        boolean isOnesExist = false;
        for(int i = 1; i <= rowsCnt; i++) {
            int currRowSum = 0;
            for(int j = 1; j <= colsCnt; j++) {
                int elem = readInt();
                isOnesExist = isOnesExist || elem == 1;
                currRowSum += elem;
                matrix[i][j] = currRowSum + matrix[i-1][j];
            }
        }

        if (!isOnesExist) {
            out.println(0);
            return;
        }

        int l = 2, r = Math.min(rowsCnt, colsCnt);
        int ans = 1;
        while (l <= r) {
            int mid = (l+r)/2;
            if (isSquareExists(matrix, rowsCnt, colsCnt, mid)) {
                ans = mid;
                l = mid+1;
            } else {
                r = mid - 1;
            }
        }
        out.println(ans);
    }

    boolean isSquareExists(int[][] matrix, int rowsCnt, int colsCnt, int a) {
        for(int x1 = 1; x1 <= rowsCnt-a+1; x1++) {
            for(int y1 = 1; y1 <= colsCnt-a+1; y1++) {
                int sum = getSubMatrixSum(matrix, x1, y1, x1+a-1, y1+a-1);
                if (sum == a*a) return true;
            }
        }
        return false;
    }

    private int getSubMatrixSum(int[][] matrix, int x1, int y1, int x2, int y2) {
        int s1 = matrix[x2][y2] - matrix[x1-1][y2];
        int s2 = matrix[x2][y1-1] - matrix[x1-1][y1-1];
        return s1 - s2;
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
