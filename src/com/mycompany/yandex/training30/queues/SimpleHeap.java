package com.mycompany.yandex.training30.queues;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class SimpleHeap {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new SimpleHeap().run();
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
        MaxIntHeap myHeap = new MaxIntHeap();
        int commandsCnt = readInt();
        int commandCode, elem;
        for(int i = 0; i < commandsCnt; i++) {
            commandCode = readInt();
            if (commandCode == 0) {
                elem = readInt();
                myHeap.insert(elem);
            } else {
                out.println(myHeap.extractMax());
            }
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

    private static class MaxIntHeap {
        private int[] elements;
        private int heapSize;

        public MaxIntHeap() {
            elements = new int[100_000];
            heapSize = 0;
        }

        private int parent(int i) {
            return (i - 1) / 2;
        }

        private int left(int i) {
            return i*2 + 1;
        }

        private int right(int i) {
            return i*2 + 2;
        }

        private boolean hasLeft(int i) {
            return left(i) < heapSize;
        }

        private boolean hasRight(int i) {
            return right(i) < heapSize;
        }

        private void swap(int i, int j) {
            int temp = elements[i];
            elements[i] = elements[j];
            elements[j] = temp;
        }

        private void siftUp(int i) {
            while(i > 0) {
                int p = parent(i);
                if (elements[p] >= elements[i]) {
                    break;
                }
                swap(i, p);
                i = p;
            }
        }

        private void siftDown(int i) {
            while(hasLeft(i)) {
                int leftIndex = left(i);
                int maxChildIndex = leftIndex;
                if(hasRight(i)) {
                    int rightIndex = right(i);
                    if (elements[rightIndex] > elements[leftIndex]) {
                        maxChildIndex = rightIndex;
                    }
                }
                if (elements[maxChildIndex] <= elements[i]) {
                    break;
                }
                swap(i, maxChildIndex);
                i = maxChildIndex;
            }
        }

        public int size() {
            return heapSize;
        }

        public void insert(int elem) {
            elements[heapSize] = elem;
            heapSize++;
            siftUp(heapSize-1);
        }

        public int extractMax() {
            if (heapSize == 0) {
                throw new RuntimeException("Heap is empty");
            }
            int answer = elements[0];
            swap(0, heapSize - 1);
            heapSize--;
            siftDown(0);
            return answer;
        }
    }

}
