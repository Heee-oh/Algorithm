import java.util.*;

class Solution {
    int MOD = 20170805;
    
    static int[][][] dp;
    static int[][] map;
    static int[] dr = {1, 0};
    static int[] dc = {0, 1};
    
    public int solution(int m, int n, int[][] cityMap) {
        int answer = 0;
        dp = new int[m][n][2];
        map = cityMap;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        
        dp[m-1][n-1][0] = dp[m-1][n-1][1] = 1;
        
        dfs(0, 0, 0);
        
        if (dp[0][0][0] != -1) {
            answer += dp[0][0][0];
        }
        if (dp[0][0][1] != -1) {
            answer += dp[0][0][1];
        }
        
        return answer;
    }
    
    private int dfs(int r, int c, int dir) {
        if (dp[r][c][dir] != -1) {
            return dp[r][c][dir];
        }
        
        dp[r][c][dir] = 0;
        
        for (int i = 0; i < 2; i++) {
            
            int nr = r + dr[i];
            int nc = c + dc[i];
            
            if (nr >= dp.length
                || nc >= dp[0].length 
                || map[nr][nc] == 1) continue;
            
            if (map[r][c] == 2) {
                if (i != dir) continue;
                
                dp[r][c][dir] = (dp[r][c][dir] + dfs(nr, nc, i)) % MOD;
                
            } else {
                dp[r][c][dir] = (dp[r][c][dir] + dfs(nr, nc, i)) % MOD;
            }
            
        }
        
        return dp[r][c][dir];
    }
}