
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class WeirdAlgorithm {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new WeirdAlgorithm().run();
        out.close();
    }

    void run() {
        int n = readInt();
        StringBuilder ans = new StringBuilder();
        long cur = n;
        while(cur > 1) {
            ans.append(cur);
            ans.append(' ');

            if (cur % 2 == 0) {
                cur = cur / 2;
            } else {
                cur = cur * 3 + 1;
            }
        }
        ans.append(1);
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
