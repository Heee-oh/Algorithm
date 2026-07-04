import java.util.*;

class Solution {
    class Food implements Comparable<Food> {
        int time;
        int idx; // 원래 음식 번호 (1부터 시작)

        Food(int time, int idx) {
            this.time = time;
            this.idx = idx;
        }

        @Override
        public int compareTo(Food o) {
            return Integer.compare(this.time, o.time); // 시간 오름차순 정렬
        }
    }
    // 1번 음식부터 먹기 -> 번호 증가순으로 음식을 갖다놓음
    // 마지막 번호 음식 섭취 -> 1번음식이 다시 앞으로 옴
    // 음식 1초동안 섭취 후 다음 음식 (남은 음식중 다음 가까운 번호의 음식)
    
    // K초 후  몇번 음식부터 섭취해야하는가? 
    // 즉, K초 때 먹은 거 다음걸 출력
    
    public int solution(int[] food_times, long k) {
        int answer = 0;
        long total = Arrays.stream(food_times).asLongStream().sum();
        // 다먹어서 더이상 갈곳이 없거나 음식이 부족한 경우 -1
        if (k >= total) return -1;
        
        // 공통으로 먹을 수 있는 최소값 순으로 저장 
        PriorityQueue<Food> pq = new PriorityQueue<>();
        for (int i = 0; i < food_times.length; i++) {
            pq.offer(new Food(food_times[i], i + 1));
        }
        
        int len = food_times.length;
        long prevTime = 0;
        while (!pq.isEmpty()) {
            Food cur = pq.peek();
            
            long totalDiffTime = (long)pq.size() * (cur.time - prevTime);
            
            if (k < totalDiffTime) break;
            
            k -= totalDiffTime; // 먹은 시간만큼 빼준다
            prevTime = cur.time;  // 직전 음식 시간 갱신
            pq.poll();          // 다 먹은 음식 제거
            
        }
        
        List<Food> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            result.add(pq.poll());
        }
        
        // 원래 음식 번호(idx) 순서대로 재정렬
        Collections.sort(result, (a, b) -> Integer.compare(a.idx, b.idx));
        
        // 남은 k초를 남은 음식 개수로 나눴을때 위치
        return result.get((int) (k % result.size())).idx;
    }
    
 
    
}