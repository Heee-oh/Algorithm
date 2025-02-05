import java.util.*;

class Solution {
    
    // BFS 탐색으로 처리하자 
    // 탐색하면서 다 더하고 
    // 값을 우선순위 큐에 저장
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    
    boolean[][] visited;
    ArrayList<Integer> list = new ArrayList<>();
    
    public int[] solution(String[] maps) {
        visited = new boolean[maps.length][maps[0].length()];
        
        // 식량이 있는 곳 탐색
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[0].length(); j++) {
                if (maps[i].charAt(j) != 'X' && !visited[i][j]) {
                    list.add(bfs(maps, i, j));
                }
            }
        }
        
        return list.isEmpty() ? new int[] {-1} 
                                : list.stream().mapToInt(x -> x).sorted().toArray();
    }
    
    private int bfs(String[] graph, int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {r, c});
        visited[r][c] = true;
        
        int sum = graph[r].charAt(c) - '0';
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            
            for (int i = 0; i < 4; i++) {
                int nextR = cur[0] + dy[i];
                int nextC = cur[1] + dx[i];
                
                // 범위를 벗어나거나 이미 방문했거나 X면 넘어감
                if (nextR < 0 || nextR >= graph.length 
                    || nextC < 0 || nextC >= graph[0].length() 
                    || visited[nextR][nextC] || graph[nextR].charAt(nextC) == 'X') continue;
                
                // 식량 추가
                sum += graph[nextR].charAt(nextC) - '0';
                
                // 큐에 넣고 방문 처리 
                q.add(new int[] {nextR, nextC});
                visited[nextR][nextC] = true;
            }
        }
        
        return sum;
    }
}