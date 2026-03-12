
import java.io.*;
import java.util.*;

public class Main {
    static int[] w;
    static int[][] dp;

    static List<Integer>[] trees;
    static Set<Integer> ts = new TreeSet<>();


    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        int N = fr.nextInt();

        w = new int[N + 1];
        dp = new int[N + 1][2];
        trees = new List[N + 1];

        for (int i = 1; i <= N; i++) {
            w[i] = fr.nextInt();
        }

        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], -1);
            trees[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            int u = fr.nextInt();
            int v = fr.nextInt();

            trees[u].add(v);
            trees[v].add(u);
        }

        dfs(0, 1);
        trace(0, 1, false);
        System.out.println(Math.max(dp[1][0], dp[1][1]));

        StringBuffer sb = new StringBuffer();
        for (Integer t : ts) {
            sb.append(t).append(" ");
        }

        System.out.println(sb.toString().trim());


    }

    private static void trace(int parent, int cur, boolean parentSelect) {

        // 부모가 선택하지 않았다면
        if (!parentSelect) {
            boolean select = dp[cur][1] >= dp[cur][0];
            if (select) {
                ts.add(cur);
            }

            for (Integer next : trees[cur]) {
                if (parent == next) continue;
                trace(cur, next, select);
            }
        } else {
            for (Integer next : trees[cur]) {
                if (parent == next) continue;

                // 부모가 선택했으므로 현재 노드는 무조건 선택 불가
                trace(cur, next, false);
            }
        }
    }

    private static void dfs(int parent, int cur) {
        dp[cur][0] = 0;
        dp[cur][1] = w[cur];


        for (int i = 0; i < trees[cur].size(); i++) {
            int next = trees[cur].get(i);
            if (next == parent) continue;

            dfs(cur, next);

            // 현재 노드를 S에 미 포함시
            dp[cur][0] += Math.max(dp[next][0], dp[next][1]);
            // 현재 노드a를 S에 포함시
            dp[cur][1] += dp[next][0];
        }
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