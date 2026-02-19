import java.io.*;
import java.util.*;

public class Main {

    static List<Integer>[] trees;
    static int[][] dp;
    static int[][] cost;
    static int[][] path;
    static int[] max;
    static int[] ans;
    static char[] colors = {'R', 'G', 'B'};

    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        StringBuilder sb = new StringBuilder();
        int N = fr.nextInt();

        trees = new List[N + 1];
        dp = new int[4][N + 1];
        cost = new int[3][N + 1];
        path = new int[4][N + 1];
        max = new int[N + 1];
        ans = new int[N + 1];

        for (int i = 0; i < 4; i++) {
            Arrays.fill(dp[i], -1);
        }

        for (int i = 0; i <= N; i++) {
            trees[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            int u = fr.nextInt();
            int v = fr.nextInt();
            trees[u].add(v);
            trees[v].add(u);
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < 3; j++) {
                cost[j][i] = fr.nextInt();
            }
        }

        dfs(3, 1, -1);
        sb.append(dp[3][1]).append("\n");


        recon(3, 1, -1);

        for (int i = 1; i <= N; i++) {
            sb.append(colors[ans[i]]);
        }
        System.out.print(sb.toString().trim());

    }


    private static void recon(int prevColor, int cur, int parent) {
        int myColor = path[prevColor][cur];
        ans[cur] = myColor;

        for (int i = 0; i < trees[cur].size(); i++) {
            int next = trees[cur].get(i);
            if (next == parent) continue;
            recon(myColor, next, cur);
        }
    }
    private static int dfs(int prevColor, int cur, int parent) {
        if (dp[prevColor][cur] != -1) {
            return dp[prevColor][cur];
        }

        // 1. 색 고르기
        for (int c = 0; c < 3; c++) {
            if (prevColor == c) continue;
            int nextCost = cost[c][cur];

            // 2. 다음 자식 트리로 이동
            for (int j = 0; j < trees[cur].size(); j++) {
                int next = trees[cur].get(j);
                if (parent == next) continue;

                int dfs = dfs(c, next, cur);
                nextCost += dfs;
            }

            // 3. 자식들의 모든 합을 더했을 때 비교
            if (dp[prevColor][cur] < nextCost) {
                dp[prevColor][cur] = nextCost;
                path[prevColor][cur] = c;
                // 이부분에 path를 둬서 역추적이 힘들었음
            }

        }


        return dp[prevColor][cur];
    }

    static class FastReader {
        private final StringBuilder sb = new StringBuilder();
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


        String next() throws IOException {
            sb.delete(0, sb.length());
            int c = read();
            while (c != '\n') {
                sb.append((char) c);
                c = read();
            }

            return sb.toString();
        }

        int nextInt() throws IOException {
            int c = read();
            while (c <= 32) c = read(); // 특수문자 공백등 무시
            int sign = 1;
            if (c == '-') {sign = -1; c = read();}
            int val = 0;

            while (c > 32) { // 숫자면 계속 읽음
                val = val * 10 + (c - '0');
                c = read();
            }

            return val * sign;
        }
    }


}
