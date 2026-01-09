import java.util.*;

class Solution {
    
    int[] dist;
    boolean[] visited;
    
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        List<int[]>[] graph = new ArrayList[N+1];
        dist = new int[N+1];
        visited = new boolean[N+1];
        
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        for (int[] info : road) {
            int a = info[0];
            int b = info[1];
            int c = info[2];
            
            graph[a].add(new int[] {b,c});
            graph[b].add(new int[] {a,c});
            
        }
        dijkstra(graph);
        
        for (int i = 1; i <= N; i++) {
            answer += dist[i] <= K ? 1 : 0;
        }
        return answer;
    }
    
    private void dijkstra(List<int[]>[] graph) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[] {1, 0});
        dist[1] = 0;
        
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int n = cur[0];
            int cost = cur[1];
            
            if (visited[n]) {
                continue;
            }
            
            for (int i = 0; i < graph[n].size(); i++) {
                int[] next = graph[n].get(i);
                int nextN = next[0];
                int nextCost = next[1];
                
                if (dist[nextN] > dist[n] + nextCost) {
                    dist[nextN] = dist[n] + nextCost;
                    pq.add(new int[] {nextN, dist[nextN]});
                }
            }
            
            
        }
        
    }
}