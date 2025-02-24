class Solution {
    public int solution(int sticker[]) {
        int[] dp = new int[sticker.length];
        int answer = 0;
        
        if (dp.length == 1) {
            return sticker[0];
        }
        
        // 0부터 시작
        dp[0] = sticker[0];
        for (int i = 1; i < dp.length - 1; i++) {
            if (i == 1) {
                dp[i] = Math.max(sticker[i], dp[i - 1]);
                continue;
            }
            dp[i] = Math.max(dp[i - 2] + sticker[i], dp[i - 1]);
        }
        answer = dp[dp.length - 2];

        dp[0] = 0;
        
        // 1부터 시작
        for (int i = 1; i < dp.length; i++) {
            if (i == 1) {
                dp[i] = Math.max(sticker[i], dp[i - 1]);
                continue;
            }
            dp[i] = Math.max(dp[i - 2] + sticker[i], dp[i - 1]);
        }
   
        answer = Math.max(answer, dp[dp.length - 1]);

        return answer;
    }
}