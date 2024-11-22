package com.mycompany.yandex.training60.trees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Woodpeckers {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    static int SIZE = 0;
    static int[] fact;

    int isolatedCnt = 0;
    int componentsCnt = 0;

    int allLeftHouseCnt = 0;
    int allRightHouseCnt = 0;

    static int MODULO = 0;

    List<Component> cmps = new ArrayList<>();

    public static void main(String[] args) {
        new Woodpeckers().run();
        out.close();
    }

    void run() {
        int N = readInt();
        int M = readInt();
        int K = readInt();
        MODULO = K;
        SIZE = N;
        fact = new int[SIZE + 1];
        preFactorial();

        List<List<Integer>> adjList  = new ArrayList<>();
        List<Vertex> vertexes = new ArrayList<>();
        adjList.add(new ArrayList<>());
        for (int i = 1; i <= N; i++) {
            adjList.add(new ArrayList<>());
            vertexes.add(new Vertex(i, 0)); // index in list = vertex num - 1
        }
        // getting edges (u, v) and filling adj
        for (int i = 1; i <= M; i++) {
            int from = readInt();
            int to = readInt();
            addEdge(adjList, from, to);
            vertexes.get(from-1).degree++;
            vertexes.get(to-1).degree++;
        }
        // sort vertexes by degree
        Collections.sort(vertexes, (v1, v2) -> Integer.compare(v2.degree, v1.degree));
        // solve
        boolean[] visited = new boolean[N+1];
        boolean hasCorrectStructure = true;
        // цикл по вершинам от максималььной степени к минимальной
        for(int i = 0; i < vertexes.size(); i++) {
            int vNum = vertexes.get(i).num;
            if (adjList.get(vNum).size() == 0) {
                // изолированный дятел без друзей, значит далее только изолированные
                isolatedCnt = N - i;
                break;
            }
            if (!visited[vNum]) {
                // непосещенный компонент связности
                componentsCnt++;
                Component cmp = new Component();
                cmp.type = CType.SEQUENCE; // если будет где-то 2 сына переприсвоится на TREE
                cmps.add(cmp);
                hasCorrectStructure = dfs(visited, adjList, vNum, 0, true, cmp);
                if (!hasCorrectStructure) break;
                allLeftHouseCnt += cmp.leftHouseCnt;
                allRightHouseCnt += cmp.rightHouseCnt;
                // учет симметрии и поворота "сверху вниз"
                if (cmp.leftHouseCnt == 1 || cmp.rightHouseCnt == 1) {
                    // цепочка a--b либо пучок от 1 к N
                    cmp.statesCnt = mult(cmp.statesCnt, 2);
                } else {
                    // возможны и симметрия и поворот сверху вниз
                    cmp.statesCnt = mult(cmp.statesCnt, 4);
                }
            }
        }
        if (!hasCorrectStructure) {
            out.println("0");
            return;
        }
        // перебираем порядок компонентов и для каждого порядка все комбинации состояний
        int cmpOrdersCnt = getFactorial(componentsCnt);
        int statesCompCnt = 1;
        for(Component cmp: cmps) {
            statesCompCnt = mult(statesCompCnt, cmp.statesCnt);
        }
        int combForConnected = mult(cmpOrdersCnt, statesCompCnt);
        // к зафиксированному порядку компонентов и состоянию каждого компонента
        // добавляем перебор добавления к размещению изолированных дятлов
        if (isolatedCnt > 0) {
            int slots = isolatedCnt;
            int delimiters = allLeftHouseCnt + allRightHouseCnt + 1;
            int isolatedComb;
            if (N < 1000)
                isolatedComb = binomCoeff(slots+delimiters, slots);
            else
                isolatedComb = binomCoeffModulo(slots+delimiters, slots, MODULO);
            int isolatedOrderedComb = mult(isolatedComb, getFactorial(slots));
            combForConnected = mult(combForConnected, isolatedOrderedComb);
        }
        out.println(combForConnected);
    }

    private boolean dfs(boolean[] visited, List<List<Integer>> adj,
                     int fromNode, int parentNode, boolean isLeftHouse, Component cmp) {
        if (visited[fromNode]) {
            return false; // cycle
        }
        visited[fromNode] = true;
        // increment house cnt for fromNode
        if (isLeftHouse) cmp.leftHouseCnt++; else cmp.rightHouseCnt++;
        int adjCnt = adj.get(fromNode).size();

        // проверяем корректность текущего "пучка" парент - чайлды
        int childCnt = 0;
        int childWithOwnChildCnt = 0;
        int maxChildWithOwnChildCnt = (parentNode == 0) ? 2 : 1;
        int freeChildCnt = 0;
        for (int i = 0; i < adjCnt; i++) {
            int childNode = adj.get(fromNode).get(i);
            if (childNode != parentNode) {
                childCnt++;
                boolean childHasOwnChilds = adj.get(childNode).size() > 1;
                if (childHasOwnChilds) {
                    childWithOwnChildCnt++;
                } else {
                    freeChildCnt++;
                }
                if (childWithOwnChildCnt > maxChildWithOwnChildCnt) {
                    return false;
                }
                boolean hasCorrectStructure = dfs(visited, adj, childNode, fromNode, !isLeftHouse, cmp);
                if (!hasCorrectStructure) return false;
            }
        }
        // save local parent--childs combinations count
        if (freeChildCnt > 0) {
            cmp.statesCnt = mult(cmp.statesCnt, getFactorial(freeChildCnt));
        }
        // if all checks is OK then hasCorrectStructure = true
        return true;
    }

    private static int mult(long a, long b) { return (int)((a * b) % MODULO); }
    private static int sum(long a, long b) { return (int)((a + b) % MODULO); }

    public static void preFactorial() {
        fact[0] = 1;
        for (int i = 1; i <= SIZE; i++) {
            fact[i] = mult(i, fact[i - 1]);
        }
    }

    public static int getFactorial(int a) {
        if (a <= SIZE) {
            return fact[a];
        }
        int res = fact[SIZE];
        for (int i = SIZE+1; i <= a; i++) {
            res = mult(i, res);
        }
        return res;
    }

    private void addEdge(List<List<Integer>> adj, int i, int j)
    {
        adj.get(i).add(j);
        adj.get(j).add(i);
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

    public static class Vertex {
        int num;
        int degree;

        public Vertex(int num, int degree) {
            this.num = num;
            this.degree = degree;
        }
    }

    public static class Component {
        CType type;
        int leftHouseCnt;
        int rightHouseCnt;

        int statesCnt = 1;
    }

    public enum CType {
        SEQUENCE, TREE
    }

    static int binomCoeff(int n, int k) {
        int[] pascalTriangleRow = new int[k + 1];

        // Since C(n, k) = C(n, n-k)
        if (k > n - k)
            k = n - k;

        pascalTriangleRow[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = Math.min(i, k); j > 0; j--)
                pascalTriangleRow[j] = sum(pascalTriangleRow[j], pascalTriangleRow[j - 1]);
        }
        return pascalTriangleRow[k];
    }

    public static int binomCoeffModulo(int n, int k, int mod) {
        List<Integer> primes = findPrimeFactors(mod);
        List<Integer> rem = new ArrayList<>();

        for (int m : primes)
            rem.add(Lucas(n, k, m));

        int min_x = 0;
        while (true) {
            boolean found = true;
            for (int i = 0; i < primes.size(); i++) {
                if (min_x % primes.get(i) != rem.get(i)) {
                    found = false;
                    break;
                }
            }
            if (found) {
                return min_x;
            }
            min_x++;
        }
    }

    // Function to utilize the Lucas theorem
    private static int Lucas(int n, int r, int m) {
        // If (r > n) return 0;
        if (r == 0)
            return 1;

        int ni = n % m;
        int ri = r % m;
        return (pascal(ni, ri, m)
                * Lucas(n / m, r / m, m))
                % m;
    }

    // Pascal triangle method to find nCr
    private static int pascal(int n, int r, int m) {
        if (r == 0 || r == n)
            return 1;

        // r = Math.min(r, n - r);
        int[] nCr = new int[r + 1];
        nCr[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = Math.min(r, i); j > 0; j--)
                nCr[j] = (nCr[j] + nCr[j - 1]) % m;
        }
        return nCr[r];
    }

    private static List<Integer> findPrimeFactors(int n) {
        List<Integer> primes = new ArrayList<>();
        if (n % 2 == 0) {
            primes.add(2);
            while (n % 2 == 0)
                n >>= 1;
        }
        for (int i = 3; n > 1; i += 2) {
            if (n % i == 0) {
                primes.add(i);
                while (n % i == 0)
                    n /= i;
            }
        }
        return primes;
    }

}