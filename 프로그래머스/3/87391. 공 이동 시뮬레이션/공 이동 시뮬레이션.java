class Solution {
    public long solution(int n, int m, int x, int y, int[][] queries) {
        long answer = -1;
        
        // 정답 범위 설정
        // r1 c1 r2 c2
        int r1,r2,c1,c2;
        r1 = r2 = x;
        c1 = c2 = y;
        
        for (int i = queries.length - 1; i >= 0; i--) {
            int dir = queries[i][0];
            int dx = queries[i][1];
            
            // 범위 엇나감 방지
            if (r1 > r2 || c1 > c2) return 0;
            
            // 각 방향별 범위 넓히기
            if (dir == 0) {
                if (c1 != 0) c1 += dx;
                c2 += dx;
                
            } else if (dir == 1) {
                c1 -= dx;
                if (c2 != m - 1) c2 -= dx;
                
            } else if (dir == 2) {
                if (r1 != 0) r1 += dx;
                r2 += dx;
                
            } else {
                r1 -= dx;
                if (r2 != n - 1) r2 -= dx;
            }
            
            // 격자 넘어감 방지 
            r1 = Math.max(r1, 0);
            c1 = Math.max(c1, 0);
            r2 = Math.min(r2, n - 1);
            c2 = Math.min(c2, m - 1);
            
        }
        

        answer = (long) (r2 - r1 + 1) * (long)(c2 - c1 + 1);
        return answer;
    }
}