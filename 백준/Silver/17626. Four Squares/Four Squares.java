import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            int sqrt = (int) Math.sqrt(i);

            if (sqrt * sqrt == i) {
                dp[i] = 1;

            } else {
                dp[i] = Integer.MAX_VALUE;
                for (int j = 1; j <= Math.sqrt(i); j++) {
                    dp[i] = Math.min(dp[i - (j * j)] + 1, dp[i]);
                }
            }
        }

        System.out.println(dp[n]);
    }

}
