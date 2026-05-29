import java.util.Arrays;

class Solution {
    public int solution(int[] money) {
        int n = money.length;
        
        int[] dp0 = new int[n];
        int[] dp1 = new int[n];
        Arrays.fill(dp0, -1);
        Arrays.fill(dp1, -1);
        
        // 0번 마을 털기
        dp0[0] = money[0];
        for (int i = 1; i < n-1; i++) {
            if (i - 2 < 0) {
                dp0[i] = dp0[i-1];
            } else {
                dp0[i] = Math.max(dp0[i-1], dp0[i-2] + money[i]);
            }
        }
        
        // 0번 마을을 안털고 1번마을 부터 
        dp1[0] = 0;
        dp1[1] = money[1];
        
        for (int i = 2; i < n; i++) {
            dp1[i] = Math.max(dp1[i-1], dp1[i-2] + money[i]);
        }

        return Math.max(dp0[n-2], dp1[n-1]);
    }
}