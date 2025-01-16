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
            if (!visited[i] && k >= dungeons[i][0]) {  
                visited[i] = true;
                dfs(k - dungeons[i][1], dungeons, cnt + 1);
                visited[i] = false;
            }
        }
        
        max = Math.max(cnt, max);
    }
    
}