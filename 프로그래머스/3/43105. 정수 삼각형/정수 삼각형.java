import java.util.Arrays;

class Solution {
    public int solution(int[][] triangle) {
        int[][] dp = new int[triangle.length][triangle.length];
        dp[0][0] = triangle[0][0];
        
        // [i-1] ([j -1], [j]) 중 제일 큰 값을 취함
        
        for (int i = 1; i < triangle.length; i++) {
            for (int j = 0; j < i + 1; j++) {
                if (j - 1 >= 0 && j <= i) {
                    dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i-1][j]);
                } else if (j - 1 >= 0){
                    dp[i][j] = dp[i-1][j-1];
                } else if (j <= i) {
                    dp[i][j] = dp[i-1][j];
                }
                
                dp[i][j] += triangle[i][j];
            }
        }
        
        

        return Arrays.stream(dp[dp.length - 1]).max().getAsInt();
    }
}