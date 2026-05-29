import java.util.Arrays;

class Solution {
    public int solution(int[] money) {
        int n = money.length;
        
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            dp[i][0] = dp[i][1] = -1;
        }
        
        dp[0][0] = dp[1][0] = money[0];
        dp[0][1] = 0;
        dp[1][1] = money[1];
        
        for (int i = 2; i < n; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-2][0] + money[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-2][1] + money[i]);
        }
        
        return Math.max(dp[n-2][0], dp[n-1][1]);
    }
}