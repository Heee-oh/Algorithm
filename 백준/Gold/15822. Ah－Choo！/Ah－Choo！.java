import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] X = new int[N + 1];
        int[] Y = new int[N + 1];

        StringTokenizer stX = new StringTokenizer(br.readLine());
        StringTokenizer stY = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) X[i] = Integer.parseInt(stX.nextToken());
        for (int i = 1; i <= N; i++) Y[i] = Integer.parseInt(stY.nextToken());

        // DP 테이블 초기화 (최대값으로 채우기)
        int[][] dp = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        // 시작점 설정: 첫 번째 원소끼리는 반드시 매칭되어야 함
        dp[1][1] = (X[1] - Y[1]) * (X[1] - Y[1]);

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                // 시작점은 이미 계산했으므로 건너뜀
                if (i == 1 && j == 1) continue;

                int diff = (X[i] - Y[j]) * (X[i] - Y[j]);

                // 세 가지 경로 중 최소값 선택
                int minPrev = dp[i - 1][j]; // 위에서 옴
                minPrev = Math.min(minPrev, dp[i][j - 1]); // 왼쪽에서 옴
                minPrev = Math.min(minPrev, dp[i - 1][j - 1]); // 대각선에서 옴

                if (minPrev != Integer.MAX_VALUE) {
                    dp[i][j] = diff + minPrev;
                }
            }
        }

        // 결과는 두 수열의 끝까지 모두 매칭했을 때의 값
        System.out.println(dp[N][N]);
    }
}