import java.util.*;

class Solution {
    
    boolean[] visited;
    boolean[][] graph;
    
    public int solution(int n, int[][] wires) {        
        graph = new boolean[n + 1][n + 1];        
        visited = new boolean[n + 1];
        
        // 하나씩 꺼내서 노드 연결
        for (int i = 0; i < wires.length; i++) {
            int node = wires[i][0];
            int nextNode = wires[i][1];
            
            graph[node][nextNode] = graph[nextNode][node] = true;
        }
        
        int answer = Integer.MAX_VALUE;
        
        for (int i = 1; i < graph.length; i++) {
            for (int j = 1; j < graph[0].length; j++) {
                if (graph[i][j]) {
                    
                    graph[i][j] = graph[j][i] = false;
                    
                    int netWork_1 = bfs(i);
                    int netWork_2 = bfs(j);
            
                    graph[i][j] = graph[j][i] = true;
                    visited = new boolean[n + 1];
                    
                    answer = Math.min(Math.abs(netWork_1 - netWork_2), answer);
                }
            }
        }
        
        
        
        return answer;
    }
    
    
    // bfs 탐색으로 개수 카운트
    private int bfs(int netWork) {
        Queue<Integer> q = new LinkedList<>();
        q.add(netWork);
        visited[netWork] = true;
        int count = 1;
        
        while (!q.isEmpty()) {
            int node = q.poll();
            
            for (int i = 1; i < graph[0].length; i++) {
                if (graph[node][i] && !visited[i]) {
                    visited[i] = true;
                    q.add(i);
                    count++;
                }
            }
        }
        
        return count;
    }
}