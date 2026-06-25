import java.util.*;

class Solution {
    // 0초 동시 출발
    // r, c 둘 중 하나 이동
    /// r 먼저 이동 가능
    
    class Point {
        int r, c;
        
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    public int solution(int[][] points, int[][] routes) {
        Point[] arr = new Point[points.length + 1];
        int answer = 0;
        
        // 각 지점을 생성
        for (int i = 0; i < points.length; i++) {      
            int r = points[i][0];
            int c = points[i][1];
            arr[i + 1] = new Point(r, c);
        }
        
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < routes.length; i++) {
            int route = routes[i][0];
            // 루트 번호, 현재 진행중인 루트, 현재 위치 
            q.offer(new int[] {i, 1, arr[route].r, arr[route].c}); 
            
        }
        
        
        while (!q.isEmpty()) {
            int[][] map = new int[101][101];
            int size = q.size();
            
            while (size--> 0) {
                int[] cur = q.poll();
                int routeNum = cur[0];
                int route = cur[1];
                int r = cur[2];
                int c = cur[3];
                map[r][c]++;

                // 해당 로봇이 모든 루트를 이동했다면 종료
                if (routes[routeNum].length == route) continue;

                int selectedRoute = routes[routeNum][route];
                Point target = arr[selectedRoute];

                // r 먼저 이동
                if (target.r != r) {
                    if (target.r < r) {
                        r--;
                    } else {
                        r++;
                    }


                    // c 이동
                } else if (target.c != c) {
                    if (target.c < c) {
                        c--;
                    } else {
                        c++;
                    }
                } 

                // 현재 선택한 루트에 도달했다면 
                if (target.r == r && target.c == c) {
                    route++;
                }
                
                q.offer(new int[] {routeNum, route, r, c});
            }
            
            // 매 초마다 충돌 체크 
            for (int i = 1; i < map.length; i++) {
                for (int j = 1; j < map[0].length; j++) {
                    if (map[i][j] > 1) answer++;
                }
            }
        }
        
        
        
        
        return answer;
    }
}