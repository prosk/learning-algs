package com.mycompany.yandex.training30.queues;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class HeapSort {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter out = new PrintWriter(System.out);
    StringTokenizer tok = new StringTokenizer("");
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new HeapSort().run();
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
        int elemsCnt = readInt();
        int[] elems = new int[elemsCnt];
        for(int i = 0; i < elemsCnt; i++) {
            elems[i] = readInt();
        }


        MaxIntHeap myHeap = new MaxIntHeap(elems);
        myHeap.heapify();
        for(int i = elemsCnt - 1; i >= 1; i--) {
            int currMax = myHeap.extractMax();
            elems[i] = currMax;
        }

        StringBuilder sb = new StringBuilder("");
        sb.append(elems[0]);
        for(int j = 1; j < elemsCnt; j++) {
            sb.append(" ");
            sb.append(elems[j]);
        }
        out.println(sb);
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

        public MaxIntHeap(int[] elements) {
            this.elements = elements;
            heapSize = elements.length;
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

        public void heapify() {
            int i = (heapSize >>> 1) - 1;
            for (; i >= 0; i--) {
                siftDown(i);
            }
        }
    }

}
