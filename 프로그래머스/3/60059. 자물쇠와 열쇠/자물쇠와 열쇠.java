import java.util.*;

class Solution {
    static int n;
    static int m;
    
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;
        n = lock.length;
        m = key.length;
        
        int[][] map = new int[n*3][n*3];
        
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i+n][j+n] = lock[i][j];
            }
        }
        
        // 필요 키 공간 개수 구하기 
        int needKey = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {                
                if (lock[i][j] == 0) {
                    needKey++;
                } 
            }
        }
        
        // 4방향 돌리면서 각 위치를 슬라이딩 윈도우처럼 대조해보기 
        for (int rot = 0; rot < 4; rot++) {
            
            for (int r = 0; r <= n * 2; r++) {
                for (int c = 0; c <= n * 2; c++) {
                    
                    if (check(r, c, needKey, map, key)) {
                        return true;
                    }
                }
            }
            
            
            key = rotate(key);
        }
        
        
        return answer;
    }
    
    private int[][] rotate(int[][] map) {
        int[][] tmp = new int[m][m];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                tmp[j][m - i - 1] = map[i][j];
            }
        }
        
        return tmp;
    }
    
    
    private boolean check(int a, int b, int needKey, int[][] map, int[][] key) {
        
        int cnt = 0;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < m; c++) {
            int nr = r + a;
            int nc = c + b;
            // map 범위 벗어나면 무시
            if (nr < 0 || nc < 0 || nr >= n * 3 || nc >= n * 3) continue;

                
             if (key[r][c] == 1) {
                    // lock의 중앙 구역(n~2n)에 있는 부분만 판정
                    if (nr >= n && nr < 2 * n && nc >= n && nc < 2 * n) {
                        if (map[nr][nc] == 0) cnt++;
                        else return false; // 돌기끼리 충돌
                    }
                }
                
            }
        }
        
        
        return cnt == needKey;
    }
}