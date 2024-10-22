// package com.mycompany.codeforces.div2round980;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TaskC {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new TaskC().run();
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

typedef pair<int,int> pii;

const int N=2e5+10;
int n,id[N],T,fg[N];pii a[N];

inline bool cmp(int x,int y){return a[x]<a[y];}

signed main(){
	cin>>T;
	while(T--){
		cin>>n;
		for(int i=1;i<=n;++i){
			id[i]=i;cin>>a[i].first>>a[i].second;
			if(a[i].first>a[i].second){swap(a[i].first,a[i].second);fg[i]=1;}
		}
		sort(id+1,id+1+n,cmp);
		for(int i=1;i<=n;++i) if(fg[i]) swap(a[i].first,a[i].second);
		for(int i=1;i<=n;++i) fg[i]=0;
		for(int i=1;i<=n;++i) cout<<a[id[i]].first<<' '<<a[id[i]].second<<' ';
		cout<<endl;
	}
	return 0;
}

     */

    void solve() {
        int n = readInt();
        Pair[] arr = new Pair[n];
        for(int i = 0; i < n; i++) {
            Pair p = new Pair();
            p.x = readInt();
            p.y = readInt();
            arr[i] = p;
        }
        Arrays.sort(arr);

        StringBuilder ans = new StringBuilder("");
        for(int i = 0; i < n; i++) {
            ans.append(arr[i].x);
            ans.append(' ');
            ans.append(arr[i].y);
            if (i < n-1) ans.append(' ');
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

    public static class Pair implements Comparable<Pair> {
        public int x;
        public int y;

        public Pair() {
        }


        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Pair that) {
            int a1 = Math.min(this.x, this.y);
            int a2 = Math.max(this.x, this.y);

            int b1 = Math.min(that.x, that.y);
            int b2 = Math.max(that.x, that.y);

            if (a1 == b1) {
                return Integer.compare(a2, b2);
            }
            return Integer.compare(a1, b1);
        }

        int oneIfMore(int a, int b) {
            return (a > b) ? 1 : 0;
        }
    }
}