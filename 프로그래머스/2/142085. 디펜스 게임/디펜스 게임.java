import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        // 우선순위 큐에 일단 k까지 넣는다. 
        // 넣은 값들의 최댓값을 저장
        int answer = 0;
        int max = 0;
        
        for (int i = 0; i < enemy.length; i++) {
            
            // k 까지 우선 넣음
            if (i < k) {
                pq.add(enemy[i]);
                max = Math.max(max, enemy[i]);
                answer++;
                continue;
            }
            
            // 무적권사용으로 넘어간 최대 적 수보다 현재 적 수가 더 많다면
            if ((max <= enemy[i] || pq.peek() < enemy[i]) && n >= pq.peek()) {
                n -= pq.poll();
                pq.add(enemy[i]);
                answer++;
                
                max = enemy[i];
                
                // n명으로 막기
            } else if (n >= enemy[i]){
                n -= enemy[i];
                answer++;
                // 막지 못한 경우 멈춤
            } else {
                break;
            }
        }
        
        
        return answer;
    }
}