import java.io.*;
import java.util.*;

public class Main {

    static class Position {
        int row, col;

        Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int N, M, C;
    static final int MOD = 1_000_007;

    static Position[] arcades; // 오락실 위치들 (1-based index)
    static boolean[][] isArcade; // 특정 위치가 오락실인지 여부
    static int[][][][] dp; // dp[r][c][k][lastArcade] = (1,1) → (r,c)까지 가며 k개의 오락실 방문하고 마지막이 lastArcade인 경우의 수

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arcades = new Position[C + 1];  // 1-based
        isArcade = new boolean[N + 1][M + 1];
        dp = new int[N + 1][M + 1][C + 1][C + 1];

        // 시작점 초기화
        dp[1][0][0][0] = 1;

        // 오락실 위치 저장
        for (int i = 1; i <= C; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            arcades[i] = new Position(r, c);
            isArcade[r][c] = true;
        }

        // 오락실을 하나도 방문하지 않는 경우
        initializeZeroArcadeDP();

        // 오락실을 h개 방문하며 마지막이 c번 오락실인 경우 계산
        for (int h = 1; h <= C; h++) {
            for (int c = h; c <= C; c++) {
                computeDPWithArcadeVisit(h, c);
            }
        }

        // 정답 출력: 방문한 오락실 수 k에 대한 경로 수
        for (int k = 0; k <= C; k++) {
            int total = 0;
            for (int last = k; last <= C; last++) {
                total = (total + dp[N][M][k][last]) % MOD;
            }
            bw.write(total + " ");
        }
        
        bw.flush();
        bw.close();

    }

    // 오락실을 하나도 방문하지 않고 단순 이동만 하는 경우의 DP 초기화
    static void initializeZeroArcadeDP() {
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= M; c++) {
                if (isArcade[r][c]) continue;
                dp[r][c][0][0] = (dp[r - 1][c][0][0] + dp[r][c - 1][0][0]) % MOD;
            }
        }
    }

    // 오락실을 h개 방문하고 마지막이 c번 오락실인 경우의 수 계산
    static void computeDPWithArcadeVisit(int h, int cIndex) {
        Position cur = arcades[cIndex];
        int r = cur.row;
        int c = cur.col;

        // 이전 오락실들로부터 현재 오락실에 도달하는 경우
        for (int prev = h - 1; prev < cIndex; prev++) {
            dp[r][c][h][cIndex] = (dp[r][c][h][cIndex]
                    + dp[r - 1][c][h - 1][prev]
                    + dp[r][c - 1][h - 1][prev]) % MOD;
        }

        // 이후 (r, c)부터 도착지까지 일반 경로 확장
        for (int i = r; i <= N; i++) {
            for (int j = c; j <= M; j++) {
                if (isArcade[i][j]) continue;
                if (i == r && j == c) continue; // 오락실 위치는 이미 위에서 설정됨

                dp[i][j][h][cIndex] = (dp[i - 1][j][h][cIndex] + dp[i][j - 1][h][cIndex]) % MOD;
            }
        }
    }
}
