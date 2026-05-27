import java.util.Arrays;
class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;
        
        int min = Integer.MAX_VALUE, max = 0;
        for (int i = 0; i < stones.length; i++) {
            min = Math.min(min, stones[i]);
            max = Math.max(max, stones[i]);
        }
        
        
        while (min <= max) {
            
            int mid = (min + max) >>> 1;
            
            int len = 0;
            for (int i = 0; i < stones.length; i++) {
                int st = stones[i];
                if (st - mid < 0) {
                    len++;
                } else {
                    len = 0;
                }
                
                if (len >= k) {
                    break;
                }
            }
            
            if (len >= k) {
                max = mid - 1;
            } else {
                answer = mid;
                
                min = mid + 1;
            }
        }
        
        

        return answer;
    }
}