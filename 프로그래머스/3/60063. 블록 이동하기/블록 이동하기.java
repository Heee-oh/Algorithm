import java.util.*;

class Solution {
    
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    static boolean[][][] visited; // [r][c][direction] 0:가로, 1:세로
    static int N;
    
    public int solution(int[][] board) {
        N = board.length;
        visited = new boolean[N][N][2];
        
        return bfs(board);
    }
    
    private int bfs(int[][] board) {
        Queue<int[]> q = new LinkedList<>();
        // {r1, c1, r2, c2, direction, time}
        q.add(new int[]{0, 0, 0, 1, 0, 0});
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int r1 = cur[0], c1 = cur[1];
            int r2 = cur[2], c2 = cur[3];
            int dir = cur[4], time = cur[5];
            
            // 범위 체크
            if (r1 < 0 || c1 < 0 || r1 >= N || c1 >= N ||
                r2 < 0 || c2 < 0 || r2 >= N || c2 >= N) continue;
            
            // 벽 체크
            if (board[r1][c1] == 1 || board[r2][c2] == 1) continue;
            
            // 방문 체크 - 두 점 모두 방문했어야 스킵
            if (visited[r1][c1][dir] && visited[r2][c2][dir]) continue;
            
            // 목표 도달
            if ((r1 == N-1 && c1 == N-1) || (r2 == N-1 && c2 == N-1)) {
                return time;
            }
            
            // 방문 처리
            visited[r1][c1][dir] = true;
            visited[r2][c2][dir] = true;
            
            // 이동 (상하좌우)
            for (int i = 0; i < 4; i++) {
                int nr1 = r1 + dr[i];
                int nc1 = c1 + dc[i];
                int nr2 = r2 + dr[i];
                int nc2 = c2 + dc[i];
                
                q.add(new int[]{nr1, nc1, nr2, nc2, dir, time + 1});
            }
            
            // 회전
            if (dir == 1) { // 세로 상태
                // 좌우로 회전 가능한지 체크
                
                // 왼쪽으로 회전
                if (c1 - 1 >= 0 && board[r1][c1-1] == 0 && board[r2][c2-1] == 0) {
                    // r1을 기준으로 회전
                    q.add(new int[]{r1, c1, r1, c2-1, 0, time + 1});
                    // r2를 기준으로 회전  
                    q.add(new int[]{r2, c1-1, r2, c2, 0, time + 1});
                }
                
                // 오른쪽으로 회전
                if (c1 + 1 < N && board[r1][c1+1] == 0 && board[r2][c2+1] == 0) {
                    // r1을 기준으로 회전
                    q.add(new int[]{r1, c1, r1, c2+1, 0, time + 1});
                    // r2를 기준으로 회전
                    q.add(new int[]{r2, c1+1, r2, c2, 0, time + 1});
                }
                
            } else { // 가로 상태
                // 상하로 회전 가능한지 체크
                
                // 위로 회전
                if (r1 - 1 >= 0 && board[r1-1][c1] == 0 && board[r2-1][c2] == 0) {
                    // c1을 기준으로 회전
                    q.add(new int[]{r1-1, c2, r2, c2, 1, time + 1});
                    // c2를 기준으로 회전
                    q.add(new int[]{r1, c1, r2-1, c1, 1, time + 1});
                }
                
                // 아래로 회전  
                if (r1 + 1 < N && board[r1+1][c1] == 0 && board[r2+1][c2] == 0) {
                    // c1을 기준으로 회전
                    q.add(new int[]{r1+1, c2, r2, c2, 1, time + 1});
                    // c2를 기준으로 회전
                    q.add(new int[]{r1, c1, r2+1, c1, 1, time + 1});
                }
            }
        }
        
        return 0;
    }
}