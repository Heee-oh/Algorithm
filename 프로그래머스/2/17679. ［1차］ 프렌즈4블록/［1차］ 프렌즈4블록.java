import java.util.*;

class Solution {
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        Queue<int[]> q = new LinkedList<>();
        char[][] map = new char[m][n];
        
        for (int i = 0; i < m; i++) {
            map[i] = board[i].toCharArray();
        }
        
        while(true) {
            // 1. 2x2 탐색
            for (int r = 0; r < m - 1; r++) {
                for (int c = 0; c < n - 1; c++) {
                    if (map[r][c] == '*') continue;
                    
                    // 2x2가 같은 모양일 경우 
                    if (map[r][c] == map[r][c+1]
                        && map[r+1][c] == map[r+1][c+1]
                       && map[r][c] == map[r+1][c]) {
                        q.offer(new int[] {r,c});
                    }
                }
            }            
            
            // 더이상 없다면 정지 
            if (q.isEmpty()) break;
            
            // 2. 2x2 지우기 
            while(!q.isEmpty()) {
                int[] pos = q.poll();
                int r = pos[0];
                int c = pos[1];
                
                for (int i = r; i <= r + 1; i++) {
                    for (int j = c; j <=c + 1; j++) {
                        if (map[i][j] != '*') {
                            answer++;
                        }
                        map[i][j] = '*';
                    }
                }
            }

        
            
            // 3. 블럭 재배치
            for (int c = n - 1; c >= 0; c--) {
                int emptyR = -1;
                
                for (int r = m - 1; r >= 0; r--) {
                    if (map[r][c] == '*') {
                        if (emptyR == -1) {
                            emptyR = r;
                        }
                        
                    } else if (emptyR != -1) {
                        map[emptyR][c] = map[r][c];
                        map[r][c] = '*';
                        
                        // 다음 빈공간을 찾음 
                        while (emptyR >= 0 && map[emptyR][c] != '*') {
                            emptyR--;
                        }
                    }
                }
            }
          
        }

        
        return answer;
    }
}