import java.util.*;

class Solution {
    public int[] solution(int n) {
        List<Integer> list = new ArrayList<>();
        int[] dr = {1, 0, -1};
        int[] dc = {0, 1, -1};
        
        int[][] arr = new int[n][n];
        
        int r = 0, c = 0;
        int num = 1, cnt = n;
        
        arr[r][c] = num++;
        
        
        while (cnt-- > 0) {
            
            for (int d = 0; d < 3; d++) {
                int nr = r;
                int nc = c;
                
                while (true) {
                    nr += dr[d];
                    nc += dc[d];
                    
                    if (nr < 0 || nr >= n 
                        || nc < 0 || nc >= n 
                        || arr[nr][nc] != 0) {
                        
                        r = nr - dr[d];
                        c = nc - dc[d];
                        break;
                    }
                    
                    arr[nr][nc] = num++;
                    
                    
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                list.add(arr[i][j]);
            }
        }
        
        int[] answer = new int[list.size()];
        
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        
    
        return answer;
    }
}