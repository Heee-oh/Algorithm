class Solution {
    public int solution(int n) {
        int answer = 0;
        
        // ()  n = 1
        // ()() n = 2
        // (())
        
        // ((())), (()()), n = 3 
        // ()()(), ()(()), (())()
        
        // () n-1
        // n-1 ()
        
        // ()() n-2
        // n-2 ()()
        
        // ()()() n-3
        // n-3 ()()()
        
        // n-2 n-2
        
        // (*) + ()~ ~() - ()()()...
        // () ~, ()() ~, ()()() ~
        // dp[i] = dp[i-1] + 2*dp[i-1] - 2 + 2*dp[i-2] - 2 ... + 1
        
        int[] dp = new int[n+1];
        int[] dp2 = new int[n+1];
        dp[0] = dp[1] = 1;
        dp2[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            dp2[i] = dp[i-1]; // (a)
            
            
            // (a) + b
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[i - j] * dp2[j];
            }
            
        }
        
        
        return dp[n];
    }
}