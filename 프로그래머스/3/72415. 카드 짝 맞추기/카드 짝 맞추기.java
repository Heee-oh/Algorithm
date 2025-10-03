import java.util.*;

class Solution {
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    static Map<Integer, List<int[]>> map;
    static List<Integer> character;
    static boolean[] check;
    static int[][] oboard;
    
    static int min = Integer.MAX_VALUE;
    
    public int solution(int[][] board, int r, int c) {
        character = new ArrayList<>();
        map = new HashMap<>();
        oboard = board;
        
        int answer = 0;
        
        // 캐릭터별 위치 초기화
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                
                if (board[i][j] != 0) {
                    List<int[]> list = map.getOrDefault(board[i][j], new ArrayList<>());
                    list.add(new int[] {i, j});
                    map.put(board[i][j], list);
                }
            }
        }
        
        for (int key : map.keySet()) {
            character.add(key);
        }

        check = new boolean[character.size()];
        
        dfs(r, c, 0, 0);
        
        return min;
    }
    
    private void dfs(int r, int c, int sum, int depth) {
        
        // 전부 다 탐색했다면 비교후 종료
        if (depth == character.size()) {
            min = Math.min(min, sum);
            return;
        }
        
        for (int i = 0; i < character.size(); i++) {
            if (!check[i]) {
                    
                int num = character.get(i);
                List<int[]> pos = map.get(num);
                int[] a = pos.get(0);
                int[] b = pos.get(1);
                
                int cnt1 = bfs(r, c, a[0], a[1]) + bfs(a[0], a[1], b[0], b[1]) + 2; // Enter 2번 따로 더함
                int cnt2 = bfs(r, c, b[0], b[1]) + bfs(b[0], b[1], a[0], a[1]) + 2;

                oboard[a[0]][a[1]] = 0;
                oboard[b[0]][b[1]] = 0;
                check[i] = true;
                    dfs(b[0], b[1], sum + cnt1, depth + 1);
                    dfs(a[0], a[1], sum + cnt2, depth + 1);

                check[i] = false;
                oboard[a[0]][a[1]] = num;
                oboard[b[0]][b[1]] = num;
                
            }
        }
    }
    
    private int bfs(int r, int c, int tr, int tc) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[4][4];
        
        q.add(new int[] {r, c, 0});
        visited[r][c] = true;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            
            if (cur[0] == tr 
                && cur[1] == tc) {
                
                return cur[2];
            }
            
            
            for (int i = 0; i < 4; i++) {
                
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                
                if (nr < 0 || nr >= 4 
                    || nc < 0 || nc >= 4 
                    || visited[nr][nc]) continue;
                
                visited[nr][nc] = true;
                q.add(new int[] {nr, nc, cur[2] + 1});
                
                
                while (true) {
                    
                    nr += dr[i];
                    nc += dc[i];
                    
                    if (nr < 0 || nr >= 4 
                        || nc < 0 || nc >= 4) {
                        nr -= dr[i];
                        nc -= dc[i];
                        
                        if (visited[nr][nc]) {
                            break;
                        }
                        
                        q.add(new int[] {nr, nc, cur[2] + 1});
                        visited[nr][nc] = true;
                        break;
                    }
                    
                    if (oboard[nr][nc] != 0 && !visited[nr][nc]) {
                        q.add(new int[] {nr, nc, cur[2] + 1});
                        visited[nr][nc] = true;
                        break;
                    }
                }
            }
        }
        
        
        
        return 0;
    }
}