import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int X;
    static int Y;
    static int[][] coordinates;
    static long[][] dp;
    static int[] dx = {0, 0, 1, 0, -1};
    static int[] dy = {0, 1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        coordinates = new int[n + 1][2];
        dp = new long[n + 1][5]; // 정 위치, -1 칸 위치 (x , y 각각에 대하여) 총 3가지

        StringTokenizer st = null;
        for (int i = 0; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            coordinates[i][0] = x;
            coordinates[i][1] = y;
        }

        X = coordinates[0][0];
        Y = coordinates[0][1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }

//        System.out.println(dfs(0, 0, new int[] {coordinates[0][0], coordinates[0][1]}));
        System.out.println(dfs(0, 0));
    }

    // 0 : x, 1 : y
    private static long dfs(int idx, int type, int[] curPos) {
        if (idx > n) return 0;
        if (dp[idx][type] != -1) return dp[idx][type];

        dp[idx][type] = Long.MAX_VALUE / 2;

        for (int i = 0; i < 5; i++) {
            int movedDistance = Math.abs(coordinates[idx][0] - curPos[0]+ dx[i])
                    + Math.abs(coordinates[idx][1] - curPos[1]+ dy[i]);

            // 다음 위치를 생성
            int[] nextCur = new int[]{coordinates[idx][0] + dx[i], coordinates[idx][1] + dy[i]};

            dp[idx][type] = Math.min(dp[idx][type], movedDistance + dfs(idx + 1, i, nextCur));
        }

        return dp[idx][type];
    }

    // 0 : x, 1 : y
    private static long dfs(int idx, int type) {
        if (idx > n) return 0;
        if (dp[idx][type] != -1) return dp[idx][type];

        int pre_X, pre_Y;
        if (idx == 0) {
            pre_X = X;
            pre_Y = Y;
        } else {
            pre_X = coordinates[idx - 1][0] + dx[type];
            pre_Y = coordinates[idx - 1][1] + dy[type];
        }

        dp[idx][type] = Long.MAX_VALUE / 2;

        for (int i = 0; i < 5; i++) {
            int movedDistance = Math.abs(coordinates[idx][0] - pre_X+ dx[i])
                    + Math.abs(coordinates[idx][1] - pre_Y+ dy[i]);

            // 다음 위치를 생성
            dp[idx][type] = Math.min(dp[idx][type], movedDistance + dfs(idx + 1, i));
        }

        return dp[idx][type];
    }

}
