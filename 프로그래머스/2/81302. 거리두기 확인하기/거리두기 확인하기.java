import java.util.*;

class Solution {
    
    int[] dr = {0, 0, 1, -1};
    int[] dc = {1, -1, 0, 0};
    
    // 모든 정점의 거리값이 2를 초과하면 됌
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        Arrays.fill(answer, 1);
        
        for (int i = 0; i < places.length; i++) {
            char[][] map = new char[5][5];
            
            for (int j = 0; j < 5; j++) {
                map[j] = places[i][j].toCharArray();                
            }
            
            valid(map, answer, i);
            
            
        }
        
        
        return answer;
    }
    
    private void valid(char[][] map, int[] answer, int i){
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                if (map[r][c] == 'P' && !bfs(map, r, c)) {
                    answer[i] = 0;
                    return;
                }
            }
        }
    }
    
    private boolean bfs(char[][] map, int r, int c) {
        boolean[][] visited = new boolean[5][5];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {r, c, 0});
        visited[r][c] = true;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            
            if (cur[2] >= 3) {
                break;
            }
            
            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                
                if (nr < 0 || nr >= 5 
                    || nc < 0 || nc >= 5 
                    || visited[nr][nc] || map[nr][nc] == 'X') continue;
                    
                
                // 도달 거리가 2이하이면 거리두지 않은 판단
                if (map[nr][nc] == 'P' && cur[2] + 1 <= 2) {
                    return false;
                }
                
                visited[nr][nc] = true;
                q.offer(new int[] {nr, nc, cur[2] + 1});
            }
        }
        
        
        
        return true;
        
        
    }
}