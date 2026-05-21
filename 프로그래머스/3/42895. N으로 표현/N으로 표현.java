import java.util.*;

class Solution {
    public int solution(int N, int number) {
        Set<Integer>[] dp = new HashSet[9];
        
        for (int i = 0; i <= 8; i++) {
            dp[i] = new HashSet<>();
        }
        
        int num = 0;
        for (int cnt = 1; cnt <= 8; cnt++) {
            num += N;
            dp[cnt].add(num);
            num *= 10;
            
            for (int l = 1; l < cnt; l++) {
                int r = cnt - l;
                
                for (int a : dp[l]) {
                    for (int b : dp[r]) {
                        dp[cnt].add(a + b);
                        dp[cnt].add(a - b);
                        dp[cnt].add(a * b);
                        
                        if (b != 0) {
                            dp[cnt].add(a / b);
                        }
                    }
                }
            }
            
            if (dp[cnt].contains(number)) {
                return cnt;
            }
        }
        return -1;
    }
}