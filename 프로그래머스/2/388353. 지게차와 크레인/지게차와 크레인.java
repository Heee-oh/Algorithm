import java.util.*;

class Solution {
    char[][] map;
    int n, m;
    
    int[] dr = {0, 0, 1, -1};
    int[] dc = { 1, -1, 0, 0};
    
    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        n = storage.length;
        m = storage[0].length();
        
        map = new char[n + 2][m + 2];
        for (int i = 0; i < n+2; i++) {
            Arrays.fill(map[i], ' ');
        }
        
        for (int i = 0; i < storage.length; i++) {
            char[] arr = storage[i].toCharArray();
            
            for (int j = 0; j < storage[0].length(); j++) {
                map[i+1][j+1] = arr[j];
            }
        }
        
        // 컨테이너 꺼내기 
        for (String req : requests) {
            char container = req.charAt(0);
            if (req.length() > 1) {
                crain(container);
            } else {
                bfs(container);
            }
        }
        
        // 남은 컨테이너 계산
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (map[i][j] == ' ') continue;
                answer++;
            }
        }
        
        return answer;
    }
    
    private void crain(char target) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (map[i][j] == target) {
                    map[i][j] = ' '; 
                }
            }
        }
    }
    
    private void bfs(char target) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n+2][m+2];
        q.offer(new int[] {0, 0});
        visited[0][0] = true;
        
        List<int[]> out = new ArrayList<>();
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cr = cur[0];
            int cc = cur[1];
            
            for (int i = 0; i < 4; i++) {
                int nr = cr + dr[i];
                int nc = cc + dc[i];
                
                if (nr < 0 || nr >= map.length 
                    || nc < 0 || nc >= map[0].length 
                    || visited[nr][nc]) {
                    continue;
                }
                visited[nr][nc] = true;
                
                if (map[nr][nc] == target && map[cr][cc] == ' ' ) {
                    out.add(new int[] {nr, nc});
                }
                
                if (map[nr][nc] == ' ') {
                    q.offer(new int[] {nr, nc});
                }
                
            }
        }
        
        for (int[] pos : out) {
            map[pos[0]][pos[1]] = ' ';
        }
        
    }
}