import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int[][] dp = new int[500][500];
        
        // 왼쪽 위, 위
        
        dp[0][0] = triangle[0][0];
        
        
        for (int i = 1; i < triangle.length; i++) {
            for (int j = 0; j < triangle[i].length; j++) {
                if (j - 1 >= 0 && triangle[i - 1].length > j){
                    dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i-1][j]) + triangle[i][j];
                } else if (j - 1 >= 0) {
                    dp[i][j] = dp[i - 1][j - 1] + triangle[i][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + triangle[i][j];
                }
                
            }
        }
        
        
        return Arrays.stream(dp[triangle.length - 1]).max().getAsInt();
    }
}