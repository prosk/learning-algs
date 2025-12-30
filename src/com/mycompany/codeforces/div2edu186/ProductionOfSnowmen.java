package com.mycompany.codeforces.div2edu186;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/*
  Example of a clean solution in C++ from ksun48

  The approach is the same as in my solution

  void solve(){
	int N;
	cin >> N;
	vector<vector<int> > S(3, vector<int>(N));
	for(auto& x : S) for(auto& y : x) cin >> y;
	int64_t ans = N;
	for(int i = 0; i < 2; i++){
		int cnt = 0;
		for(int l = 0; l < N; l++){
			int ok = 1;
			for(int a = 0; a < N; a++){
				if(S[i][a] >= S[i+1][(a+l) % N]) ok = 0;
			}
			cnt += ok;
		}
		ans *= cnt;
	}
	cout << ans << '\n';
}

 */

public class ProductionOfSnowmen {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new ProductionOfSnowmen().run();
        out.close();
    }

    void run() {
        int t = readInt();
        while (t-- > 0) {
            cleanSolve();
        }
    }

    void solve() {
        int n = readInt();
        long nLong = n;
        int MAX = 3*n;

        int[] a = new int[n];
        int minA = MAX + 1, maxA = 0;
        for(int i = 0; i < n; i++) {
            a[i] = readInt();
            minA = Math.min(minA, a[i]);
            maxA = Math.max(maxA, a[i]);
        }

        int[] b = new int[n];
        int minB = MAX + 1, maxB = 0;
        for(int i = 0; i < n; i++) {
            b[i] = readInt();
            minB = Math.min(minB, b[i]);
            maxB = Math.max(maxB, b[i]);
        }

        int[] c = new int[n];
        int minC = MAX + 1, maxC = 0;
        for(int i = 0; i < n; i++) {
            c[i] = readInt();
            minC = Math.min(minC, c[i]);
            maxC = Math.max(maxC, c[i]);
        }

        if (maxA < minB && maxB < minC) {
            out.println(nLong*nLong*nLong);
            return;
        }
        long aCnt = 0, bCnt = 0;
        for(int startPosInB = 0; startPosInB < n; startPosInB++) {
            boolean[] checkRes = checkForShiftedB(a, b, c, startPosInB);
            aCnt += checkRes[0] ? 1 : 0;
            bCnt += checkRes[1] ? 1 : 0;
        }
        long acMult = aCnt * bCnt;
        //int mult = (maxA < minB || maxB < minC) ? n*n : n;
        //int ans = bShiftedCnt * mult;

        long ans = nLong * acMult;
        out.println(ans);
    }

    void cleanSolve() {
        int n = readInt();
        int[] a = readArray(n);
        int[] b = readArray(n);
        int[] c = readArray(n);
        long aCnt = 0, bCnt = 0;
        for(int startPosInB = 0; startPosInB < n; startPosInB++) {
            boolean[] checkRes = checkForShiftedB(a, b, c, startPosInB);
            aCnt += checkRes[0] ? 1 : 0;
            bCnt += checkRes[1] ? 1 : 0;
        }
        long ans = n * aCnt * bCnt;
        out.println(ans);
    }

    int[] readArray(int n) {
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = readInt();
        }
        return arr;
    }

    boolean[] checkForShiftedB(int[] a, int[] b, int[] c, int startPosInB) {
        int indB = startPosInB;
        int n = a.length, cntA = 0, cntB = 0;
        for(int i = 0; i < n; i++) {
            if (a[i] >= b[indB] && b[indB] >= c[i]) {
                break;
            }
            if (a[i] < b[indB]) {
                cntA++;
            }
            if (b[indB] < c[i]) {
                cntB++;
            }
            indB = (indB + 1) % n;
        }
        return new boolean[] {cntA == n, cntB == n};
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