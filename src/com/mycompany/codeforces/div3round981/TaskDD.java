//package com.mycompany.codeforces.div3round981;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class TaskDD {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new TaskDD().run();
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
        Лаконичное решение на С++ от __illuminati__

        void solve(ll tc)
            {
                ll n;
                cin >> n;

                vi a(n);
                in(a);

                ll cur = 0;
                ll count = 0;
                map<ll,ll> mp;
                mp[0] = 1;
                f(i,0,n)
                {
                    cur += a[i];
                    if(mp[cur])
                    {
                        cur = 0;
                        count++;
                        mp.clear();
                        mp[0] = 1;
                    }
                    else
                    {
                        mp[cur]++;
                    }
                }

                cout << count << endl;
            }

            Похожее но через множество taffynya

            int solve() {
                    int n; cin >> n;
                    vector<int> a(n);
                    for (auto &i : a) cin >> i;
                    int ans = 0, i = 0;
                    set<ll> s;
                    ll sum = 0;
                    s.insert(0);
                    while (i < n) {
                        sum += a[i];
                        if (s.count(sum)) {
                            ans++;
                            s.clear();
                            s.insert(0);
                            sum = 0;
                        }
                        s.insert(sum);
                        i++;
                    }
                    return ans;
               }

     */

    void solve() {
        int n = readInt();
        int[] arr = new int[n];
        int[] pref = new int[n+1];
        for(int i = 0; i < n; i++) {
            arr[i] = readInt();
            pref[i+1] = pref[i] + arr[i];
        }

        Set<Integer> hs = new HashSet<>();
        hs.add(0);
        int ans = 0;
        for(int i = 0; i < n; i++) {
            if (hs.contains(pref[i+1])) {
                ans++;
                hs.clear();
            } else {
                hs.add(pref[i+1]);
            }
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
