import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] dp;
    static int[][] arr;

    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N][N];

        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Arrays.fill(dp[i], -1);
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dfs(i, j);
            }
        }


        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < 4; k++) {
                    max = Math.max(max, dp[i][j]);
                }
            }
        }

        System.out.println(max);


    }

    private static int dfs(int r, int c) {
        if (dp[r][c] != -1) {
            return dp[r][c];
        }

        dp[r][c] = 1;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr < 0 || nr >= N || nc < 0 || nc >= N
                    || arr[nr][nc] >= arr[r][c]) {
                continue;
            }

            dp[r][c] = Math.max(dp[r][c], dfs(nr, nc) + 1);
        }

        return dp[r][c];

    }
}
