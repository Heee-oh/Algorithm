import java.util.*;
import java.io.*;

public class Main {

    static int[][][][] dp;
    static int w, h;
    static final int MOD = 100_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        // w * h
        dp = new int[w+1][h+1][2][2];

        for (int i = 0; i <= w; i++) {

            for (int j = 0; j <= h; j++) {
                for (int k = 0; k < 2; k++) {

                    Arrays.fill(dp[i][j][k], -1);
                }
            }
        }

        System.out.println((dfs(2, 1, 0, 0) + dfs(1, 2, 1, 0)) % MOD);;


    }


    // dir == 0 ? r방향 : c방향
    private static int dfs(int r, int c, int dir, int turn) {
        if(r > w || c > h) return 0;
        if (r == w && c == h) return dp[r][c][dir][turn] = 1;

        if (dp[r][c][dir][turn] != -1) {
            return dp[r][c][dir][turn];
        }


        if (turn == 1) {
            if (dir == 0) { // r방향 이동이라면
                dp[r][c][dir][turn] = dfs(r + 1, c, dir, 0) % MOD;

            } else {
                dp[r][c][dir][turn] = dfs(r, c + 1, dir, 0) % MOD;
            }

        } else {
            if (dir == 0) {
                dp[r][c][dir][turn] = (dfs(r, c + 1, 1, 1) // 방향 전환 + 직진
                        + dfs(r + 1, c, 0, 0)) % MOD;

            }else  {
                dp[r][c][dir][turn] = (dfs(r, c + 1, 1, 0) // 직진
                        + dfs(r + 1, c, 0, 1)) % MOD; // 방향 전환
            }
        }

        return dp[r][c][dir][turn];

    }

}