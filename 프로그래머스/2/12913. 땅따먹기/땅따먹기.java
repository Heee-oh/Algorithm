import java.util.Arrays;

class Solution {
    
    // 선택한 땅을 제외한 이전 땅의 합중 가장 큰 것을 선택
    int solution(int[][] land) {
        int[][] dp = new int[land.length][land[0].length];
        int answer = 0;
        
        for (int i = 0; i < land[0].length; i++) {
            dp[0][i] = land[0][i];
        }
        
        for (int i = 1; i < land.length; i++) {
            
            // 땅 선택
            for (int j = 0; j < 4; j++) {
                
                // 각 땅의 최댓값 구하기
                for (int k = 0; k < 4; k++) {
                    if (j == k) continue;
                    // 이전 선택한 최댓값 땅 합 + 현재 선택한 땅, 현재 땅 최댓값 
                    dp[i][j] = Math.max(dp[i - 1][k] + land[i][j], dp[i][j]);
                }
            }
        }
        
        for (int n : dp[land.length - 1]) {
            answer = Math.max(n, answer);
        }
        
        
        return answer;
    }
}