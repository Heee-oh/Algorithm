class Solution {
    public int solution(int n) {
        int answer = 0;
        int[] dp = new int[60001];
        
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        
        for (int i = 4; i <= n; i++) {
            dp[i] = (dp[i-1] + (dp[i-2] ) % 1000000007) % 1000000007;
        }
        
        return dp[n] % 1000000007;
    }
}