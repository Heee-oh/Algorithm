
import java.io.*;
import java.util.*;

public class Main {


    static int[] w;
    static List<Integer>[] trees;
    static int[] parents;
    static int[][] dp;

    static Set<Integer> set = new TreeSet<>();

    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        int N = fr.nextInt();
        trees = new List[N + 1];
        w = new  int[N + 1];

        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            w[i] = fr.nextInt();
            trees[i] = new ArrayList<>();
        }

        dp = new int[N + 1][2];

        for (int i = 0; i < N - 1; i++) {
            int u = fr.nextInt();
            int v = fr.nextInt();

            trees[u].add(v);
            trees[v].add(u);
        }

        trees[0] = new ArrayList<>();
        trees[0].add(1);

        for (int i = 0; i <= N; i++) {
            dp[i][0] = dp[i][1] = -1;
        }


        System.out.println(dfs(1, 0));

        trace(1, 0, false);

        StringBuffer sb = new StringBuffer();
        for (Integer i : set) {
            sb.append(i).append(" ");
        }

        System.out.println(sb.toString().trim());



    }

    private static void trace(int cur, int parent, boolean parentSelect) {

        if (parentSelect) {
            for (Integer next : trees[cur]) {
                if (next == parent) continue;

                trace(next, cur, false);
            }
        } else {
            boolean select = dp[cur][1] >= dp[cur][0];
            if (select) {
                set.add(cur);
            }

            for (Integer next : trees[cur]) {
                if (next == parent) continue;
                trace(next, cur, select);

            }
        }
    }

    private static int dfs(int n, int parent) {
        dp[n][0] = 0;
        dp[n][1] = w[n];


        for (int i = 0; i < trees[n].size(); i++) {
            int nextNode = trees[n].get(i);
            if (nextNode == parent) continue;
            dfs(nextNode, n);

            dp[n][0] += Math.max(dp[nextNode][0], dp[nextNode][1]);
            dp[n][1] += dp[nextNode][0];
        }

        return Math.max(dp[n][0], dp[n][1]);
    }

    static class FastReader {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' ');

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }

            int val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }
    }
}