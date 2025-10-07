class Solution {
    static final int MOD = 10_007;
    public int solution(int n, int[] tops) {
        int answer = 0;
        
        int[] share = new int[n];
        int[] notShare = new int[n];
        
        if (tops[0] == 1) {
            share[0] = 4;
            notShare[0] = 3;
        } else {
            share[0] = 3;
            notShare[0] = 2;
        }
        
        
        for (int i = 1; i < n; i++) {
            if (tops[i] == 1) {
                share[i] = (share[i-1] * 3 + notShare[i-1]) % MOD;
                notShare[i] = (share[i-1] * 2 + notShare[i-1]) % MOD;
            } else {
                share[i] = (share[i-1] * 2 + notShare[i-1]) % MOD;
                notShare[i] = (share[i-1] + notShare[i-1]) % MOD;
            }
        }
        
        
        
        
        return share[n-1];
    }
}