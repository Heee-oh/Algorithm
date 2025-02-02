import java.util.*;

class Solution {
    // N개의 마을, K : 갈 수 있는 거리
    // BFS 탐색으로 K - 각 마을까지의 비용 >= 0 인 곳을 방문
    int[] visited;
    
    public int solution(int N, int[][] road, int K) {
        int[][] graph = new int[N + 1][N + 1]; 
        visited = new int[N + 1];
        
        // 마을 그래프 생성
        for (int i = 0; i < road.length; i++) {
            int start = road[i][0];
            int end = road[i][1];
            
            
            // 양방향, 걸리는 시간 최소값
            graph[start][end] = graph[end][start] 
                = ((graph[start][end] > 0) ? 
                   Math.min(graph[start][end], road[i][2]) : road[i][2]);
        }

        return bfs(graph, K);
    }
    
    private int bfs(int[][] towns, int k) {
        Queue<int[]> q = new LinkedList<>(); // [다음 마을, 남은 k]
        q.add(new int[] {1, 0}); // 문제에서 '1번 마을에 있는 음식점'
        visited[1] = k;
        int count = 1; // 1번 마을도 포함
        
        while(!q.isEmpty()) {
            int[] curTownInfo = q.poll();
            int idx = curTownInfo[0];

            
            // 0이면 더이상 배달할 시간이 없음
            if (curTownInfo[1] == k) continue;
                     
            // 각 연결된 마을들을 순회
            for (int i = 1; i < towns.length; i++) {
                if (towns[idx][i] > 0 
                    && (curTownInfo[1] + towns[idx][i]) <= k) {// 남은 배달시간 안에 가능하다면 
                    
                    // 같은 마을을 가는데 더 비용이 적게 든다면
                    if (visited[i] > curTownInfo[1] + towns[idx][i]) {
                        q.add(new int[] {i, (curTownInfo[1] + towns[idx][i])});
                        visited[i] = curTownInfo[1] + towns[idx][i];
                        
                    } else if (visited[i] == 0) {
                        q.add(new int[] {i, (curTownInfo[1] + towns[idx][i])});
                        visited[i] = curTownInfo[1] + towns[idx][i];
                        count++;
                    }
                }
            }
        }
        return count;
    }
}