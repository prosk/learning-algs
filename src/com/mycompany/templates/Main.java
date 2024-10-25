package com.mycompany.templates;

/*

   Codeforces user: ankush9140

    ॐ भूर् भुवः स्वः।
    तत् सवितुर्वरेण्यं।
    भर्गो देवस्य धीमहि।
    धियो यो नः प्रचोदयात् ॥

  @Author  : ANKUSH
  @Country : INDIA

 */
import java.io.*;
import java.util.*;

public class Main { // Codechef {

    static class Pair {

        long val;
        long freq;

        public Pair(long val, long freq) {
            this.val = val;
            this.freq = freq;
        }
        /*
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            pair pair = (pair) o;
            return val1 == pair.val1 && val2 == pair.val2;
        }

        @Override
        public int hashCode() {
            return 31 * Long.hashCode(val1) + Long.hashCode(val2);
        }

        @Override
        public String toString() {
            return "pair{" + "x=" + val1 + ", y=" + val2 + '}';
        }
         */
    }

    static long MOD = (long) 1e9 + 7;
    static FastReader in;
    static FastWriter p;

    public static void main(String[] args) {
        int TTT = 0;
        try {
            in = new FastReader();
            p = new FastWriter();
            int testCases = in.nextInt();
            while (TTT++ != testCases) {
                //write code here
                int n = in.nextInt();
                long arr[] = new long[n];
                for(int i=0;i<n;i++)arr[i] = in.nextLong();
                TreeMap<Long,Integer> mpp = new TreeMap<>();
                mpp.put(0l,0);
                long sum = 0;
                long ans = 0;
                for(int i=0;i<n;i++){
                    sum += arr[i];
                    if(mpp.get(sum)!=null){
                        ans++;
                        mpp.clear();
                        sum = 0;
                    }
                    mpp.put(sum,0);
                }
                p.pln(ans);
            }
            p.close();
        } catch (Exception e) {
            System.out.println(e);

        }
    }
    public static boolean possible(long x,int arr[],int m){
        int n = arr.length;
        long vis[] = new long[m+1];
        int cnt = n;
        for(int i=0;i<n;i++){
            if(vis[arr[i]]<x){
                vis[arr[i]]++;
                cnt--;
            }
        }
        PriorityQueue<Long> pq = new PriorityQueue<>((a,b)->Long.compare(a, b));
        for(int i=1;i<=m;i++){
            pq.add(vis[i]);
        }
        while(cnt>0){
            long val = pq.poll();
            if(val>=x)return false;
            while(val+2<=x){
                val+=2;
                cnt--;
            }
        }
        return true;
    }
    /*-------------------------------------------  MATHS     MATHS     MATHS   ------------------------------------ */
    public static long sqrt(long x) {
        long low = 0, high = 3037000499L;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            long square = mid * mid;

            if (square == x) {
                return mid;
            } else if (square < x) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return high;
    }

