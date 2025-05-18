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

        // 1번의 선물을 임의의 k번에게 주었을떄

        // k번도 1번에게 준다. (서로교환), k와 1번은 이미 가졌으므로 이때 남은 경우의 수는 n-2가지
        // 1을 k번이라 취급하면 나머지 n-1 가지가 있다.

        // dp[i] = (i-1) * dp[i-1] + dp[i-2]
        for (int i = 4; i <= n; i++) {
            dp[i] = ((i-1) * (dp[i-1] + dp[i-2]) % MOD) % MOD;

        }

        System.out.println(dp[n]);
    }


}
