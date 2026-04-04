import java.util.*;


class Solution {
    static int N, K;
    static int max = 0;
    static List<Integer>[][] trees;
    public int solution(int n, int infection, int[][] edges, int k) {
        N = n;
        K = k;

        trees = new ArrayList[n+1][4];
        
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < 4; j++) {
                trees[i][j] = new ArrayList<>();
            }
        }
        
        // 연결 
        for (int[] edge : edges) {
            
            int x = edge[0];
            int y = edge[1];
            int type = edge[2];
            
            trees[x][type].add(y);
            trees[y][type].add(x);
        }
        
        boolean[] infections = new boolean[n+1];
        infections[infection] = true;
        dfs(infections,  0);
    
        return max;
    }
    
    
    private static void dfs(boolean[] infection, int cnt) {
        
        int size = 0;
        for (int i = 1; i <= N; i++) {
            if (infection[i]) size++;
        }
        
        if (size > max) { 
            max = size;
        }
        
        if (cnt == K) {
            return;
        }
        
        for (int i = 1; i <= 3; i++) {
           boolean[] newInfection = infection.clone();
            
            // 파이프 하나씩 열어보기
            bfs(newInfection, i);
        
            // 다음 사이클 
            dfs(newInfection, cnt + 1);
        }
        
        
    }
    
    private static void bfs(boolean[] newInfection, int type) {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (newInfection[i]) {
                q.offer(i);
            }
        }
        
        
        while (!q.isEmpty()) {
            int cur = q.poll();
            if (trees[cur][type].isEmpty()) continue;
            
            for (int next : trees[cur][type]) {
                if (newInfection[next]) continue;
                newInfection[next] = true;
                q.offer(next);
            }
            
        }
    
    }
}