import java.util.*;

class Solution {
    // 빨 파 수레 1개씩 존재, 각 도착지점 따로 존재
    // 매 턴마다 반드시 모든 수레를 상하좌우 인접 1칸 이동
    
    // 조건
    // 벽, 격자밖 불가
    // 방문했던 곳 불가
    // 도착하면 고정
    // 동시에 두 수레 같은 칸 이동 불가
    // 수레 끼리 자리 체인지 불가
    
    int[] dr = {1, -1, 0, 0};
    int[] dc = {0, 0, 1, -1};
    int n, m;
    int answer = Integer.MAX_VALUE;
        
    public int solution(int[][] maze) { // 가로 세로 최대 길이 = 4
        int turn = 0;
        n = maze.length; m = maze[0].length;
        
        int[][] pos = new int[4][2]; // redStart, blueStart, redEnd, blueEnd
        boolean[][] rVisited = new boolean[n][m];
        boolean[][] bVisited = new boolean[n][m];
        
        
        // 수레 위치 따로 저장
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                
                if (maze[i][j] % 5 != 0) {
                    pos[maze[i][j] - 1] = new int[] {i,j};
                }
            }
        }
        
        // 빨, 파 수레 방문 처리 
        rVisited[pos[0][0]][pos[0][1]] = true;
        bVisited[pos[1][0]][pos[1][1]] = true;
        
        dfs(maze, pos, rVisited, bVisited, 0);
        
        return answer == Integer.MAX_VALUE ? 0 : answer; // 퍼즐 풀이 가능 ? 최소 턴 : 0
    }
    
    private void dfs(int[][] maze, int[][] pos, boolean[][] rVisited,boolean[][] bVisited, int turn) {
    
        if (turn >= answer) return;
        
        int rr = pos[0][0], rc = pos[0][1];
        int br = pos[1][0], bc = pos[1][1];
        
        List<int[]> nextRList = new ArrayList<>();
        List<int[]> nextBList = new ArrayList<>();
        
        boolean redDone = false, blueDone = false;
        
        // 빨강 수레가 도착했다면 그 자리 그대로 저장
        if (rr == pos[2][0] && rc == pos[2][1]) {
            nextRList.add(new int[] {rr, rc});
            redDone = true;
            
        } else {
            for (int i = 0; i < 4; i++) {
                int nr = rr + dr[i];
                int nc = rc + dc[i];
                if (nr < 0 || nr >= n || nc < 0 || nc >= m 
                    || maze[nr][nc] == 5 || rVisited[nr][nc]) continue;

                nextRList.add(new int[] {nr, nc});
            }
            
            // 다 막혀서 생성 불가시 리턴
            if (nextRList.isEmpty()) return;
        }
        
        if (br == pos[3][0] && bc == pos[3][1]) {
            nextBList.add(new int[] {br, bc});
            blueDone = true;
            
        } else {
            for (int i = 0; i < 4; i++) {
                int nr = br + dr[i];
                int nc = bc + dc[i];
                if (nr < 0 || nr >= n || nc < 0 || nc >= m 
                    || maze[nr][nc] == 5 || bVisited[nr][nc]) continue;

                nextBList.add(new int[] {nr, nc});
            }
            
            if (nextBList.isEmpty()) return;
        }
        
        if (redDone && blueDone) {
            answer = Math.min(answer,  turn);
            return;
        }
        
        
        for (int[] rPos : nextRList) {
            for (int[] bPos : nextBList) {
                if (rPos[0] == bPos[0] && rPos[1] == bPos[1] // 겹침
                    || (rPos[0] == br && rPos[1] == bc &&  bPos[0] == rr && bPos[1] == rc )) continue;
                
                pos[0][0] = rPos[0]; 
                pos[0][1] = rPos[1];
                pos[1][0] = bPos[0];
                pos[1][1] = bPos[1];
                
                if (!redDone) rVisited[rPos[0]][rPos[1]] = true;
                if (!blueDone) bVisited[bPos[0]][bPos[1]] = true;
                
                dfs(maze, pos, rVisited, bVisited, turn + 1);
                
                pos[0][0] = rr; 
                pos[0][1] = rc;
                pos[1][0] = br;
                pos[1][1] = bc;
                
                if (!redDone) rVisited[rPos[0]][rPos[1]] = false;
                if (!blueDone) bVisited[bPos[0]][bPos[1]] = false;
            }
            
        }
        
        
        
        
    }
}