import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1] = 0;

        for (int i = 2; i < dp.length; i++) {
            int j = i;
            if (j % 6 == 0) {
                dp[i] = Math.min(Math.min(dp[j / 3] + 1, dp[j / 2] + 1), dp[i - 1] + 1);
            } else if (j % 3 == 0) {
                dp[i] = Math.min(dp[j / 3] + 1, dp[i - 1] + 1);
            } else if (j % 2 == 0) {
                dp[i] = Math.min(dp[j / 2] + 1, dp[i - 1] + 1);
            }

            dp[i] = Math.min(dp[i] , dp[i - 1] + 1);

        }

        bw.write(dp[n]+"\n");
        bw.flush();
        bw.close();
    }

}