    public static boolean isPrime(long number) {
        if (number <= 1) {
            return false;
        }
        if (number <= 3) {
            return true;
        }
        if (number % 2 == 0 || number % 3 == 0) {
            return false;
        }

        long sqrt = (long) Math.sqrt(number);
        for (long i = 5; i <= sqrt; i += 6) {
            if (number % i == 0 || number % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

    public static long power(long a, long b) {
        long ans = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                ans *= a;
            }
            a *= a;
            b >>= 1;
        }
        return ans;
    }

    public static long power_mod(long a, long b) {
        long ans = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                ans *= a;
                ans %= MOD;
            }
            a *= a;
            a %= MOD;
            b >>= 1;
        }
        return ans;
    }

    static final int SIZE = (int) (2000);
    private static long[] fact = new long[SIZE + 1];
    private static boolean[] isPrime = new boolean[SIZE + 1];

    public static long add_mod(long a, long b) {
        return ((a % MOD) + (b % MOD)) % MOD;
    }

    public static long sub_mod(long a, long b) {
        return (((a % MOD) - (b % MOD)) + MOD) % MOD;
    }

    public static long multiply_mod(long a, long b) {
        return ((a % MOD) * (b % MOD)) % MOD;
    }

    public static long inv(long a) {
        return power_mod(a, MOD - 2);
    }

    public static long divide_mod(long a, long b) {
        return multiply_mod(a, inv(b));
    }

    public static long nCr(long n, long r) {
        if (n < r) {
            return 0;
        }
        return divide_mod(fact[(int) n], multiply_mod(fact[(int) r], fact[(int) (n - r)]));
    }

    public static long nPr(long n, long r) {
        if (n < r) {
            return 0;
        }
        return divide_mod(fact[(int) n], fact[(int) (n - r)]);
    }

    public static void preFactorial() {
        fact[0] = 1;
        for (int i = 1; i <= SIZE; i++) {
            fact[i] = multiply_mod(i, fact[i - 1]);
        }
    }

    public static void Sieve_Of_Eratosthenes() {
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; i * i <= SIZE; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= SIZE; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }

    public static long gcd(long x, long y) {
        if (x > y) {
            return gcd(y, x);
        }
        if (y == 0) {
            return x;
        }
        if (x == 0) {
            return y;
        }
        return gcd(y % x, x);
    }

    public static long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }

    /*----------------------------------------  ADVANCED DATA STRUCTURES  ADVANCED DATA STRUCTURES  ------------------------------------ */
    public static class DisjointSet {

        private long[] parent;
        private long[] size;

        public DisjointSet(int n) {
            parent = new long[n];
            size = new long[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public long findParent(long node) {
            if (node == parent[(int) node]) {
                return node;
            }
            return parent[(int) node] = findParent(parent[(int) node]);
        }

        public void unionSize(long node1, long node2) {
            long p1 = findParent(node1);
            long p2 = findParent(node2);
            if (p1 == p2) {
                return;
            }
            if (size[(int) p1] > size[(int) p2]) {
                size[(int) p1] += size[(int) p2];
                parent[(int) p2] = p1;
            } else {
                size[(int) p2] += size[(int) p1];
                parent[(int) p1] = p2;
            }
        }

        public long findSize(long node) {
            long p1 = findParent(node);
            return size[(int) p1];
        }
    }

    static class Segment_Tree {

        long arr[];
        int n;
        long seg[];

        public Segment_Tree(long arr[], int n) {
            this.arr = arr;
            this.n = n;
            this.seg = new long[(4 * n) + 1];
            build(0, 0, n - 1);
        }

        public void build(int ind, int low, int high) {
            if (low == high) {
                seg[ind] = arr[low];
                return;
            }
            int mid = (low + high) / 2;
            build((2 * ind) + 1, low, mid);
            build((2 * ind) + 2, mid + 1, high);
            seg[ind] = Math.max(seg[2 * ind + 1], seg[2 * ind + 2]);
        }

        public long query2(int ind, int low, int high, int left, int right) {
            if (low >= left && high <= right) {
                return seg[ind];
            }
            if (low > right || high < left) {
                return 0;
            }
            int mid = (low + high) / 2;
            long l = query2(2 * ind + 1, low, mid, left, right);
            long r = query2(2 * ind + 2, mid + 1, high, left, right);
            return Math.max(l, r);
        }

        public long query(int l, int r) {
            return query2(0, 0, n - 1, l, r);
        }

        public void pointupdate2(int ind, int low, int high, int node, int val) {
            if (low == high) {
                seg[ind] = val;
            } else {
                int mid = (low + high) / 2;
                if (node <= mid && node >= low) {
                    pointupdate2(2 * ind + 1, low, mid, node, val);
                } else {
                    pointupdate2(2 * ind + 2, mid + 1, high, node, val);
                }
                seg[ind] = Math.max(seg[2 * ind + 1], seg[2 * ind + 2]);
            }
        }

        public void point_update(int node, int val) {
            pointupdate2(0, 0, n - 1, node, val);
        }
    }

    /*-------------------------------------  FAST READER AND WRITER      FAST READER AND WRITER  ------------------------------------ */
    static class FastReader {

        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine().trim();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    static class FastWriter {

        private final BufferedWriter bw;

        public FastWriter() {
            this.bw = new BufferedWriter(new OutputStreamWriter(System.out));
        }

        public void p(Object object) throws IOException {
            bw.append("" + object);
        }

        public void pln(Object object) throws IOException {
            p(object);
            bw.append("\n");
        }

        public void y() throws IOException {
            p("Yes");
            bw.append("\n");
        }

        public void n() throws IOException {
            p("No");
            bw.append("\n");
        }

        public void arr(long arr[]) throws IOException {
            for (int i = 0; i < arr.length; i++) {
                p(arr[i] + " ");
            }
            bw.append("\n");
        }

        public void lst(List<Long> lst) throws IOException {
            for (int i = 0; i < lst.size(); i++) {
                p(lst.get(i) + " ");
            }
            bw.append("\n");
        }

        public void close() throws IOException {
            bw.close();
        }
    }

}
