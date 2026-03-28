import java.io.*;
import java.util.*;


public class Main {

    static class Node {
        int num;
        int cnt;

        public Node(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }

    // 각 부품의 필요 개수
    static int[][] dp;
    static List<Node>[] graph;
    static int N, M;

    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        StringBuilder sb = new StringBuilder();
        N = fr.nextInt();
        M = fr.nextInt();

        dp = new int[N + 1][N + 1];

        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dp[i], -1);
            graph[i] = new ArrayList<>();
        }

        int min = 1000; // 중간 부품 경계 찾기
        for (int i = 0; i < M; i++) {

            int x = fr.nextInt();
            int y = fr.nextInt();
            int k = fr.nextInt();

            graph[x].add(new Node(y, k));
        }

        for (int i = 1; i <= N; i++) {
            if (!graph[i].isEmpty()) continue;

            dp[i] = new int[N + 1];
            dp[i][i] = 1;
        }

        dfs(N);


        for (int i = 1; i <= N; i++) {
            if (!graph[i].isEmpty()) continue; // 중간부품은 출력 X

            sb.append(i).append(" ").append(dp[N][i]).append("\n");
        }

        System.out.println(sb.toString().trim());
    }

    private static int[] dfs(int x) {

        if (dp[x][x] != -1) {
            return dp[x];
        }

        // 중간 부품이 아님
        if (graph[x].isEmpty()) {
            return new int[N + 1];
        }


        int[] ans = new int[N + 1];
        for (Node need : graph[x]) {

            int[] rs = dfs(need.num);

            for (int i = 0; i <= N; i++) {
                ans[i] += rs[i] * need.cnt;
            }
        }


        for (int i = 0; i <= N; i++) {
            dp[x][i] = ans[i];
        }

        return dp[x];
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