import java.util.*;

class Solution {
    
    static int[] ogates;
    static int[] osummits;
    static List<Node>[] graph;
    static int[] dist;
    
    class Node {
        int u, w;
        
        public Node(int u, int w) {
            this.u = u;
            this.w = w;
        }
    }
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        ogates = gates;
        osummits = summits;
        int[] answer = new int[2];
        
        graph = new ArrayList[n + 1];
        
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        // 그래프 초기화
        for (int[] path : paths) {
            int i = path[0];
            int j = path[1];
            int w = path[2];
            
            graph[i].add(new Node(j, w));
            graph[j].add(new Node(i, w));
        }
        
        dijkstra(n, summits);
        
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < summits.length; i++) {
            int summit = summits[i];
            if (min > dist[summit]) {
                answer[0] = summit;
                answer[1] = dist[summit];
                
                min = dist[summit];
                
            } else if (min == dist[summit]) {
                answer[0] = Math.min(summit, answer[0]);
            }
        }
        
        return answer;
    }
    
    private void dijkstra(int n, int[] summits) {
        // next, w, cnt
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
        dist = new int[n+1]; // intensity가 최소가 되게 방문처리 
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        boolean[] isGate = new boolean[n+1];
        boolean[] isSummit = new boolean[n+1];
        boolean[] visited = new boolean[n+1];
        
        for (int i = 0; i < ogates.length; i++) {
            pq.add(new Node(ogates[i], 0));
            isGate[ogates[i]] = true;
            dist[ogates[i]] = 0;
        }
        
        for (int summit : summits) {
            isSummit[summit] = true;
        }
        
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            
            if (visited[cur.u] ) {
                continue;
            }

            visited[cur.u] = true;

            if (isSummit[cur.u]) {
                continue;
            }
            for (Node next : graph[cur.u]) {
                int newIntensity = Math.max(cur.w, next.w);
                if (!isGate[next.u] && dist[next.u] >  newIntensity) {
                    dist[next.u] = newIntensity;
                    pq.add(new Node(next.u, dist[next.u]));
                }
            }
            
        }
    }
}