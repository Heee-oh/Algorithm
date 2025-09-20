class Solution {
    
    static int[] oinfo;
    static int[][] oedges;
    
    static int max = 0;
    
    public int solution(int[] info, int[][] edges) {
        oinfo = info;
        oedges = edges;
        boolean[] visited = new boolean[info.length];
        
        dfs(visited, 0, 0, 0);
        return max;
    }
    
    private void dfs(boolean[] visited, int idx, int sheep, int wolf) {
        visited[idx] = true;
        
        if (oinfo[idx] == 0) {
            sheep++;
            
            if (sheep > max) {
                max = sheep;   
            }
        } else {
            wolf++;
        }
        
        
        if (sheep <= wolf) {
            return;
        }
        
        for (int i = 0; i < oedges.length; i++) {
            int[] v = oedges[i];
            // 부모는 방문, 자식은 방문하지 않음이면 길 확장
            if (visited[v[0]] && !visited[v[1]]) {
                boolean[] newVisited = visited.clone();
                
                dfs(newVisited, v[1], sheep, wolf);
            }
            
            
        }
        
        
    
    }
}