import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue();
        for (int food : scoville) {
            pq.add(food);
        }
        
        while (pq.peek() < K && pq.size() > 1) {
            int mixedFood = pq.poll() + (pq.poll() * 2);
            pq.add(mixedFood);
        }
        
        return pq.peek() < K ? -1 : scoville.length - pq.size();
    }
}