import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());


        int[][] dp = new int[n + 1][10];

        Arrays.fill(dp[1], 1);

        for (int i = 2; i <= n; i++) {

            for (int j = 0; j < 10; j++) {
                int sum = 0;
                for (int k = j; k < 10; k++) {
                    sum += dp[i-1][k];
                    sum %= 10007;
                }

                dp[i][j] = sum;
            }
        }

        int sum  = 0;
        for (int i : dp[n]) {
            sum += i;
            sum %= 10007;

        }



        bw.write(sum + "");
        bw.flush();
        bw.close();
    }
}