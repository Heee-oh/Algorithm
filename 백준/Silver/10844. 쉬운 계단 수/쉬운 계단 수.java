import java.io.*;
import java.util.*;

public class Main {

    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        long[][] dp = new long[n+1][10];

        for (int i = 1; i < 10; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= n; i++) {
            // 1~8까지는 같음
            for (int j = 1; j <= 8; j++) {
                dp[i][j] = dp[i-1][j-1] + dp[i-1][j+1] % 1000000000;
            }

            // 0과 9 따로 처리
            dp[i][0] = dp[i - 1][1] % 1000000000;
            dp[i][9] = dp[i - 1][8] % 1000000000;
        }


        long sum  = 0;
        for (int i = 0; i < 10; i++) {
            sum += dp[n][i];
            sum %= 1000000000;
        }

        bw.write(sum + "");
        bw.flush();
        bw.close();
    }
}