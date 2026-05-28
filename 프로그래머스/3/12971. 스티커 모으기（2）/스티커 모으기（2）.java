import java.util.Arrays;

class Solution {
    public int solution(int sticker[]) {
        int answer = 0;
        int n = sticker.length;
        int[] dp0 = new int[sticker.length];
        int[] dp1 = new int[sticker.length];
        
        // 원형이기때문에 뜯어낼 경우와 안뜯어 낼경우 탐색 범위를 나눠야함 그래야 안전하게 가능
        Arrays.fill(dp0, -1);
        Arrays.fill(dp1, -1);
        
        
        dp0[0] = sticker[0];
        for (int i = 1; i < n - 1; i++) {
            if (i - 2 < 0) {
                dp0[i] = dp0[i-1];
                
            } else {
                dp0[i] = Math.max(dp0[i-1], dp0[i-2] + sticker[i]);
            }
        }
        
        
        dp1[0] = 0;
        for (int i = 1; i < n; i++) {
            if (i - 2 < 0) {
                dp1[i] = Math.max(dp1[i-1], sticker[i]);
                
            } else {
                dp1[i] = Math.max(dp1[i-1], dp1[i-2] + sticker[i]);
            }
        }
        
        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, Math.max(dp0[i], dp1[i]));
        }
        
        return answer;
        
    }
}