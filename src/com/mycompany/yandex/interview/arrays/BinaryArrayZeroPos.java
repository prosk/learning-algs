package com.mycompany.yandex.interview.arrays;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BinaryArrayZeroPos {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        //new BinaryArrayZeroPos().run();
        new BinaryArrayZeroPos().runTests();
        out.close();
    }

    /*
       ЗАДАЧА
       Для массива из нулей и единиц надо найти позицию pos, для которой значение
       равно 0, а расстояние до ближайшей единицы максимально
     */

    void run() {
        int n = readInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = readInt();
        }
        int pos = solveOpt(arr);
        out.println(pos);
    }

    void runTests() {
        int pos = solveOpt2(new int[]{0, 0, 1});
        out.println(pos == 0 ? "OK" : "ERROR");

        pos = solveOpt2(new int[]{1, 1, 1, 1, 0});
        out.println(pos == 4 ? "OK" : "ERROR");

        pos = solveOpt2(new int[]{1, 0, 0, 0, 0});
        out.println(pos == 4 ? "OK" : "ERROR");

        pos = solveOpt2(new int[]{1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1});
        out.println(pos == 7 ? "OK" : "ERROR");

        pos = solveOpt2(new int[]{1, 0, 0, 1, 1, 0, 1});
        out.println(pos == 1 ? "OK" : "ERROR");

        pos = solveOpt2(new int[]{0, 0, 1, 0, 0, 0});
        out.println(pos == 5 ? "OK" : "ERROR");

        pos = solveOpt2(new int[]{0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1});
        out.println(pos == 13 ? "OK" : "ERROR");
    }

    // идея упрощения - вообще забываем про нули
    // оперируем только текущая позиция единицы и предыдущая позиция единицы
    int solveOpt2(int[] arr) {
        int lastOnePos = -1, pos = -1, maxDist = 0;
        for(int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                if (lastOnePos >= 0) {
                    int dist = (i - lastOnePos)/2;
                    if (dist > maxDist) {
                        maxDist = dist;
                        pos = lastOnePos + dist;
                    }
                } else if (i > 0) {
                    maxDist = i;
                    pos = 0;
                }
                lastOnePos = i;
            }
        }
        if ((arr.length - 1 - lastOnePos) > maxDist)
            pos = arr.length-1;
        return pos;
    }

    // oneWasSeen - булевый признак, была ли уже 1 в ранее просмотренной части последовательности
    // zeroCnt - в начале каждой итерации цикла равна длине накопленной последовательности из нулей,
    // если ранее был хотя бы один или более последовательных нулей
    // maxDist - текущее значение максимального расстояния от 0 до 1 в просмотренной ранее части
    // pos - позиция нуля для которой было рассчитано maxDist
    // в предыдущей реализации для случая 2-х позиций нуля посередине между единицами мы брали левую позицию,
    // так как вычисляли как lastOnePos + dist, а здесь мы берем правую так как вычисляем как
    // i - dist, поэтому хранить lastOnePos вместо булевого флага тоже имеет смысл - чтобы упростить вычисление
    // середины
    int solveOpt(int[] arr) {
        boolean oneWasSeen = false;
        int zeroCnt = 0, maxDist = 0, pos = -1;
        for(int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                zeroCnt++;
            } else {
                if (oneWasSeen) {
                    int dist = (zeroCnt + 1)/2;
                    if (dist > maxDist) {
                        maxDist = dist;
                        pos = i - dist;
                    }
                } else {
                    maxDist = i;
                    pos = 0;
                    oneWasSeen = true;
                }
                zeroCnt = 0;
            }
        }
        if (zeroCnt > maxDist)
            pos = arr.length-1;
        return pos;
    }

    int solve(int[] arr) {
        int len = arr.length;
        int lastOnePos = -1;
        int cnt = 0, pos = -1;
        int ans = 0;   // 101  ans = 1
        for(int i = 0; i < len; i++) {
            if (arr[i] == 0) {
                cnt++;
            } else {
                if (cnt == 0) {
                    lastOnePos = i;
                    continue;
                }
                if (lastOnePos >= 0) {
                    // слева 1     101  1001  1000001
                    int dist = (cnt + 1)/2;
                    if (dist > ans) {
                        ans = dist;
                        pos = lastOnePos + dist;
                    }
                } else {
                    // слева край 01  001  0001 00001
                    ans = i;
                    pos = 0;
                }
                cnt = 0;
                lastOnePos = i;
            }
        }
        if (cnt > 0) {
            int dist = cnt;
            if (dist > ans) {
                pos = len-1;
            }
        }
        return pos;
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