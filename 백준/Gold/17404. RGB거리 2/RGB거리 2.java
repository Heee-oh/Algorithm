import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static final int INF = 10000000;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[][] houses = new int[n + 1][3];
        int[][] dp = new int[n + 1][3];

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            houses[i][0] = Integer.parseInt(st.nextToken());
            houses[i][1] = Integer.parseInt(st.nextToken());
            houses[i][2] = Integer.parseInt(st.nextToken());
        }

        int min = Integer.MAX_VALUE;

        for (int k = 0; k < 3; k++) {

            for (int i = 0; i < 3; i++) {
                if (i == k) dp[1][i] = houses[1][i]; // i번 색으로 칠한다면
                else dp[1][i] = INF;
            }

            for (int i = 2; i <= n; i++) {
                dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + houses[i][0];
                dp[i][1] = Math.min(dp[i - 1][2], dp[i - 1][0]) + houses[i][1];
                dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + houses[i][2];
            }

            // 처음 집을 k 색으로 칠했으니 k색을 고르는 경우를 제외한 n번째까지의 비용 중 최솟값을 얻는다.
            for (int i = 0; i < 3; i++) {
                if (k != i) min = Math.min(dp[n][i], min);
            }

        }

        System.out.println(min);
    }

}