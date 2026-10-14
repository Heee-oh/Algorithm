
class Solution {
    public int solution(int[] money) {
        int answer = 0;
        int n = money.length;
        int[] dp0 = new int[n + 1]; // 처음집 포함
        int[] dp1 = new int[n + 1]; // 처음집 미포함
        
        dp0[1] = money[0]; // 첫집 포함
    
        
        // 포함 부터 
        for (int i = 2; i < n; i++) {
            dp0[i] = Math.max(dp0[i-2] + money[i-1], dp0[i-1]);
        }
        
        
        // 미포함 
        for (int i = 2; i <= n; i++) {
            dp1[i] = Math.max(dp1[i-2] + money[i-1], dp1[i-1]);
        }
        
        
        
        return Math.max(dp0[n - 1], dp1[n]);
    }
}