import java.io.*;
import java.util.*;

public class Main {
    static final int NEG = -1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] a = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) a[i][j] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N][M];

        // 0번째 행은 오른쪽으로만 갈 수 있음
        dp[0][0] = a[0][0];
        for (int j = 1; j < M; j++) {
            dp[0][j] = dp[0][j - 1] + a[0][j];
        }

        int[] left = new int[M];
        int[] right = new int[M];

        for (int i = 1; i < N; i++) {
            // 왼 -> 오 스캔: 위에서 내려오거나, 왼쪽에서 오는 경우
            left[0] = dp[i - 1][0] + a[i][0];

            for (int j = 1; j < M; j++) {
                left[j] = Math.max(dp[i - 1][j], left[j - 1]) + a[i][j];
            }

            // 오 -> 왼 스캔: 위에서 내려오거나, 오른쪽에서 오는 경우
            right[M - 1] = dp[i - 1][M - 1] + a[i][M - 1];
            for (int j = M - 2; j >= 0; j--) {
                right[j] = Math.max(dp[i - 1][j], right[j + 1]) + a[i][j];
            }

            // 두 경우 중 최댓값이 dp
            for (int j = 0; j < M; j++) {
                dp[i][j] = Math.max(left[j], right[j]);
            }
        }

        System.out.println(dp[N - 1][M - 1]);
    }
}
