class Solution {
    
    static int MOD = 10_007;
    public int solution(int n, int[] tops) {
        int[] sharedDp = new int[n];
        int[] noSharedDp = new int[n];
        
        if (tops[0] == 1) {
            sharedDp[0] = 4;
            noSharedDp[0] = 3;
        } else {
            sharedDp[0] = 3;
            noSharedDp[0] = 2;
        }
        
        for (int i = 1; i < n; i++) {
            if (tops[i] == 1) {
                
                sharedDp[i] = (sharedDp[i-1] * 3 + noSharedDp[i-1]) % MOD;
                noSharedDp[i] = (sharedDp[i-1] * 2 + noSharedDp[i-1]) % MOD;
                
            } else {
                                
                sharedDp[i] = (sharedDp[i-1] * 2 + noSharedDp[i-1]) % MOD;
                noSharedDp[i] = (sharedDp[i-1] + noSharedDp[i-1]) % MOD;
            }
            
            
        }
        
        return sharedDp[n-1];
    }
}