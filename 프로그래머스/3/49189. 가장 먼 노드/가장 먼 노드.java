import java.util.*;

class Solution {
    
    public int solution(int n, int[][] edge) {
        boolean[][] graph = new boolean[n + 1][n + 1];
        boolean[] visited = new boolean[n + 1];
        int[] dist = new int[n + 1];
        
        for (int i = 0; i < edge.length; i++) {
            graph[edge[i][0]][edge[i][1]] = true;
            graph[edge[i][1]][edge[i][0]] = true;
        }
        
        int max = bfs(graph, visited, dist);
        int answer = 0;
        
        for (int distance : dist) {
            
            if (distance == max) {
                answer++;
            }
        }
        
        return answer;
    }
    
    private int bfs(boolean[][] graph, boolean[] visited, int[] dist) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {1, 1});
        visited[1] = true;
        int max = 0;
        
        while(!q.isEmpty()) {
            int[] node = q.poll();
            
            for (int i = 1; i < graph.length; i++) {
                if (!visited[i] && graph[node[0]][i]) {
                    q.add(new int[] {i, node[1] + 1});
                    dist[i] = node[1] + 1;
                    visited[i] = true;
                    max = Math.max(max, node[1] + 1);
                }
            }
            
        }
        return max;
    }
}