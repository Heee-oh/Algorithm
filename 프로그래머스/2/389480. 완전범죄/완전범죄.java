import java.util.Arrays;

class Solution {
    // A, B 도둑이 잡히지 않으면서
    // A 도둑의 최소 흔적 구하기
    int INF = (int)1e9;
    public int solution(int[][] info, int n, int m) {
        int answer = 0;
        
        int[] dp = new int[m];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        
        for (int[] arr : info) {
            int a = arr[0];
            int b = arr[1];
            
            int[] nextDp = new int[m];
            Arrays.fill(nextDp, INF);
            
            for (int j = 0; j < m; j++) {
                if (dp[j] == INF) continue;
                
                nextDp[j] = Math.min(nextDp[j], dp[j] + a);
                if (j + b < m) {
                    nextDp[j + b] = Math.min(nextDp[j+b], dp[j]);
                }
            }
            
            dp = nextDp;
        }
        
        
        int minA = INF;
        for (int j = 0; j < m; j++) {
            if (dp[j] < n) {
                minA = Math.min(minA, dp[j]);
            }
        }

        return minA == INF ? -1 : minA;
        
    }

}