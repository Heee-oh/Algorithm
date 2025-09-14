import java.util.*;

class Solution {
    
    // 1 <= n, m
    // 중복 방문 가능, 사전순 빠르게 
    // 탈출 불가시 impossilbe 리턴
    // 이동 길이가 총 k 이여야함
    
    // 사전 순으로 순회
    static int[] dr = {1, 0, 0, -1};
    static int[] dc = {0, -1, 1, 0};
    static String[] dir = {"d", "l", "r", "u"};
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";
        
        Map<Integer, String> map = new HashMap<>();
        
        
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {x,y, 0});
        
        String[][] visited = new String[n+1][m+1];
        
        while(!q.isEmpty()) {
            
            int[] cur = q.poll();
            
            int curR = cur[0];
            int curC = cur[1];
            int dist = cur[2];
            

            
            if (dist == k 
                && (curR == r && curC == c)) {
                
                return map.get(curR * 100 + curC);
            }
            
            if (dist > 2500) {
                
                return "impossible";
            }
            
            String curPath = map.getOrDefault(curR * 100 + curC, "");

            for (int i = 0; i < 4; i++) {
                
                int nr = curR + dr[i];
                int nc = curC + dc[i];
                
                
                if (nr <= 0 || nr > n 
                    || nc <= 0 || nc > m)  continue;
                
                int pos = nr * 100 + nc;
                String nPath = map.getOrDefault(pos, "");
                
                if (!map.containsKey(pos)) {
                    map.put(pos, curPath + dir[i]);
                    q.add(new int[] {nr, nc, dist+1});
                    continue;
                }
                
                // 더 긴것은 일단 담는다. 
                if (curPath.length() > nPath.length()) {
                    map.put(pos, curPath + dir[i]);                    
                    
                    q.add(new int[] {nr, nc, dist+1});
                    
                    // 같다면 사전순 
                } else if (curPath.length() == nPath.length()) {
                    int a = nPath.compareTo(curPath);
                    
                } 

            }
            
            
        }
        
        
        return answer;
    }
    

}