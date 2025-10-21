import java.util.*;

class Solution {
    
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    static int[][][] dist;
    static int n;
    
    class Path {
        int r,c,dir, cost;
        
        public Path(int r, int c, int dir, int cost) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.cost = cost;
        }
    }
    
    public int solution(int[][] board) {
        int answer = 0;
        n = board.length;
        
        dist = new int[n][n][4];
        for (int i = 0; i < n; i++) {
            
            for (int j = 0; j  < n; j++) {
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);
                
            }
        }
        
        
        
        bfs(board);
        
        
        
        return Math.min(Math.min(dist[n-1][n-1][0], dist[n-1][n-1][1]),
                    Math.min(dist[n-1][n-1][2], dist[n-1][n-1][3]));
    }
    
    
    private void bfs(int[][] board) {
        PriorityQueue<Path> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        for (int i = 0; i < 4; i++) {
            int nr = dr[i], nc = dc[i];
            if (nr < 0 || nc < 0 || nr >= n || nc >= n || board[nr][nc] == 1) continue;
            dist[nr][nc][i] = 100;
            pq.add(new Path(nr, nc, i, 100));
        }
    
        
        
        while (!pq.isEmpty()) {
            Path cur = pq.poll();
            
            if (cur.r == n - 1 && cur.c == n - 1) continue;
            for (int i = 0; i < 4; i++) {
                if ((cur.dir == 0 && i == 1) || (cur.dir == 1 && i == 0) 
                    || (cur.dir == 2 && i == 3) || (cur.dir == 3 && i == 2))  continue;
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                
                
                if (nr < 0 || nr >= n 
                    || nc < 0 || nc >= n 
                    || board[nr][nc] == 1) continue;
                
                int nCost = cur.cost;
                // 코너일 경우
                if (isConner(cur.dir, i)) {
                    nCost += 600;
                // 아닌경우
                } else {
                    nCost += 100;
                }
                
                
                if (dist[nr][nc][i] > nCost) {
                    dist[nr][nc][i] = nCost;
                    pq.add(new Path(nr, nc, i, nCost));
                    
                } 
                
            }
            
        }
        
    }
   
    
    private boolean isConner(int dir, int nextDir) {
        if (dir == -1) return false;
        
        // 상하
        if (dir < 2 && nextDir >= 2 
            || dir >= 2 && nextDir < 2) {
            
            return true;
        } 
        
        return false;
    }
}