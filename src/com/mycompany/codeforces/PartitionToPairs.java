package com.mycompany.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

// https://codeforces.com/contest/1788/problem/C
public class PartitionToPairs {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new PartitionToPairs().run();
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
        int inpSetCnt = readInt();
        for (int i = 0; i < inpSetCnt; i++) {
            int n = readInt();
            //printResult(n);
            printFinalResults(n);
        }
    }

    private void printFinalResults(int n) {
        if (n == 1) {
            out.printf("%s\n%s\n", "Yes", "1 2");
            return;
        }
        if ((n % 2) == 0) {
            out.println("No");
            return;
        }

        // n >= 2, 2n >= 4, n нечетное

        // matrix output
        /*for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                out.print(getMatrixElem(n, i, j) + "   ");
            }
            out.println();
        }*/

        out.println("Yes");
        // 1-ая диагональ
        int rowInd = n/2 + 1;
        int colInd = 1;
        while (colInd <= n) {
            out.printf("%d %d\n", rowInd, colInd+n);
            colInd += 2;
            rowInd--;
        }

        // 2-ая диагональ
        rowInd = n;
        colInd = 2;
        while (colInd < n) {
            out.printf("%d %d\n", rowInd, colInd+n);
            colInd += 2;
            rowInd--;
        }

    }

    private void printResult(int n) {
        if (n == 1) {
            out.printf("%s\n%s\n", "Yes", "1 2");
            return;
        }
        if ((n % 2) == 0) {
            out.println("No");
            return;
        }

        // n >= 2, 2n >= 4, n нечетное

        // matrix output
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                out.print(getMatrixElem(n, i, j) + "   ");
            }
            out.println();
        }

        int minSum = 2 * n + 1 - n / 2;
        int maxSum = 2 * n + 1 + n / 2;
        int midSum = 2 * n + 1;

        SumPosition[] sumPositions = new SumPosition[n];
        int rowInd, colInd;
        // 1-м элементом при поиске делаем верхний правый угол матрицы
        sumPositions[0] = new SumPosition();
        sumPositions[0].sum = midSum;
        int diagInd = midSum - (n + 2) + 1;
        rowInd = 1;
        colInd = diagInd;
        while (colInd > 0) {
            int[] elem = new int[2];
            elem[0] = rowInd;
            elem[1] = colInd;
            sumPositions[0].coords.add(elem);
            colInd--;
            rowInd++;
        }

        int j = 0;
        for (int i = 0; i < n; i++) {
            int currSum = minSum + i;
            if (currSum < midSum) {
                j++;
                sumPositions[j] = new SumPosition();
                sumPositions[j].sum = currSum;
                diagInd = currSum - (n + 2) + 1;
                rowInd = 1;
                colInd = diagInd;
                while (colInd > 0) {
                    int[] elem = new int[2];
                    elem[0] = rowInd;
                    elem[1] = colInd;
                    sumPositions[j].coords.add(elem);
                    colInd--;
                    rowInd++;
                }
            } else if (currSum > midSum) {
                j++;
                sumPositions[j] = new SumPosition();
                sumPositions[j].sum = currSum;
                diagInd = currSum - midSum + 1;
                rowInd = diagInd;
                colInd = n;
                while (rowInd <= n) {
                    int[] elem = new int[2];
                    elem[0] = rowInd;
                    elem[1] = colInd;
                    sumPositions[j].coords.add(elem);
                    colInd--;
                    rowInd++;
                }
            }
        }

        int[][] foundedPairs = new int[n][2];
        Set<Integer> usedRows = new HashSet<>();
        Set<Integer> usedCols = new HashSet<>();

        /*boolean founded = findElemsSet(n, minSum, sumPositions, 0, usedRows, usedCols, foundedPairs);

        if (founded) {
            out.println("Yes");
            for(int k = 0; k < n; k++) {
                out.printf("%d %d\n", foundedPairs[k][0], foundedPairs[k][1]+n);
            }
        } else {
            out.println("No");
        }*/

    }

    private boolean findElemsSet(int n, int minSum, SumPosition[] positions, int currInd,
                              Set<Integer> usedRows, Set<Integer> usedCols, int[][] foundedPairs) {
        if (currInd == n) {
            return true;
        }

        List<int[]> currPositions = positions[currInd].coords;
        int currSum = positions[currInd].sum;
        if (currInd == 0) {
            for(int[] pos: currPositions) {
                int addedRow = pos[0];
                int addedCol = pos[1];
                usedRows.add(addedRow);
                usedCols.add(addedCol);
                foundedPairs[currInd][0] = addedRow;
                foundedPairs[currInd][1] = addedCol;
                boolean founded = findElemsSet(n, minSum, positions, currInd+1, usedRows, usedCols, foundedPairs);
                if (founded) return true;
                // отменям поставленную точку для currInd
                usedRows.remove(addedRow);
                usedCols.remove(addedCol);
                foundedPairs[currInd][0] = 0;
                foundedPairs[currInd][1] = 0;
            }
            return false;
        } else {
            for(int[] pos: currPositions) {
                int addedRow = pos[0];
                int addedCol = pos[1];
                if (usedRows.contains(addedRow) || usedCols.contains(addedCol)) {
                    continue;
                } else {
                    usedRows.add(addedRow);
                    usedCols.add(addedCol);
                    foundedPairs[currInd][0] = addedRow;
                    foundedPairs[currInd][1] = addedCol;
                    boolean founded = findElemsSet(n, minSum, positions, currInd + 1, usedRows, usedCols, foundedPairs);
                    if (founded) return true;
                    // отменям поставленную точку для currInd
                    usedRows.remove(addedRow);
                    usedCols.remove(addedCol);
                    foundedPairs[currInd][0] = 0;
                    foundedPairs[currInd][1] = 0;
                }
            }
            // не смогли поставить currInd сумму вообще, возвращаемся назад, надо отменить последнюю
            return false;
        }
    }


    private static class SumPosition {
        int sum;
        List<int[]> coords = new ArrayList<>();
    }


    // i, j - меняются от 1 до n
    int getMatrixElem(int n, int i, int j) {
        int firstRowBegin = n + 2;
        return firstRowBegin + (i - 1) + (j - 1);
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