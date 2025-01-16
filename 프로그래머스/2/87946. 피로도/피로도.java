import java.util.Arrays;

class Solution {
    // 현재 피로도 (k), [최소 필요 피로도, 소모 피로도]
    // 최필피 >= 소피
    // 자연수
    // 백트래킹 완전탐색 
    boolean[] visited;
    int max = 0;
    
    public int solution(int k, int[][] dungeons) {        
        visited = new boolean[dungeons.length];
        dfs(k, dungeons, 0);
        return max;
    }
    
    private void dfs(int k, int[][] dungeons, int cnt) {
        for (int i = 0; i < dungeons.length; i++) {
            
            // 방문하지 않았고, 현재 피로도가 던전 최소 필요 피로도보다 같거나 클때
            if (!visited[i] && k >= dungeons[i][0]) {  
                visited[i] = true;
                dfs(k - dungeons[i][1], dungeons, cnt + 1);
                visited[i] = false;
            }
        }
        
        max = Math.max(cnt, max);
    }
    
}