import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    static final int MOD = 1000000;
    static int[] dp;
    static String code;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        code = br.readLine();
        int n = code.length();

        if (code.charAt(0) == '0') {
            System.out.println(0);
            return;
        }

        dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            int first = code.charAt(i - 1) - '0';
            int second = code.charAt(i - 2) - '0';
            int num = second * 10 + first;

            if (first != 0) {
                dp[i] += dp[i - 1];
            }

            if (10 <= num && num <= 26) {
                dp[i] += dp[i - 2];
                dp[i] %= MOD;
            }
        }

        System.out.println(dp[n]);
    }


}
