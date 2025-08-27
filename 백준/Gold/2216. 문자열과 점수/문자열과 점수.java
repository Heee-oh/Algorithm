import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken()); // match reward (>0)
        int B = Integer.parseInt(st.nextToken()); // gap penalty (<0)
        int C = Integer.parseInt(st.nextToken()); // mismatch penalty (<0)

        char[] X = br.readLine().toCharArray();
        char[] Y = br.readLine().toCharArray();

        int n = X.length, m = Y.length;
        int[][] dp = new int[n + 1][m + 1];

        // base: align prefix with gaps only
        for (int i = 1; i <= n; i++) dp[i][0] = dp[i - 1][0] + B;
        for (int j = 1; j <= m; j++) dp[0][j] = dp[0][j - 1] + B;


        for (int i = 1; i <= n; i++) {
            char xi = X[i - 1];
            for (int j = 1; j <= m; j++) {
                char yj = Y[j - 1];

                int both = dp[i - 1][j - 1] + (xi == yj ? A : C); // match/mismatch
                int gapX = dp[i - 1][j] + B; // X의 문자 ↔ 공백
                int gapY = dp[i][j - 1] + B; // Y의 문자 ↔ 공백

                dp[i][j] = Math.max(both, Math.max(gapX, gapY));
            }

        }

        System.out.println(dp[n][m]);
    }
}