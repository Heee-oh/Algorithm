import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][][] dp;
    static int[][] arr;

    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N][N][4];

        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                Arrays.fill(dp[i][j], -1);
            }
        }


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < 4; k++) {
                    dfs(i, j, k);

                }
            }
        }


        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < 4; k++) {
                    max = Math.max(max, dp[i][j][k]);
                }
            }
        }

        System.out.println(max);


    }

    private static int dfs(int r, int c, int d) {
        if (dp[r][c][d] != -1) {
            return dp[r][c][d];
        }

        dp[r][c][d] = 1;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr < 0 || nr >= N || nc < 0 || nc >= N
                    || arr[nr][nc] >= arr[r][c]) {
                continue;
            }

            dp[r][c][d] = Math.max(dp[r][c][d], dfs(nr, nc, i) + 1);
        }

        return dp[r][c][d];

    }
}
