import java.util.*;

class Solution {
    
    class Job {
        int num, req, cost;
        
        public Job(int num, int req, int cost) {
            this.num = num;
            this.req = req;
            this.cost = cost;
        }
    }
    
    
    public int solution(int[][] jobs) {
        int n = jobs.length;
        int answer = 0;
        
        Job[] arr = new Job[n];
        
        for (int i = 0; i < jobs.length; i++) {
            arr[i] = new Job(i, jobs[i][0], jobs[i][1]);
        }
        
        Arrays.sort(arr,(o1, o2) -> o1.req - o2.req);
        
        
        // 소요시간, 요청시간, 낮은 번호순
        PriorityQueue<Job> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.cost != o2.cost) {
                return o1.cost - o2.cost;
            }
            
            if (o1.req != o2.req) {
                return o1.req - o2.req;
            }
            
            return o1.num - o2.num;
        });
        
        int time = 0;
        int i = 0; 
        //각 작업물을 순서대로 저장
        while (i < n) {
            while (i < n && arr[i].req <= time) {
                pq.add(arr[i]);
                i++;
            }
            
            
            // 작업 꺼내기 
            if (!pq.isEmpty()) {
                Job cur = pq.poll();
                
                // 요청 시간이 남았다면 이때까지 기다리기 
                if (time < cur.req) time = cur.req;
                
                // 작업 끝낸 시간으로 이동
                time += cur.cost;
                answer += (time - cur.req);
            } else {
                time = arr[i].req;
            }
        }
        
        
        // 아직 못끝낸 작업들 처리
        while(!pq.isEmpty()) {
            Job cur = pq.poll();
                
            // 요청 시간이 남았다면 이때까지 기다리기 
            if (time < cur.req) time = cur.req;
                
            // 작업 끝낸 시간으로 이동
            time += cur.cost;
            answer += (time - cur.req);
        }
        
        
        return answer / n;
    }
}