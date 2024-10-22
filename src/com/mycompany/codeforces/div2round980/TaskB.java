// package com.mycompany.codeforces.div2round980;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TaskB {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new TaskB().run();
        out.close();
    }

    void run() {
        int t = readInt();
        while (t > 0) {
            solve();
            t--;
        }
    }

    /*

    Эквивалентное но более лаконичное решение от liyujiangwx

    const int N=2e5+10;
int n,k,a[N],T,cnt,sum[N];

signed main(){
	cin>>T;
	while(T--){
		cin>>n>>k;cnt=k;
		for(int i=1;i<=n;++i) cin>>a[i];
		sort(a+1,a+1+n);
		for(int i=1;i<=n;++i){
			sum[i]=sum[i-1]+a[i];
			int tmp=(a[i]-a[i-1])*(n-i+1);
//			cout<<i<<' '<<tmp<<endl;
			if(i!=1) ++cnt;
			k-=tmp;
			if(k<=0) break;
		}
		cout<<cnt<<endl;
	}
	return 0;
}
     */

    void solve() {
        int n = readInt();
        long k = readInt();
        int[] arr = new int[n];
        long aSum = 0;
        for(int i = 0; i < n; i++) {
            arr[i] = readInt();
            aSum += arr[i];
        }
        // solution
        Arrays.sort(arr);
        int pos = 0;
        long ans = 0, rest = n, prevMin = 0;
        while(pos < n) {
            long currMin = arr[pos];
            int cnt = 1;
            while((pos + 1) < n && arr[pos+1] == currMin) {
                pos++;
                cnt++;
            }
            // calculation
            long toClick = rest * (currMin - prevMin);
            if (k <= toClick) {
                ans += k;
                break;
            }
            ans += toClick + cnt;
            rest -= cnt;
            prevMin = currMin;
            k -= toClick;

            pos++;
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