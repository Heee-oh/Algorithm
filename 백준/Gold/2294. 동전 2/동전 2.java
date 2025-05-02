import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static final int INF = 10000000;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken()); // 목표금액

        int[] dp = new int[10000 + 1];
        Arrays.fill(dp, INF);

        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            int value = Integer.parseInt(br.readLine());

            if (value <= 10000) {
                coins[i] = value;
                dp[coins[i]] = 1; // 동전 가치 값 자체는 1개
            }
        }

        Arrays.sort(coins);


        for (int target = 1; target <= k; target++) {
            // 각 코인들을 돌면서
            for (int coin : coins) {
                // 목표보다 코인 자체 값이 더 크다면 다음 목푯값으로
                if (target < coin) {
                    break;
                }

                dp[target] = Math.min(dp[target], dp[target - coin] + 1);
            }
        }

        System.out.println(dp[k] >= INF ? -1 : dp[k]);
    }

}