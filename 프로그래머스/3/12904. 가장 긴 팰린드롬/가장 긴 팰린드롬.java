import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        char[] str = s.toCharArray();
        
        boolean[][] dp = new boolean[str.length][str.length];
        
        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = true;
            
            if (i + 1 < dp.length && str[i] == str[i+1]) {
                dp[i][i+1] = true;
                answer = 1;
            }
            
        }
        for (int len = 2; len < dp.length; len++) {
            boolean flag = false;
            for (int l = 0; l < dp.length - len; l++) {
                int r = l + len;
                
                // 안쪽이 팰린드롬이고 탐색하는 왼쪽 오른쪽이 팰린드롬이라면 
                if (dp[l + 1][r - 1] && str[l] == str[r]) {
                    answer = len;
                    dp[l][r] = true;
                    flag = true;
                }
                    
                
            }
        }


        return answer == 0 ? 1 : answer + 1;
    }
}