import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    static final int MOD = 9901;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[100001];

        dp[1] = 3;
        dp[2] = 7;

        // dp[n] = dp[n-2] + dp[n-1] * 2
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 2] + (dp[i - 1] * 2) % MOD) % MOD;
        }

        System.out.println(dp[n]);

    }

}