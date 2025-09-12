import java.util.*;

class Solution {
    // 할인율이 최대인 40% 부터 시작
    // 할인율이 사용자가 정한 %이상 할인하는 이모티콘 모두 구매 
    // 구매 상한이 넘으면 플러스 가입
    
    static int n;
    static Map<Integer, int[]> map;
    
    public int[] solution(int[][] users, int[] emoticons) {
        n = emoticons.length;
        map = new HashMap<>();
        dfs(emoticons, 0, 0, new int[n]);
        
        
        int plusCnt = 0;
        int sellCost = 0;
        int[] discount = new int[n];
        
        for (int key : map.keySet()) {
            int curPlusCnt = 0;
            int curSellCost = 0;
            
            int[] discountEmoticons = map.get(key);
            for (int i = n-1; i >= 0; i--) {
                discount[i] = (key % 10);
                key /= 10;
            }
            
            for (int i = 0; i < users.length; i++) {
                int rate = users[i][0];
                int limit = users[i][1];
                
                int sum = 0;
                
                for (int j = 0; j < n; j++) {
                    // rate 비율이상만 구매함 
                    if (discount[j] * 10 < rate) continue;
                    sum += discountEmoticons[j];
                }
                
                // 제한 금액 이상이면 플러스 가입
                if (sum >= limit) {
                    curPlusCnt++;
                } else {
                    curSellCost += sum;
                }
            }
            
            
            if (plusCnt == curPlusCnt) {
                sellCost = Math.max(sellCost, curSellCost);
            } else if (plusCnt < curPlusCnt) {
                plusCnt = curPlusCnt;
                sellCost = curSellCost;
            }
        }
        

        
        return new int[] {plusCnt, sellCost};
    }
    
    private void dfs(int[] emoticons, int idx, int key, int[] arr) {
        
        if (n == idx) {
            
            map.put(key, arr.clone());
            return;
        }
        
            for (int j = 1; j <= 4; j++) {
                arr[idx] = emoticons[idx] - ((emoticons[idx] / 100) * (j * 10));
                dfs(emoticons, idx+1, key * 10 + j, arr);
            }
        
        
    }
}