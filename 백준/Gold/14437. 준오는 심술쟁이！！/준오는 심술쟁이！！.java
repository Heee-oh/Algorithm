import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    // 문자는 k= 0(안바뀜) 부터 k= 25까지 변경 가능함
    // 이를 각각 고려했을때 각 합(j = 0~S)을 만들 수 있는 경우의 수를 구함
    // i번 문자에서 k를 사용하면 나머지 i-1개에서 (j-k)를 만듦
    // k가 0~25까지 변경 가능하니 pSum(j) = dp[i][j - 0] + dp[i][j-1] + dp[i][j-2]... + dp[i][j-25(kMax)]
    // 이를 3중 for문으로 처리시 시간초과발생

    // pSum(j+1) = dp[i][j+1] + dp[i][j] + dp[i][j-1]...dp[i][j-24]
    // 두 식의 차이가 dp[i][j+1]이 더해지고 dp[i][j-25]가 빠짐

    // 따라서 슬아이딩 윈도우로 현재 dp[i-1][j]를 더하고 dp[i-1][j-26]을 뺌
    static final int MOD = 1_000_000_007;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int s = Integer.parseInt(br.readLine().trim());
        String str = br.readLine().trim();
        int n = str.length();

        int[] prevDp = new int[s + 1];
        prevDp[0] = 1;

        for (int i = 0; i < n; i++) {
            int[] dp = new int[s + 1];

            long pSum = 0; // prevDp[j] +  ~ +  prevDp[j-25]
            for (int j = 0; j <= s; j++) {
                pSum += prevDp[j];
                if (j - 26 >= 0) pSum -= prevDp[j - 26];

                pSum = pSum % MOD;
                if (pSum < 0) pSum += MOD;

                dp[j] = (int)pSum;
            }
            prevDp = dp;
        }

        System.out.println(prevDp[s]);


    }
}
