import java.util.*;

class Solution {
    
    static int n, min;
    static List<boolean[]> list;
    
    public int solution(int[][] cost, int[][] hint) {
        int answer = Integer.MAX_VALUE;
        n = cost.length;
        list = new ArrayList<>();
        // 모든 힌트번틀 구매 경우의 수를 구함
        dfs(0, new boolean[n]);
       
        
        for (int i = 0; i < list.size(); i++) {
            boolean[] buy = list.get(i);
            int sum = 0;
            
            int[] coupon = new int[n+1];
            
            for (int stage = 0; stage < n; stage++) {    
                int used = Math.min(coupon[stage + 1], n - 1); // 쿠폰은 최대 n-1개 사용 가능
                sum += cost[stage][used];
                
                if (buy[stage]) {
                    sum += hint[stage][0];
                    for (int k = 1; k < hint[stage].length; k++) {
                        int hintN = hint[stage][k];
                        coupon[hintN]++;
                    }
                }
            }
            
            answer = Math.min(answer, sum);
            
        }
      
        
        return answer;
    }
    
    
    
    private void dfs(int stage, boolean[] buy) {
        if (stage == n - 1) {
            list.add(buy.clone());
            return;
        }

        // 안 사는 경우
        buy[stage] = false;
        dfs(stage + 1, buy);

        // 사는 경우
        buy[stage] = true;
        dfs(stage + 1, buy);
        
    }
}