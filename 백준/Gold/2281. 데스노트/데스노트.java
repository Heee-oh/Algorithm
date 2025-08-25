import java.io.*;
import java.util.*;

public class Main {
    static int[] names;
    static int[] dp;
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        names = new int[n];
        dp = new int[n + 1];
        Arrays.fill(dp, -1);

        for (int i = 0; i < n; i++) {
            names[i] = Integer.parseInt(br.readLine());
        }

        int answer = dfs(0);
        System.out.println(answer);
    }

    private static int dfs(int idx) {
        if (idx == n) return 0;
        if (dp[idx] != -1) return dp[idx];

        int minCost = Integer.MAX_VALUE;
        int len = 0;

        for (int i = idx; i < n; i++) {
            len += names[i];
            if (i != idx) len++; // 공백

            if (len > m) break;

            int cost;
            if (i == n - 1) {
                // 마지막 줄은 비용 없음
                cost = 0;
            } else {
                int diff = m - len;
                cost = diff * diff;
            }

            minCost = Math.min(minCost, cost + dfs(i + 1));
        }

        return dp[idx] = minCost;
    }
}
