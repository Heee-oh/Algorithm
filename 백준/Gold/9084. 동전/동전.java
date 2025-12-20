import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int coinCnt = Integer.parseInt(br.readLine());
            int[] coin = new int[coinCnt];

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < coinCnt; i++) {
                coin[i] = Integer.parseInt(st.nextToken());
            }

            int M = Integer.parseInt(br.readLine());
            int[] dp = new int[M + 1];
            dp[0] = 1;

            for (int i = 0; i < coinCnt; i++) {
                for (int c = coin[i]; c <= M; c ++) {
                    dp[c] = dp[c] + dp[c - coin[i]];
                }
            }

            System.out.println(dp[M]);

        }


    }

}