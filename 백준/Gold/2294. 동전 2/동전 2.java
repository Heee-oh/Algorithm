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

            // 코인 값이 최대 10만까지이기 때문에 목푯값 K 는 1만 이하 수이므로 넘어가면 굳이 저장하지 않는다. 
            if (value <= 10000) {
                coins[i] = value;
                dp[coins[i]] = 1; // 동전 가치 값 자체는 1개
            }
        }

        // 오름차순 정렬
        Arrays.sort(coins);


        // target 즉, 1~k까지 각 값에 필요한 코인 개수를 구한다. 
        for (int target = 1; target <= k; target++) {
            // 각 코인들을 돌면서
            for (int coin : coins) {
                // 목표보다 코인 자체 값이 더 크다면 다음 목푯값으로
                if (target < coin) {
                    break;
                }

                // 현재 target의 코인 개수는 현재 target 코인 개수 , (target - coin)한 값의 코인 개수 + 1(-coin 했으므로) 중 최소개수를 구한다. 
                dp[target] = Math.min(dp[target], dp[target - coin] + 1);
            }
        }

        System.out.println(dp[k] >= INF ? -1 : dp[k]);
    }

}