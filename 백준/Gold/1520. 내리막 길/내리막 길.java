import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] dp;
    static int[][] map;


    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    static boolean[][] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new int[N][M];
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        dp[N-1][M-1] = 1;

        visited[0][0] = true;


        System.out.println(dfs(0, 0));
    }


    private static int dfs(int r, int c) {

        if (dp[r][c] !=-1) {
            return dp[r][c];
        }

        dp[r][c] = 0;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr < 0 || nr >= N
                    || nc < 0 || nc >= M
                    || map[r][c] <= map[nr][nc]
                    || visited[nr][nc]) continue;

            visited[nr][nc] = true;
            dp[r][c] += dfs(nr, nc);
            visited[nr][nc] = false;
        }

        return dp[r][c];
    }

}