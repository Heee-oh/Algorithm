import java.io.*;
import java.util.*;

public class Main {
    // 32406번 의좋은 형제

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][][] dp = new int[N][2][2]; // N개, 두 형제, 최대 최소
        int[][] arr = new int[N][2];

        for (int i = 0; i < 2; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[j][i] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0][0] = dp[0][0][1] = arr[0][0];
        dp[0][1][0] = dp[0][1][1] = arr[0][1];

        // N개의 볏단에 대하여
        for (int i = 1; i < N - 1; i++) {
            // 두 형제에 대하여
            for (int j = 0; j < 2; j++) {
                int min1 = Math.min(dp[i - 1][0][0], dp[i - 1][0][1]);
                int min2 = Math.min(dp[i - 1][1][0], dp[i - 1][1][1]);

                int max1 = Math.max(dp[i - 1][0][0], dp[i - 1][0][1]);
                int max2 = Math.max(dp[i - 1][1][0], dp[i - 1][1][1]);

                dp[i][j][0] = Math.min(min1, min2) + arr[i][j];
                dp[i][j][1] = Math.max(max1, max2) + arr[i][j];
            }
        }

        int min1 = Math.min(dp[N - 2][1][0], dp[N - 2][1][1]) + arr[N-1][0];
        int max1 = Math.max(dp[N - 2][1][0], dp[N - 2][1][1]) + arr[N-1][0];

        int min2 = Math.min(dp[N - 2][0][0], dp[N - 2][0][1]) + arr[N-1][1];
        int max2 = Math.max(dp[N - 2][0][0], dp[N - 2][0][1]) + arr[N-1][1];


        int result = Math.max(max1, max2) - Math.min(min2, min1);

        System.out.println(result);
    }


}
