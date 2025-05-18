import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.List;

public class Main {
    static final int MOD = 1_000_000_000;
    static long[] dp;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new long[1000001];
        dp[2] = 1;
        dp[3] = 2;

        for (int i = 4; i <= n; i++) {
            dp[i] = ((i-1) * (dp[i-1] + dp[i-2]) % MOD) % MOD;

        }

        System.out.println(dp[n]);
    }


}
