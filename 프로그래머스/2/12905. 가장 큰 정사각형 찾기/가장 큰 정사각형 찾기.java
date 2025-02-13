import java.util.*;

class Solution {
    public int solution(int[][] board) {
        int answer = 0;
        
        int[][] dp = new int[board.length + 1][board[0].length + 1];
        
        // 누적값 저장
        for (int i = 1; i <= board.length; i++) {
            for (int j = 1; j <= board[0].length; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] + board[i - 1][j - 1] - dp[i - 1][j - 1];
            }
        }
        
        
        int size = 0;
        int rightValue = (size + 1) * (size + 1);
        boolean flag = false;
        
        // 처음부터 탐색
        // 사각형 모양 찾기 
        // dp[i + r][j + c] - dp[i - 1][j] - dp[i][j - 1] + dp[i - 1][j - 1] * 2
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                
                while (i + size < dp.length 
                       && j + size < dp[0].length) {
                    
                    int result = dp[i + size][j + size] - dp[i - 1][j + size] - dp[i + size][j - 1] + (dp[i-1][j-1]);    
                    
                    if (result != rightValue) break;
                    
                    size++;                        
                    flag = true;
                    rightValue = (size + 1) * (size + 1);
                }
            }
        }
        
        return flag ? (size * size) : 0;
    }
}