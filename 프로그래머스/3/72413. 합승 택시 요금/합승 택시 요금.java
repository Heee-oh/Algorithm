import java.util.*;
 
class Solution {
    // 만약, 아예 합승을 하지 않고 각자 이동하는 경우의 예상 택시요금이 더 낮다면, 합승을 하지 않아도 됩니다.
    class Node {
        int num, cost;
        
        public Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }
    
    static List<Node>[] graph;
    static int[][] dists;
    static int A, B;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        A = a;
        B = b;
        
        graph = new ArrayList[n+1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < fares.length; i++) {
            int v = fares[i][0];
            int u = fares[i][1];
            int w = fares[i][2];
            
            graph[v].add(new Node(u, w));
            graph[u].add(new Node(v, w));
        }
        
        // 각 노드에서의 거리값
        dists = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            int[] rs = dijkstra(n, i);
            dists[i] = rs;
        }
        
        for (int i = 1; i <= n; i++) {
            answer = Math.min(answer , dists[s][i] + dists[i][a] + dists[i][b]);
        }
         
        return answer;
    }
    
    private int[] dijkstra(int n, int s) {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.add(new Node(s, 0));
        
        boolean[] check = new boolean[n+1];
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;
        
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            
            if (check[cur.num]) continue;
            check[cur.num] = true;
            
            for (Node next : graph[cur.num]) {
                if (!check[next.num] && dist[next.num] > dist[cur.num] + next.cost) {
                    dist[next.num] = dist[cur.num] + next.cost;
                        
                    pq.add(new Node(next.num, dist[next.num]));
                }
            }
            
        }
        
        
        return dist;
    }
}