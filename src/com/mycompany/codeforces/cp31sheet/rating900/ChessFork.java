package com.mycompany.codeforces.cp31sheet.rating900;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/*
   Super clean solution from winner shenzihan (without sets and intersections, just counting)

       int main() {
        ios::sync_with_stdio(0);
        cin.tie(0);
        cin >> T;
        while (T--) {
            int a, b, x, y, z, w;
            cin >> a >> b >> x >> y >> z >> w;
            const int dx[8] = {a, a, -a, -a, b, b, -b, -b};
            const int dy[8] = {b, -b, b, -b, a, -a, a, -a};
            int ans = 0;
            for (int i = 0; i < (a == b ? 4 : 8); ++i) {
                int px = x + dx[i], py = y + dy[i];
                int tx = abs(px - z), ty = abs(py - w);
                if ((tx == a && ty == b) || (tx == b && ty == a)) ++ans;
            }
            cout << ans << '\n';
        }
        return 0;
    }


   There are at most 8 positions of the knight that can attack a single cell.
   Therefore, we can find all 8 positions that attack the king and the 8 positions that attack the queen
   and count the number of positions that appear in both of these lists.

    clean solution in C++
    Вместо перебора 8 пар ходов они перебирают только 4 направления
    Так как например для направления вправо и вверх пары ходов (0, -1)+(1, 0) и
    (1, 0) + (0, -1) перемещают в одну и ту же точку, которая задается сдвигами (1, -1)

        #include <bits/stdc++.h>
        using namespace std;

        int dx[4] = {-1, 1, -1, 1}, dy[4] = {-1, -1, 1, 1};

        int main(){
            int t; cin >> t;
            for(int i = 0; i < t; i++){
                int a, b; cin >> a >> b;
                int x1, y1, x2, y2; cin >> x1 >> y1 >> x2 >> y2;
                set<pair<int, int>> st1, st2;
                for(int j = 0; j < 4; j++){
                    st1.insert({x1+dx[j]*a, y1+dy[j]*b});
                    st2.insert({x2+dx[j]*a, y2+dy[j]*b});
                    st1.insert({x1+dx[j]*b, y1+dy[j]*a});
                    st2.insert({x2+dx[j]*b, y2+dy[j]*a});
                }
                int ans = 0;
                for(auto x : st1)
                    if(st2.find(x) != st2.end())
                        ans++;
                cout << ans << '\n';
            }
        }
 */

public class ChessFork {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new ChessFork().run();
        out.close();
    }

    void run() {
        int t = readInt();
        while (t-- > 0) {
            solve();
        }
    }

    void solve() {
        int a = readInt();
        int b = readInt();
        Point king = new Point();
        king.x = readInt();
        king.y = readInt();
        Point queen = new Point();
        queen.x = readInt();
        queen.y = readInt();
        // solution
        Point[][] knightMoves = new Point[8][2];
        List<Point> firstMoves = Arrays.asList(new Point(1, 0), new Point(-1, 0),
                new Point(0, -1), new Point(0, 1));
        int i = 0;
        for(Point first: firstMoves) {
            Point second1 = nextPoint(first, -1);
            Point second2 = nextPoint(first, 1);
            knightMoves[i][0] = first;
            knightMoves[i][1] = second1;
            knightMoves[i+1][0] = first;
            knightMoves[i+1][1] = second2;
            i += 2;
        }
        Set<Point> fromKing = getKnightPositions(king, knightMoves, a, b);
        Set<Point> fromQueen = getKnightPositions(queen, knightMoves, a, b);
        fromKing.retainAll(fromQueen);
        int ans = fromKing.size();
        out.println(ans);
    }

    private Point nextPoint(Point p, int next) {
        int nextX = p.x != 0 ? 0 : next;
        int nextY = p.y != 0 ? 0 : next;
        return new Point(nextX, nextY);
    }

    private Set<Point> getKnightPositions(Point p, Point[][] knightMoves, int a, int b) {
        Set<Point> resultSet = new HashSet<>();
        int x, y;
        for(Point[] moves: knightMoves) {
            Point first = moves[0], second = moves[1];
            // a, b
            x = p.x + first.x*a + second.x*b;
            y = p.y + first.y*a + second.y*b;
            resultSet.add(new Point(x, y));
            // b, a
            x = p.x + first.x*b + second.x*a;
            y = p.y + first.y*b + second.y*a;
            resultSet.add(new Point(x, y));
        }
        return resultSet;
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

    private static class Point {
        int x;
        int y;

        public Point() {
            this(0, 0);
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}