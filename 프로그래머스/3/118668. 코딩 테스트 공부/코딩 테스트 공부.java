import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int maxAlpReq = 0, maxCopReq = 0;

        // 문제들 중 가장 큰 요구 알고력, 코딩력 찾기
        for (int[] p : problems) {
            maxAlpReq = Math.max(maxAlpReq, p[0]);
            maxCopReq = Math.max(maxCopReq, p[1]);
        }

        // 시작 능력이 이미 요구치를 넘는 경우 보정
        alp = Math.min(alp, maxAlpReq);
        cop = Math.min(cop, maxCopReq);

        // dp[a][c] = a 알고력, c 코딩력 도달 최소 시간
        int[][] dp = new int[maxAlpReq + 2][maxCopReq + 2];
        for (int[] row : dp) Arrays.fill(row, Integer.MAX_VALUE);
        dp[alp][cop] = 0;

        for (int a = alp; a <= maxAlpReq; a++) {
            for (int c = cop; c <= maxCopReq; c++) {
                if (dp[a][c] == Integer.MAX_VALUE) continue;

                // 1. 공부해서 알고력 +1
                if (a + 1 <= maxAlpReq)
                    dp[a + 1][c] = Math.min(dp[a + 1][c], dp[a][c] + 1);

                // 2. 공부해서 코딩력 +1
                if (c + 1 <= maxCopReq)
                    dp[a][c + 1] = Math.min(dp[a][c + 1], dp[a][c] + 1);

                // 3. 문제 풀기
                for (int[] p : problems) {
                    int reqAlp = p[0], reqCop = p[1];
                    int rwdAlp = p[2], rwdCop = p[3], cost = p[4];

                    if (a >= reqAlp && c >= reqCop) {
                        int newAlp = Math.min(maxAlpReq, a + rwdAlp);
                        int newCop = Math.min(maxCopReq, c + rwdCop);
                        dp[newAlp][newCop] = Math.min(dp[newAlp][newCop], dp[a][c] + cost);
                    }
                }
            }
        }

        return dp[maxAlpReq][maxCopReq];
    }
}