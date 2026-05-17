import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Long> pq = new PriorityQueue<>();        
        for (int sc : scoville) {
            pq.offer(sc + 0L);
        }
        
        while (!pq.isEmpty()) {
            long min = pq.poll();
            if (min >= K) return answer;
            if (pq.isEmpty()) break;
            
            min += (pq.poll() << 1);
            pq.offer(min);
            answer++;
        }
        
        
        return -1;
    }
}