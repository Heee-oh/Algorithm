import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {

        HashMap<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        int minCount = 0;
        for (int t : tangerine) 
            map.put(t , map.getOrDefault(t, 0) + 1);
        
        for (Integer i : map.keySet()) {
                pq.add(map.get(i));
        }
        
        while (k > 0) {
            
            if(k < pq.peek()) {
                minCount++;
                break;
            } else {
                k -= pq.poll();
                minCount++;    
            }
            
        }
                

        return minCount;
    }
}
