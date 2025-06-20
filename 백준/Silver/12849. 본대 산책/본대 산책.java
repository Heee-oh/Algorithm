import java.io.*;
import java.util.*;

public class Main {

    static int[][] dp;
    static int[][] graph = {{1, 2}, {0, 2, 3}, {0, 1, 3, 4}, {1, 2, 4, 5}, {2, 3, 5, 6}, {3, 4, 7}, {4, 7}, {5, 6}};
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int D = Integer.parseInt(br.readLine());

        dp = new int[8][D+1];
        for (int i = 0; i < 8; i++) {
            Arrays.fill(dp[i], -1);
        }
        dp[0][0] = 1;
        
        System.out.println(dfs(D, 0));

    }

    private static int dfs(int d, int node) {
        //남은 시간이 노드 깊이보다 작다면 돌아갈 수 없으므로 0 리턴
        if (d < 0) return 0;
        if (d == 0 && node == 0) {
            return 1;
        }

        if (dp[node][d] != -1) {
            return dp[node][d];
        }


        int sum = 0;
        for (int next : graph[node]) {
            sum = (sum + dfs(d - 1, next)) % MOD;
        }

        return dp[node][d] = sum;
    }


}
