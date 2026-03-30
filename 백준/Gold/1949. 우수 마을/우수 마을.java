import java.io.*;
import java.util.*;


public class Main {


    static int[][] dp;
    static int[] cnt;

    static int N;
    static List<Integer>[] trees;

    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        StringBuilder sb = new StringBuilder();
        N = fr.nextInt();

        trees = new ArrayList[N+1];
        dp = new int[N + 1][2];
        cnt = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            cnt[i] = fr.nextInt();
            trees[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {

            int u = fr.nextInt();
            int v = fr.nextInt();
            trees[u].add(v);
            trees[v].add(u);
        }

        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], -1);

        }

        System.out.println(Math.max(dfs(1, 0, 0), dfs(1, 0, 1)));


        System.out.println(sb.toString().trim());
    }


    private static int dfs(int cur, int parent, int pSelect) {

        if (dp[cur][pSelect] != -1) {
            return dp[cur][pSelect];
        }

        dp[cur][pSelect] = 0;
        int tmp = pSelect == 0 ? 0 : cnt[cur];
        for (int next : trees[cur]) {
            if (parent == next) continue;

            if (pSelect == 0) {
                tmp += Math.max(dfs(next, cur, 0), dfs(next, cur, 1)  );
            } else {
                tmp += dfs(next, cur, 0);

            }

        }

        return dp[cur][pSelect] = tmp;
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

        long nextLong() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' ');

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }

            long val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }
    }
}