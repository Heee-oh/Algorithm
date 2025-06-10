import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    static int n, INF = Integer.MAX_VALUE;
    static int[][] dp;
    static int[] matrix;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 행렬 수

        matrix = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            matrix[i-1] = r;
            matrix[i] = c;
        }

        dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], INF);
        }

        System.out.println(solve(0, n-1));
    }

    private static int solve(int pos, int cur) {
        if (pos == cur) return 0;
        if (dp[pos][cur] != INF) return dp[pos][cur];

        for (int i = pos; i < cur; i++) {
            int value = solve(pos, i) // pos ~ i 까지 행렬 곱셉값 
                    + solve(i + 1, cur)  // i+1 ~ cur 까지 행렬 곱셈 값
                    + (matrix[pos] * matrix[i + 1] * matrix[cur + 1]); // pos * i+1 * cur+1 새로운 행렬 곱셈 값
            dp[pos][cur] = Math.min(dp[pos][cur], value);
        }

        return dp[pos][cur];
    }


}
