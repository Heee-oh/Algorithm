import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

public class Main {
    static final int MOD = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long[] dp = new long[10001];
        dp[2] = 1;
        dp[4] = 2;

        for (int i = 6; i <= n; i += 2) {

            dp[i] = (dp[i - 2] * 2) % MOD;
            for (int j = i - 4; j >= 2; j -= 2) {
                dp[i] = (dp[i] + dp[j] * dp[i - j - 2]) % MOD;
            }
        }

        System.out.println(dp[n]);
    }

    // 각각 하나씩 유전자를 빼보면서 최장길이를 찾음



}
