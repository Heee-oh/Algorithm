import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    static final int MOD = 1_000_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[1000001];

        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            if (i % 2 != 0) {
                dp[i] = dp[i - 1];
            } else {
                dp[i] = (dp[i - 1] + dp[i / 2]) % MOD;
            }
        }

        System.out.println(dp[n]);
    }




}
