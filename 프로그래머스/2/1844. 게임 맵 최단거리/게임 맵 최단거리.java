import java.util.*;

class Solution {
    static int[] dxarr = {0, 0, 1, -1};
    static int[] dyarr = {1, -1, 0, 0};
    
    public int solution(int[][] maps) {
        boolean[][] visited = new boolean[maps.length][maps[0].length];
        int answer = bfs(visited, maps);
        return answer;
    }
    
    public int bfs(boolean[][] visited, int[][] map) {
        Queue<int[]> q = new LinkedList<>();
        visited[0][0] = true;
        q.add(new int[]{0,0});
        
        while(!q.isEmpty()) {
            int[] coord = q.poll();
            
            for(int i = 0; i < 4; i++) {
                int dx = coord[1] + dxarr[i]; // 열
                int dy = coord[0] + dyarr[i]; // 행

                if (dx >= 0 && dx < map[0].length && dy >= 0 && dy < map.length) {
                    if(!visited[dy][dx] && map[dy][dx] >= 1) {
                        map[dy][dx]+= map[coord[0]][coord[1]];
                        q.add(new int[]{dy, dx});
                        visited[dy][dx] = true;
                    }
                } 
            }
        }
        
        int endPoint = map[map.length - 1][map[0].length - 1];
        return  endPoint == 1 ? -1 : endPoint;
    }
}