import java.util.*;

class Solution {
    // 최소한의 다트로 0점 , 여러가지시, 싱글 or 불 최대한 많이
    // [다트 수 , 싱글+불 합]
    class Score {
        int dart;
        int sum;
        
        public Score(int dart, int sum) {
            this.dart = dart;
            this.sum = sum;
        }
    }
    static final int INF = Integer.MAX_VALUE;
    public int[] solution(int target) {
        int[] answer = {};
        
        Set<Integer> scores = new HashSet<>();
        for (int i = 1; i <= 20; i++) {
            scores.add(i);
            scores.add(i * 2);
            scores.add(i * 3);
        }
        scores.add(50);
        List<Integer> list = new ArrayList<>(scores);
        
        Score[] dp = new Score[target + 1];
        dp[0] = new Score(0, 0);

        for (int i = 1; i <= target; i++) {
            dp[i] = new Score(INF, 0);
            
            for (int j = 0; j < list.size(); j++) {
                int s = list.get(j);
                
                if (i - s < 0) continue;
                Score prev = dp[i - s];
                int newDart = prev.dart + 1;
                int newSum = prev.sum + (isSingleOrBull(s) ? 1 : 0);

                if (newDart < dp[i].dart ||
                   (newDart == dp[i].dart && newSum > dp[i].sum)) {
                    dp[i].dart = newDart;
                    dp[i].sum = newSum;
                }
            }
        }
        
        
        return new int[] {dp[target].dart, dp[target].sum};
    }
    
    private boolean isSingleOrBull(int s) {
        return s <= 20 || s == 50;
    }
}