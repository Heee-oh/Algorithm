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

        // 1번 집에 빨강, 초록, 파랑 중 하나를 칠했다고 가정했을때 최소비용을 구한다.  
        for (int k = 0; k < 3; k++) {
            // 처음 집에  k 번 색을 칠하기로 결정하고, k번 색을 칠했을때 최소비용이 나오게 하기 위해 나머지 색들은 비용을 무한으로 변경
            for (int i = 0; i < 3; i++) {
                if (i == k) dp[1][i] = houses[1][i]; // i번 색으로 칠한다면
                else dp[1][i] = INF; // 나머지 집들은 k번 색이 아니므로 무한으로 변경
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