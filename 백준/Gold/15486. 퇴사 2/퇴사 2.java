import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    static final int MOD = 1_000_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int n = Integer.parseInt(br.readLine());

        int[] T = new int[n + 1];
        int[] P = new int[n + 1];
        int[] dp = new int[n + 2];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <=n; i++) {
            if (i + T[i] <= n + 1) {
                dp[i + T[i]] = Math.max(dp[i + T[i]],Math.max(dp[i] + P[i], dp[i - 1] + P[i]));
            }
            dp[i] = Math.max(dp[i], dp[i - 1]);
        }

        dp[n + 1] = Math.max(dp[n], dp[n + 1]);

        System.out.println(dp[n + 1]);
    }

}
