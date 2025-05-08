import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];

        boolean[] isVipSeat = new boolean[n + 1];
        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            int seatNum = Integer.parseInt(br.readLine());
            isVipSeat[seatNum] = true;
        }

        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            if (isVipSeat[i] || isVipSeat[i-1]) {
                dp[i] = dp[i - 1];
            } else {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
        }

        System.out.print(dp[n]);

    }
}
