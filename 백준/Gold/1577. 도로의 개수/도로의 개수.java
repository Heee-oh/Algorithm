import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static long[][] dp;

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static boolean[][][] block;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());

        dp = new long[M + 1][N + 1];
        block = new boolean[M + 1][N + 1][2];

        for (int i = 0; i <= M; i++) {
            Arrays.fill(dp[i], -1);
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            createBlock(c1, r1, c2, r2);
        }

        dp[M][N] = 1;


        System.out.println(dfs(0, 0));

    }

    private static long dfs(int r, int c) {
        if (r < 0 || r > M
                || c < 0 || c > N) {
            return 0;
        }

        if (dp[r][c] != -1) {
            return dp[r][c];
        }

        long res = 0;
        if (!block[r][c][0]) res += dfs(r, c + 1);
        if (!block[r][c][1]) res += dfs(r + 1, c);

        return dp[r][c] = res;

    }


    // 0 ->, 1 : 아래
    private static void createBlock(int r1, int c1, int r2, int c2) {
        if (r1 < r2) {
            block[r1][c1][1] = true;

        } else if (r1 > r2) {
            block[r2][c2][1] = true;

        } else if (c1 < c2) {
            block[r1][c1][0] = true;

        } else if (c1 > c2) {
            block[r2][c2][0] = true;
        }
    }
}