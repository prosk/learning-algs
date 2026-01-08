package com.mycompany.codeforces.hello2026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/*
  Tutotial:

  Hint 1:
  Considering that in the end only k−1 numbers will remain, which numbers in an interval of length k are invalid?

  Hint 2:
  Consider the Pigeonhole Principle.

  Solution:

  Since only k−1 numbers will remain in the end, it is relatively easy to notice that for any interval of length k,
  numbers within this interval that are greater than or equal to k−1 or have duplicate values are invalid.
  One of these two types of numbers can be removed. Since there are only k−1 numbers from 0 to k−2,
  in any interval of length k, there must be at least one number that can be deleted.
  Therefore, the answer is min(mex(a),k−1).

  Time complexity: O(∑n).

 */

/*
      -- Очень красивое решение от Ormlis (Легендарный гроссмейстер, Федор Ромашов, Россия, Барнаул)

      #define rep(i, n) for (int i = 0; i < (n); ++i)
      using vi = vector<int>;

      void solve() {
        int n; cin >> n;
        int k; cin >> k;
        vi a(n + 1);
        rep(i, n) {
            int x; cin >> x;
            a[x] = 1;
        }
        int r = 0;
        while (r < k - 1 && a[r]) r++;
        cout << r << '\n';
    }

 */

/*
    Very clean solution from ecnerwala

    #include <bits/stdc++.h>

    int main() {
    	using namespace std;
    	ios_base::sync_with_stdio(false), cin.tie(nullptr);

    	int T; cin >> T;
    	while (T--) {
    		int N, K; cin >> N >> K;
    		std::vector<int> A(N);
    		for (auto& a : A) cin >> a;
    		std::vector<bool> has(N+1);
    		for (auto a : A) { has[a] = true; }
    		int ans = 0;
    		while (ans < K-1 && has[ans]) ans++;
    		cout << ans << '\n';
    	}

    	return 0;
    }
 */

public class YetAnotherMexTask {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new YetAnotherMexTask().run();
        out.close();
    }

    void run() {
        int t = readInt();
        while (t-- > 0) {
            solve();
        }
    }

    void solve() {
        int n = readInt();
        int k = readInt();

        int[] freq = new int[n+1];
        for(int i = 0; i < n; i++) {
            int elem = readInt();
            freq[elem]++;
        }
        // solution
        int toDeleteCnt = n - k + 1;
        int currMax = 0;
        boolean zeroFreq = false;
        for(int i = 0; i <= n; i++) {
            if (freq[i] == 0 && !zeroFreq) {
                currMax = i;
                zeroFreq = true;
            }
            if (freq[i] > 1 && !zeroFreq) {
                int canDelete = Math.min(freq[i] - 1, toDeleteCnt);
                toDeleteCnt -= canDelete;
                freq[i] -= canDelete;
            }
            if (zeroFreq && freq[i] > 0) {
                int canDelete = Math.min(freq[i], toDeleteCnt);
                toDeleteCnt -= canDelete;
                freq[i] -= canDelete;
            }
        }
        // либо toDeleteCnt = 0 либо перебраны все n
        int ans = 0;
        if (toDeleteCnt > 0) {
            // удаляем от большего к меньшему
            for(int i = n; i >= 0; i--) {
                if (freq[i] > 0) {
                    int canDelete = Math.min(freq[i], toDeleteCnt);
                    toDeleteCnt -= canDelete;
                    freq[i] -= canDelete;
                    if (freq[i] == 0) {
                        currMax = Math.min(currMax, i);
                    }
                }
                if (toDeleteCnt == 0) break;
            }
            ans = Math.min(currMax, k-1);
        } else {
            ans = Math.min(currMax, k-1);
        }
        out.println(ans);
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