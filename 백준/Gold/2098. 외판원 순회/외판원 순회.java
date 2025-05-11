import com.sun.security.jgss.GSSUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int MOD = 1000000000;
    static int[][] W;
    static int[][] dp;
    static int n, fullBit, INF = 100000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        n = Integer.parseInt(br.readLine());
        fullBit = (1 << n) - 1;

        W = new int[n][n];
        dp = new int[n][fullBit];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        // W 초기화
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 0에서부터 출발
        System.out.println(tps(0, 1));


    }

    private static int tps(int x, int mask) {

        // 모든 도시 방문했을 경우
        if (mask == fullBit) {
            // 마지막으로 x -> 0인 원지점으로 도착하려했으나 길이가 0인 자기 자신인 경우 경로가 없음, INF로 탐색 무효화
            if (W[x][0] == 0) return INF;
            else return W[x][0]; //
        }

        // 이미 방문했다면 저장된 값 반환
        if (dp[x][mask] != -1) return dp[x][mask];

        // 방문 표시 (dp를 모두 -1로 초기화해줬으므로 방문했다면 큰 값으로 초기화함)
        dp[x][mask] = INF;

        for (int i = 0; i < n; i++) {
            int newMask = mask | 1 << i;

            // 경로가 없거나 도시 이미 방문했을 경우 다른 도시 탐색
            if ((mask & 1 << i) != 0 || W[x][i] == 0) {
                continue;
            }

            dp[x][mask] = Math.min(dp[x][mask], tps(i, newMask) + W[x][i]);


        }

        return dp[x][mask];
    }
}
