package com.mycompany.yandex.training30.warmup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

// https://contest.yandex.ru/contest/45468/problems/2/
// В видео разбора другое решение (через префиксные суммы+ бинарный поиск), можно добавить такое альтернативное решение сюда.
// https://www.youtube.com/watch?v=O26-2-94BDk&t=159s
// Но 2-мя указателями более эффективно
public class BeautifulString {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new BeautifulString().run();
    }

    private void run() {
        try {
            long timeStart = System.currentTimeMillis();
            // solve();
            solveWithTwoPointers();
            out.close();
            long timeEnd = System.currentTimeMillis();
            System.err.println("Time(ms) = " + (timeEnd - timeStart));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void solveWithTwoPointers() {
        int k = readInt();
        String inputString = readString();
        int len = inputString.length();

        // если k больше или равно len - 1, то точно можно сделать всю строку из 1 символа
        if (k >= (len-1)) {
            out.println(len);
            return;
        }
        // 0 =< k < (len - 1)

        int finalMax = 0;
        for(int targetChar = 0; targetChar < 26; targetChar++) {
            // поиск за 1 проход
            int first = 0, second = 0, currLen = 0, replacesCount = 0;
            int maxLen = 0;
            while(second < len) {
                int currChar = (int) inputString.charAt(second) - 97;
                if(currChar == targetChar) {
                    second++;
                } else {
                    if (replacesCount < k) {
                        replacesCount++;
                        second++;
                    } else {
                        // надо убрать первую замену слева, чтобы добавить справа
                        int nextFirstChar;
                        do {
                            nextFirstChar = (int) inputString.charAt(first) - 97;
                            first++;
                        } while (nextFirstChar == targetChar);
                        second++;
                    }
                }
                currLen = second - first;
                maxLen = Math.max(currLen, maxLen);
            }
            finalMax = Math.max(finalMax, maxLen);
        }
        out.println(finalMax);
    }

    private void solve() {
        int k = readInt();
        String inputString = readString();
        int len = inputString.length();

        // если k больше или равно len - 1, то точно можно сделать всю строку из 1 символа
        if (k >= (len-1)) {
            out.println(len);
            return;
        }
        // 0 =< k < (len - 1)

        Map<Integer, Integer> charsToStartIndex = new HashMap<>();
        for(int i = 0; i < len; i++) {
            int currChar = (int) inputString.charAt(i) - 97;
            int startPos = Math.max(0, i - k);
            charsToStartIndex.putIfAbsent(currChar, startPos);
            if (charsToStartIndex.keySet().size() == 26) {
                break;
            }
        }

        int[] currMaxBeautiful = new int[26]; // максимальная красота по каждому символу, сначала 0

        for(int targetChar: charsToStartIndex.keySet()) {
            int currUnusedReplaceCnt = k; // текущий, пока еще неиспользованный запас операций замены символа

            int firstFindedIndex = -1; // первый символ, совпавший с нашим в процессе сборки текущей последовательности
            int firstNotEqualIndex = -1; // первый несовпавший с нашим в процессе сборки
            int currLen = 0; // сколько собрали на данный момент

            int currPosition = charsToStartIndex.get(targetChar);
            int lastStartIndex = currPosition; // последняя позиция символа, с которой начали собирать последовательность-
            // претендент на максимальную красоту

            while (currPosition < len) {
                // 97 - код строчной латинской буквы a
                int currChar = (int) inputString.charAt(currPosition) - 97;
                if (currChar == targetChar) {
                    // встретили наш символ
                    currLen++;
                    if (firstFindedIndex == -1) {
                        firstFindedIndex = currPosition;
                    }
                    currPosition++;
                } else {
                    // другой символ
                    if (firstNotEqualIndex == -1) {
                        firstNotEqualIndex = currPosition;
                    }

                    if (currUnusedReplaceCnt > 0) {
                        // есть запас операций замены
                        currUnusedReplaceCnt--;
                        currLen++;
                        currPosition++;
                    } else {
                        // символ другой, и заменять больше не можем
                        // фиксируем, какую красоту собрали
                        if (currLen > currMaxBeautiful[targetChar]) {
                            currMaxBeautiful[targetChar] = currLen;
                        }
                        // сбрасываем длину в 0
                        currLen = 0;
                        // определяем, на какую позицию перейти
                        if (firstFindedIndex == -1) {
                            // мы так и не нашли наш символ, только делали замены
                            // ищем следующий
                            int pos = currPosition+1;
                            int nextFindedTargetCharPos = -1;
                            while (pos < len) {
                                int nextChar = (int) inputString.charAt(pos) - 97;
                                if (nextChar == targetChar) {
                                    nextFindedTargetCharPos = pos;
                                    break;
                                }
                                pos++;
                            }
                            if (nextFindedTargetCharPos == -1) {
                                // больше нет до конца строки целевых символов
                                break; // переходим на след таргет символ, с этим больше нечего делать
                            } else {
                                currPosition = nextFindedTargetCharPos - k;
                                lastStartIndex = currPosition;
                            }
                        } else {
                            if (firstFindedIndex > lastStartIndex) {
                                currPosition = firstFindedIndex;
                                lastStartIndex = currPosition;
                            } else {
                                currPosition = firstNotEqualIndex+1;
                                lastStartIndex = currPosition;
                            }
                        }
                        // сбрасываем остальное, т.к. мы перешли к новой собираемой последовательности
                        currUnusedReplaceCnt = k;
                        firstFindedIndex = -1;
                        firstNotEqualIndex = -1;
                    }
                }

            }
            // фиксируем, какую красоту собрали, если на последней сборке уперлись в конец строки
            if (currLen > currMaxBeautiful[targetChar]) {
                currMaxBeautiful[targetChar] = currLen;
            }
        }

        // печать
        /*for(int j = 0; j < currMaxBeautiful.length; j++) {
            out.printf("%c: %d\n", (char)(j+97), currMaxBeautiful[j]);
        }*/

        int finalMax = 0;
        for(int j = 0; j < currMaxBeautiful.length; j++) {
            if(currMaxBeautiful[j] > finalMax) {
                finalMax = currMaxBeautiful[j];
            }
        }
        out.println(finalMax);

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
