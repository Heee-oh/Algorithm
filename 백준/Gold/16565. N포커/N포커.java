import java.io.*;
import java.sql.SQLOutput;
import java.util.*;

public class Main {

    static final long MOD = 10_007;
    // 계산은 long
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 4카드 이하면 이길 수 없음
        if (n < 4) {
            System.out.println(0);
            return;
        }

        int[][] dp = new int[53][53];

        dp[0][0] = 1;
        for (int i = 1; i < 53; i++) {
            dp[i][1] = i;
            dp[i][0] = dp[i][i] = 1;
            for (int j = 2; j < i; j++) {
                dp[i][j] = (int) ((dp[i - 1][j] + dp[i - 1][j - 1]) % MOD);
            }
        }

        long ans = 0;
        int max = n / 4;  // 가능한 최대 포카드 쌍 개수

        for (int i = 1; i <= max; i++) {
            // i쌍의 포카드를 뽑는 경우의 수
            long pickFourCard = dp[13][i];                   // C(13, i)
            long rest  = dp[52 - 4*i][n - 4*i];              // C(52 - 4*i, N - 4*i)
            long cnt = (pickFourCard * rest) % MOD;

            // 뽑은 포카드의 쌍의 수가 홀수일때는 더하고, 짝수일떄는 뺀다. 
            if (i % 2 == 1) {
                ans = (ans + cnt) % MOD;
            } else {
                ans = (ans - cnt + MOD) % MOD;
            }
        }

        System.out.println(ans);

    }

}
