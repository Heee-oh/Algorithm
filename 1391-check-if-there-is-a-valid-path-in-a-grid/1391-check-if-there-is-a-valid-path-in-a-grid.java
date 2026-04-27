class Solution {
    // 왼 위 오 아래 = 0, 1, 2, 3
    int[] dr = {0, -1, 0, 1};
    int[] dc = {-1, 0, 1, 0};

    int[][] dir = {
        {}, 
        {0,2},{1,3},{0,3},
        {2,3},{0,1},{1,2}
        };

    public boolean hasValidPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        boolean[][] visited = new boolean[m][n];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {0, 0});
        
        visited[0][0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];

            if (r == m - 1 && c == n - 1) {
                return true;
            }

            int type = grid[r][c];
            
            for (int d : dir[type]) {
                
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n 
                    || visited[nr][nc]) continue;
                

                int nextType = grid[nr][nc];
                
                for (int nd : dir[nextType]) {
                    if ((d + 2) % 4 == nd) {
                        q.offer(new int[] {nr, nc});
                        visited[nr][nc] = true;
                    }
                }
            }
        }

        return false;
    }
}