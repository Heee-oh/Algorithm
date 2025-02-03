import java.util.*;

class Solution {
    // 1. 레버로 이동
    // 2. 출구로 이동 (레버가 당겨져야만 탈출 , 아니라면 지나가는 것은 가능)
    // 한 칸 이동시 1초, 이동 시간 최솟값 구하기
    // 탈출 불가시 -1 반환
    
    // S : 시작, E : 출구, L : 레버, O : 통로, X : 벽
    
    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};
    boolean[][] visited;
    int time = 0;
    
    public int solution(String[] maps) {
        // 레버를 밟으면 방문 기록을 초기화시키면 된다.
        visited  = new boolean[maps.length][maps[0].length()];
        
        // 시작 점 찾기
        for (int i = 0; i < maps.length; i++) {
            if (maps[i].indexOf("S") != -1) { 
                bfs(maps, new int[] {i, maps[i].indexOf("S"), 0}); // r, c, 걸린 시간
                break;
            }
        }
        
        // 시간이 0이면 탈출 불가이므로 -1반환
        return time == 0 ? -1 : time;
    }
    
    
    private void bfs(String[] maps, int[] start) {
        Queue<int[]> q = new LinkedList<>();
        q.add(start);
        
        visited[start[0]][start[1]] = true;
        // 레버 On/Off 여부
        boolean isLeverOn = false;
        
        while (!q.isEmpty()) {
            int[] current = q.poll();
            
            // 레버가 켜졌고 Exit 위치라면 멈춤
            if (isLeverOn && maps[current[0]].charAt(current[1]) == 'E') {
                time = current[2];
                return;
            }
            
            for (int i = 0; i < 4; i++) {
                int nextY = current[0] + dy[i];
                int nextX = current[1] + dx[i];
                
                // 이미 방문했거나 맵 밖으로 나가거나 X인 벽이라면 지나가지 않음
                if (nextY < 0 || nextY >= maps.length 
                    || nextX < 0 || nextX >= maps[0].length() 
                    || visited[nextY][nextX] || maps[nextY].charAt(nextX) == 'X') continue;
                
                
                // 다음 위치가 레버이며, 아직 레버를 당기지 않았다면 레버를 당기고 방문 초기화
                if (maps[nextY].charAt(nextX) == 'L' && !isLeverOn) {
                    visited = new boolean[maps.length][maps[0].length()];
                    isLeverOn = true;
                    q.clear(); // 레버를 당긴 곳부터 시작해야하므로 초기화
                    
                    // 레버 위치만 저장 및 방문처리 후 멈춘다. (이 위치부터 다시 탐색하기 위함)
                    q.add(new int[] {nextY, nextX, current[2] + 1});
                    visited[nextY][nextX] = true;
                    break; 
                }
                
                // 방문 처리 및 다음 노드 큐에 넣기
                q.add(new int[] {nextY, nextX, current[2] + 1});
                visited[nextY][nextX] = true;
            }   
        }
    }
}