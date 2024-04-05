class Solution {
    public long solution(int n) {
        long[] Dp = new long[2001];
        Dp[1] = 1;
        Dp[2] = 2;
        
        for (int i = 3; i < Dp.length; i++) {
            Dp[i] = (Dp[i-1] + Dp[i-2]) % 1234567;
        }
        
        
        return Dp[n] ;
    }
}