// package com.mycompany.yandex.training60.testing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class LettersOnTheScreen {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new LettersOnTheScreen().run();
        out.close();
    }

    int onSum = 0;

    void run() {
        int n = readInt();
        int[][] screen = new int[n+2][n+2];

        for(int i = 1; i <= n; i++) {
            String s = readString();
            for(int j = 0; j < n; j++) {
                if (s.charAt(j) == '#') {
                    screen[i][j+1] = 1;
                    onSum++;
                }
            }
        }

        boolean isI = isLetterI(screen, n);
        boolean isO = isLetterO(screen, n);
        boolean isC = isLetterC(screen, n);
        boolean isL = isLetterL(screen, n);
        boolean isH = isLetterH(screen, n);
        boolean isP = isLetterP(screen, n);
        if (isI) {
            out.println("I");
        } else if (isO) {
            out.println("O");
        } else if (isC) {
            out.println("C");
        } else if(isL) {
            out.println("L");
        } else if(isH) {
            out.println("H");
        } else if (isP) {
            out.println("P");
        } else {
            out.println("X");
        }
    }

    boolean isLetterP(int[][] screen, int n) {
        for(int fW = 1; fW <= n; fW++) {
            for(int fH = 1; fH <= n; fH++) {
                for(int fStartRow = 1; fStartRow <= n - fH + 1; fStartRow++) {
                    for(int fStartCol = 1; fStartCol <= n - fW + 1; fStartCol++) {
                        boolean res = isFilledByVal(screen, n, fStartRow, fStartCol, fW, fH, 0);
                        if (res && screen[fStartRow][fStartCol-1] == 1 &&
                            screen[fStartRow][fStartCol+fW] == 1 && screen[fStartRow+fH][fStartCol] == 1) {
                            // first is found, trying to find second
                            int sStartCol = fStartCol;
                            for(int sW = fW+1; sW <= n; sW++) {
                                for (int sH = 1; sH <= n; sH++) {
                                    for (int sStartRow = fStartRow + fH + 1; sStartRow <= n - sH + 1; sStartRow++) {
                                        res = isFilledByVal(screen, n, sStartRow, sStartCol, sW, sH, 0);
                                        if (res && screen[sStartRow][sStartCol - 1] == 1) {
                                            int copyOfScreen[][] = getScreenCopy(screen, n);
                                            fillByVal(copyOfScreen, n, fStartRow, fStartCol, fW, fH, 1);
                                            fillByVal(copyOfScreen, n, sStartRow, sStartCol, sW, sH, 1);
                                            boolean pIsFound = isFilledAndCoverLikeP(copyOfScreen, n,
                                                fStartRow, fStartCol, fW, fH,
                                                sStartRow, sStartCol, sW, sH);
                                            if (pIsFound) return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    boolean isFilledAndCoverLikeP(int[][] screen, int n,
                                  int inFirstStartRow, int inFirstStartCol, int inFirstW, int inFirstH,
                                  int inSecondStartRow, int inSecondStartCol, int inSecondW, int inSecondH) {
        int sum = getSumOfScreen(screen, n);
        for(int w = 1; w <= n; w++) {
            for(int h = 1; h <= n; h++) {
                for(int startRow = 1; startRow <= n - h + 1; startRow++) {
                    for(int startCol = 1; startCol <= n - w + 1; startCol++) {
                        boolean res = isFilledByValAndSum(screen, n, startRow, startCol, w, h, 1, sum);
                        if (res) {
                            int endRow = startRow + h - 1;
                            int endCol = startCol + w - 1;
                            int inEndRow = inSecondStartRow + inSecondH - 1;
                            int inEndCol = inSecondStartCol + inSecondW - 1;
                            return (startRow < inFirstStartRow && startCol < inFirstStartCol
                                && endRow == inEndRow && endCol == inEndCol);
                        }
                    }
                }
            }
        }
        return false;
    }

    boolean isLetterH(int[][] screen, int n) {
        for(int fW = 1; fW <= n; fW++) {
            for(int fH = 1; fH <= n; fH++) {
                for(int fStartRow = 1; fStartRow <= n - fH + 1; fStartRow++) {
                    for(int fStartCol = 1; fStartCol <= n - fW + 1; fStartCol++) {
                        boolean res = isFilledByVal(screen, n, fStartRow, fStartCol, fW, fH, 0);
                        if (res && screen[fStartRow][fStartCol-1] == 1 &&
                            screen[fStartRow][fStartCol+fW] == 1 && screen[fStartRow+fH][fStartCol] == 1) {
                            // first is found, trying to find second
                            int sW = fW, sStartCol = fStartCol;
                            for(int sH = 1; sH <= n; sH++) {
                                for (int sStartRow = fStartRow + fH + 1; sStartRow <= n - sH + 1; sStartRow++) {
                                    res = isFilledByVal(screen, n, sStartRow, sStartCol, sW, sH, 0);
                                    if (res && screen[sStartRow][sStartCol-1] == 1) {
                                        int copyOfScreen[][] = getScreenCopy(screen, n);
                                        fillByVal(copyOfScreen, n, fStartRow, fStartCol, fW, fH, 1);
                                        fillByVal(copyOfScreen, n, sStartRow, sStartCol, sW, sH, 1);
                                        boolean hIsFound = isFilledAndCoverLikeH(copyOfScreen, n,
                                            fStartRow, fStartCol, fW, fH,
                                            sStartRow, sStartCol, sW, sH);
                                        if (hIsFound) return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    boolean isFilledAndCoverLikeH(int[][] screen, int n,
                                  int inFirstStartRow, int inFirstStartCol, int inFirstW, int inFirstH,
                                  int inSecondStartRow, int inSecondStartCol, int inSecondW, int inSecondH) {
        int sum = getSumOfScreen(screen, n);
        for(int w = 1; w <= n; w++) {
            for(int h = 1; h <= n; h++) {
                for(int startRow = 1; startRow <= n - h + 1; startRow++) {
                    for(int startCol = 1; startCol <= n - w + 1; startCol++) {
                        boolean res = isFilledByValAndSum(screen, n, startRow, startCol, w, h, 1, sum);
                        if (res) {
                            int endRow = startRow + h - 1;
                            int endCol = startCol + w - 1;
                            int inEndRow = inSecondStartRow + inSecondH - 1;
                            int inEndCol = inFirstStartCol + inFirstW - 1;
                            return (startRow == inFirstStartRow && startCol < inFirstStartCol
                                && endRow == inEndRow && endCol > inEndCol);
                        }
                    }
                }
            }
        }
        return false;
    }

    boolean isLetterL(int[][] screen, int n) {
        for(int w = 1; w <= n; w++) {
            for(int h = 1; h <= n; h++) {
                for(int startRow = 1; startRow <= n - h + 1; startRow++) {
                    for(int startCol = 1; startCol <= n - w + 1; startCol++) {
                        boolean res = isFilledByVal(screen, n, startRow, startCol, w, h, 0);
                        if (res && screen[startRow][startCol-1] == 1) {
                            int copyOfScreen[][] = getScreenCopy(screen, n);
                            fillByVal(copyOfScreen, n, startRow, startCol, w, h, 1);
                            boolean lIsFound = isFilledAndCoverLikeL(copyOfScreen, n, startRow, startCol, w, h);
                            if (lIsFound) return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    boolean isFilledAndCoverLikeL(int[][] screen, int n, int inStartRow, int inStartCol, int inW, int inH) {
        int sum = getSumOfScreen(screen, n);
        for(int w = 1; w <= n; w++) {
            for(int h = 1; h <= n; h++) {
                for(int startRow = 1; startRow <= n - h + 1; startRow++) {
                    for(int startCol = 1; startCol <= n - w + 1; startCol++) {
                        boolean res = isFilledByValAndSum(screen, n, startRow, startCol, w, h, 1, sum);
                        if (res) {
                            int endRow = startRow + h - 1;
                            int endCol = startCol + w - 1;
                            int inEndRow = inStartRow + inH - 1;
                            int inEndCol = inStartCol + inW - 1;
                            return (startRow == inStartRow && startCol < inStartCol
                                && endRow > inEndRow && endCol == inEndCol);
                        }
                    }
                }
            }
        }
        return false;
    }

    boolean isLetterC(int[][] screen, int n) {
        for(int w = 1; w <= n; w++) {
            for(int h = 1; h <= n; h++) {
                for(int startRow = 1; startRow <= n - h + 1; startRow++) {
                    for(int startCol = 1; startCol <= n - w + 1; startCol++) {
                        boolean res = isFilledByVal(screen, n, startRow, startCol, w, h, 0);
                        if (res && screen[startRow-1][startCol-1] == 1) {
                            int copyOfScreen[][] = getScreenCopy(screen, n);
                            fillByVal(copyOfScreen, n, startRow, startCol, w, h, 1);
                            boolean cIsFound = isFilledAndCoverLikeC(copyOfScreen, n, startRow, startCol, w, h);
                            if (cIsFound) return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    boolean isFilledAndCoverLikeC(int[][] screen, int n, int inStartRow, int inStartCol, int inW, int inH) {
        int sum = getSumOfScreen(screen, n);
        for(int w = 1; w <= n; w++) {
            for(int h = 1; h <= n; h++) {
                for(int startRow = 1; startRow <= n - h + 1; startRow++) {
                    for(int startCol = 1; startCol <= n - w + 1; startCol++) {
                        boolean res = isFilledByValAndSum(screen, n, startRow, startCol, w, h, 1, sum);
                        if (res) {
                            int endRow = startRow + h - 1;
                            int endCol = startCol + w - 1;
                            int inEndRow = inStartRow + inH - 1;
                            int inEndCol = inStartCol + inW - 1;
                            return (startRow < inStartRow && startCol < inStartCol
                                && endRow > inEndRow && endCol == inEndCol);
                        }
                    }
                }
            }
        }
        return false;
    }

    boolean isLetterO(int[][] screen, int n) {
        for(int w = 1; w <= n; w++) {
            for(int h = 1; h <= n; h++) {
                for(int startRow = 1; startRow <= n - h + 1; startRow++) {
                    for(int startCol = 1; startCol <= n - w + 1; startCol++) {
                        boolean res = isFilledByVal(screen, n, startRow, startCol, w, h, 0);
                        if (res && screen[startRow-1][startCol-1] == 1) {
                            int copyOfScreen[][] = getScreenCopy(screen, n);
                            fillByVal(copyOfScreen, n, startRow, startCol, w, h, 1);
                            boolean oIsFound = isFilledAndCover(copyOfScreen, n, startRow, startCol, w, h);
                            if (oIsFound) return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    boolean isFilledAndCover(int[][] screen, int n, int inStartRow, int inStartCol, int inW, int inH) {
        int sum = getSumOfScreen(screen, n);
        for(int w = 1; w <= n; w++) {
            for(int h = 1; h <= n; h++) {
                for(int startRow = 1; startRow <= n - h + 1; startRow++) {
                    for(int startCol = 1; startCol <= n - w + 1; startCol++) {
                        boolean res = isFilledByValAndSum(screen, n, startRow, startCol, w, h, 1, sum);
                        if (res) {
                            int endRow = startRow + h - 1;
                            int endCol = startCol + w - 1;
                            int inEndRow = inStartRow + inH - 1;
                            int inEndCol = inStartCol + inW - 1;
                            return (startRow < inStartRow && startCol < inStartCol
                                && endRow > inEndRow && endCol > inEndCol);
                        }
                    }
                }
            }
        }
        return false;
    }

    boolean isLetterI(int[][] screen, int n) {
        for(int w = 1; w <= n; w++) {
            for(int h = 1; h <= n; h++) {
                for(int startRow = 1; startRow <= n - h + 1; startRow++) {
                    for(int startCol = 1; startCol <= n - w + 1; startCol++) {
                        boolean res = isFilledByValAndSum(screen, n, startRow, startCol, w, h, 1, onSum);
                        if (res) return true;
                    }
                }
            }
        }
        return false;
    }

    boolean isFilledByValAndSum(int[][] screen, int n, int startRow, int startCol, int w, int h, int val,
                                int targetSum) {
        if ((startRow + h - 1) > n || (startCol + w - 1) > n) return false;
        int filledByVal = 0;
        for(int r = startRow; r < startRow + h; r++) {
            for(int c = startCol; c < startCol + w; c++) {
                if (screen[r][c] != val) {
                    return false;
                }
                filledByVal++;
            }
        }
        return (filledByVal == targetSum);
    }

    boolean isFilledByVal(int[][] screen, int n, int startRow, int startCol, int w, int h, int val) {
        if ((startRow + h - 1) > n || (startCol + w - 1) > n) return false;
        for(int r = startRow; r < startRow + h; r++) {
            for(int c = startCol; c < startCol + w; c++) {
                if (screen[r][c] != val) {
                    return false;
                }
            }
        }
        return true;
    }

    void fillByVal(int[][] screen, int n, int startRow, int startCol, int w, int h, int val) {
        for(int r = startRow; r < startRow + h; r++) {
            for(int c = startCol; c < startCol + w; c++) {
                screen[r][c] = val;
            }
        }
    }

    int[][] getScreenCopy(int[][] screen, int n)  {
        int[][] res = new int[n+2][n+2];
        for(int i = 0; i < n+2; i++) {
            for(int j = 0; j < n+2; j++) {
                res[i][j] = screen[i][j];
            }
        }
        return res;
    }

    // для поиска H или P удобно инвертировать массив
    // далее при поиске прямоугольников для каждого верхнего левого угла (startRow, startCol)
    // ищем включенный прямоугольник с максимальной суммой (w + h)
    // таких должно быть ровно два
    // если их ровно 2 остается только проверить соответствующие буквам ограничения на их расположение
    int[][] getInvertedScreenCopy(int[][] screen, int n)  {
        int[][] res = new int[n+2][n+2];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                res[i][j] = 1 - screen[i][j];
            }
        }
        return res;
    }

    int getSumOfScreen(int[][] screen, int n) {
        int sum = 0;
        for(int i = 0; i < n+2; i++) {
            for(int j = 0; j < n+2; j++) {
                if(screen[i][j] == 1) sum++;
            }
        }
        return sum;
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