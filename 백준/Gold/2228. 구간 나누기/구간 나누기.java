import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] dp;
    static int[] sum;

    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dp = new int[N + 1][M + 1];
        sum = new int[N + 1];

        // 누적합을 구한다.
        for (int i = 1; i <= N; i++) {
            sum[i] = sum[i - 1] + Integer.parseInt(br.readLine());
        }

        for (int i = 0; i <= N; i++) {
            // 1~M 까지 구간에 대해여 초기값을 -무한 으로 변경
            for (int j = 1; j <= M; j++) {
                dp[i][j] = Integer.MIN_VALUE / 2;
            }
        }

        dp[1][1] = sum[1];

        // n개를 m개의 구간으로 나누기
        for (int n = 2; n <= N; n++) {
            for (int m = 1; m <= M; m++) {

                // case 1 n을 현재 구간에 미포함 하는 경우
                dp[n][m] = dp[n - 1][m];

                // m = 1일때 첫번째 구간을 얻어야한다. 따라서 k를 1~N까지 탐색하게 한다.
                int min = (m == 1) ? -1 : 0;

                // case 2 n을 현재 구간에 포함하는 경우 한칸 띄고 포함해야한다. (...n-2) n-1 (n) 이런식으로 때문에 k = n-2 부터
                for (int k = n - 2; k >= min; k--) {

                    if (k < 0) { // k가 -라는 것은 앞에 아무것도 없다는 의미 즉, 구간이 존재하지 않기에 1~n까지의 구간을 생성허요 첫번째 구간으로 잡는다.
                        dp[n][m] = Math.max(dp[n][m],sum[n]);

                    } else {
                        dp[n][m] = Math.max(dp[n][m], dp[k][m - 1] + sum[n] - sum[k + 1]);
                    }

                }


            }
        }

        System.out.println(dp[N][M]);
    }

}
