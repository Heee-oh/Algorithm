class Solution {
    
    boolean[] visited;
    public int solution(int n, int[][] computers) {
        visited = new boolean[n + 1];
        int answer = 0;
        
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i, computers);
                answer++;
            }
        }
        return answer;
    }
    
    private void dfs(int start, int[][] computers) {
        
        for (int i = 0; i < computers.length; i++) {
            if (!visited[i] && computers[start][i] == 1) {
                visited[i] = true;
                dfs(i, computers);
            }
        }
    }
}