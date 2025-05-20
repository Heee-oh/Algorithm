import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    static final int MOD = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long[] dp = new long[10001];
        dp[2] = 1;
        dp[4] = 2;

        for (int i = 6; i <= n; i += 2) {
            // 1번 사람이 왼쪽사람과 악수하는 경우 (dp[i-2])
            // 1번 사람이 오른쪽 사람과 악수하는 경우 (dp[i-2])
            dp[i] = (dp[i - 2] * 2) % MOD;

            // 1번 사람이 나머지 팔이 교차하지 않는 위치에 있는 사람과 악수하는 경우의 수
            for (int j = i - 4; j >= 2; j -= 2) {
                dp[i] = (dp[i] + dp[j] * dp[i - j - 2]) % MOD;
            }
        }

        System.out.println(dp[n]);
    }
}
