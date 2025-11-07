import java.util.*;

class Solution {
    
    static int[][] otarget;
    static int answer = Integer.MAX_VALUE;
    static int size;
    static int row, col;
    
    public int solution(int[][] beginning, int[][] target) {
        row = target.length;
        col = target[0].length;
        size = row * col;
        otarget = target;
        
        dfs2(beginning, 0, 0);
        
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
    
    private void dfs2(int[][] map, int cnt, int depth) {
        if (cnt >= answer) return;
        if (depth == row + col) {
            if (valid(map)) answer = Math.min(answer, cnt);
            return;
        }

        // 행 부분 탐색
        if (depth < row) {
            // 뒤집지 않음
            dfs2(map, cnt, depth + 1);
            // 해당 행 뒤집음
            int[][] newMap = cloneMap(map);
            switching(newMap, depth, true);
            dfs2(newMap, cnt + 1, depth + 1);
        } 
        // 열 부분 탐색
        else {
            int idx = depth - row;
            // 뒤집지 않음
            dfs2(map, cnt, depth + 1);
            // 해당 열 뒤집음
            int[][] newMap = cloneMap(map);
            switching(newMap, idx, false);
            dfs2(newMap, cnt + 1, depth + 1);
        }
    }
    
    private boolean valid(int[][] map) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (map[i][j] != otarget[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private int[][] cloneMap(int[][] map) {
        int[][] tmp = new int[row][col];
        for (int i = 0; i < row; i++) {
            tmp[i] = map[i].clone();
        }
        
        return tmp;
    }
    
    private void switching(int[][] tmp, int line, boolean isRow) {
        
        for (int i = 0; i < (isRow ? col : row); i++) {
            // 한 행을 바꿈
            if (isRow) {
                tmp[line][i] = (tmp[line][i] + 1) % 2;
                
                // 한 열을 바꿈
            } else {
                tmp[i][line] = (tmp[i][line] + 1) % 2;
            }
        }
        
    }
}