package com.mycompany.yandex.training.warmup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

// https://contest.yandex.ru/contest/45468/problems/3/
// В видео разбора другое решение (через бинарный поиск), можно добавить такое альтернативное решение сюда.
// https://www.youtube.com/watch?v=O26-2-94BDk&t=159s
public class CollectorDiego {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new CollectorDiego().run();
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
        // Ввод данных
        int n = readInt();
        int[] diegos = new int[n];
        for(int i = 0; i < n; i++) {
            diegos[i] = readInt();
        }

        int k = readInt();
        Collector[] collectors = new Collector[k];
        for(int i = 0; i < k; i++) {
            int currMin = readInt();
            collectors[i] = new Collector(currMin, i);
        }

        // Сортировка
        Arrays.sort(diegos);
        Arrays.sort(collectors);

        int[] res = new int[k];

        int d = 0, c = 0, uniqueCnt = 0;
        int prev = -1;
        while(d < n && c < k) {
            if (diegos[d] >= collectors[c].minValue) {
                res[collectors[c].collectorIndex] = uniqueCnt;
                c++;
            } else {
                if (diegos[d] != prev) {
                    uniqueCnt++;
                }
                prev = diegos[d];
                d++;
            }
        }
        // если не заполнили полностью массив res
        if (d == n && c < k) {
            for (int i = c; i < k; i++) {
                res[collectors[i].collectorIndex] = uniqueCnt;
            }
        }
        // вывод результатов
        for(int j = 0; j < k; j++) {
            out.println(res[j]);
        }

    }

    private static class Collector implements Comparable<Collector> {
        @Override
        public int compareTo(Collector o) {
            int c1 = Integer.compare(this.minValue, o.minValue);
            return c1 == 0 ? Integer.compare(this.collectorIndex, o.collectorIndex) : c1;
        }

        int minValue;
        int collectorIndex;

        Collector(int minValue, int collectorIndex) {
            this.minValue = minValue;
            this.collectorIndex = collectorIndex;
        }

    }

    private void solve() {
        int n = readInt();

        List<CollectionItem> inputNums = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            int curr = readInt();
            CollectionItem ci = new CollectionItem(CollectionItem.ItemType.DIEGO, curr, 0);
            inputNums.add(ci);
        }

        int k = readInt();

        for(int i = 0; i < k; i++) {
            int curr = readInt();
            CollectionItem ci = new CollectionItem(CollectionItem.ItemType.MIN_FOR_COLLECTOR, curr, i);
            inputNums.add(ci);
        }

        Collections.sort(inputNums);

        int[] res = new int[k];

        int currUniqueCnt = 0;
        int prev = -1;
        for(CollectionItem item: inputNums) {
            if (item.itemType == CollectionItem.ItemType.DIEGO) {
                if (item.labelValue != prev) {
                    currUniqueCnt++;
                    prev = item.labelValue;
                }
            } else {
                // коллекционер
                res[item.collectorIndex] = currUniqueCnt;
            }
        }

        for(int j = 0; j < k; j++) {
            out.println(res[j]);
        }

    }

    private static class CollectionItem implements Comparable<CollectionItem> {
        @Override
        public int compareTo(CollectionItem o) {
            int c1 = Integer.compare(this.labelValue, o.labelValue);
            return c1 == 0 ? Integer.compare(this.itemType.ordinal(), o.itemType.ordinal()) : c1;
        }

        enum ItemType { MIN_FOR_COLLECTOR, DIEGO };
        ItemType itemType;
        int labelValue;
        int collectorIndex;

        CollectionItem(ItemType itemType, int labelValue, int collectorIndex) {
            this.itemType = itemType;
            this.labelValue = labelValue;
            this.collectorIndex = collectorIndex;
        }

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
