package com.mycompany.yandex.training.warmup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class BoringLecture {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new BoringLecture().run();
    }

    private void run() {
        try {
            long timeStart = System.currentTimeMillis();
            // solve();
            optimizedSolve();
            out.close();
            long timeEnd = System.currentTimeMillis();
            System.err.println("Time(ms) = " + (timeEnd - timeStart));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void optimizedSolve() {
        String inputWord = readString();
        int[] currCharCnt = new int[26];
        long[] res = new long[26];
        int len = inputWord.length();

        for(int i = 0; i < len; i++) {
            int currChar = (int) inputWord.charAt(i) - 97;
            currCharCnt[currChar]++;
            for(int j = 0; j < 26; j++) {
                res[j] += currCharCnt[j]*(len - (long)(len-(i+1))*2);
            }
        }
        // выводим результат
        for(int i = 0; i < 26; i++) {
            if (res[i] > 0) {
                out.printf("%c: %d\n", (char) (97 + i), res[i] );
            }
        }
    }

    private void solve() {
        String inputWord = readString();
        int[] currSymbolsCnt = new int[26];
        int len = inputWord.length();

        // считаем кол-во для каждой буквы
        for(int i = 0; i < len; i++) {
            int currChar = (int) inputWord.charAt(i) - 97;
            currSymbolsCnt[currChar]++;
        }


        int left = 0, right = 0;
        boolean isForwardDirection = true;
        int[] res = Arrays.copyOf(currSymbolsCnt, currSymbolsCnt.length);

        while(true) {
            // переходим к следующей конфигурации
            if (isForwardDirection) {
                if (right < (len-left-1)) {
                    right++;

                    int newDeletedSymbol = (int) inputWord.charAt(len - right) - 97;
                    currSymbolsCnt[newDeletedSymbol]--;
                } else {
                    // Меняем направление на обратное
                    left++;
                    if (left == len) break;

                    int newDeletedSymbol = (int) inputWord.charAt(left-1) - 97;
                    currSymbolsCnt[newDeletedSymbol]--;

                    right--;
                    isForwardDirection = false;

                    int newAddedSymbol = (int) inputWord.charAt(len - 1 - right) - 97;
                    currSymbolsCnt[newAddedSymbol]++;

                }
            } else {
                if (right > 0) {
                    right--;

                    int newAddedSymbol = (int) inputWord.charAt(len - 1 - right) - 97;
                    currSymbolsCnt[newAddedSymbol]++;
                } else {
                    left++;
                    if (left == len) break;

                    int newDeletedSymbol = (int) inputWord.charAt(left-1) - 97;
                    currSymbolsCnt[newDeletedSymbol]--;

                    isForwardDirection = true;
                }
            }
            // Получив вхождения следующей конфигурации, прибавлюяем их к текущей
            for (int i = 0; i < 26; i++) {
                res[i] += currSymbolsCnt[i];
            }
        }
        // выводим результат
        for(int i = 0; i < 26; i++) {
            if (res[i] > 0) {
                out.printf("%c: %d\n", (char) (97 + i), res[i] );
            }
        }

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
