import java.util.*;

class Solution {
    static List<Integer>[] trees;
    static boolean[] visited;
    static long cnt = 0;
    static int[] oa;
    
    public long solution(int[] a, int[][] edges) {
        oa = a;
        
        // 트리 생성
        trees = new ArrayList[a.length];
        for (int i = 0; i < trees.length; i++) {
            trees[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            trees[u].add(v);
            trees[v].add(u);
        }
        
        
        // a의 합이 0인지 검증
        long rs = 0;
        int prev = 0, min = 0, idx = 0;
        boolean isInitAllZero = false; // 전부 0인지 체크
        
        for (int i = 0; i < a.length; i++) {
            if (prev != a[i]) isInitAllZero = true;
            
            // 최소값을 가지는 idx 기록
            if (min > a[i]) {
                min = a[i];
                idx = i;
            }
            
            rs += a[i];
        }
        
        if (!isInitAllZero) return 0;
        // 0이 아니라면 만들기 불가능
        if (rs != 0) return -1;
        
        
        // 최소 값의 트리를 채우는 것을 목표로 최소 값 노드를 기준으로 탐색
        // + 인 애들껄 뺏어서 채워나가는 식
        visited = new boolean[a.length + 1];
        visited[idx] = true;
        dfs(idx);
        
        return cnt;
    }
    
    private long dfs(int idx) {
        long sum = 0;
       
        for (int i = 0; i < trees[idx].size(); i++) {
            int next = trees[idx].get(i);
            
            if (!visited[next]) {
                visited[next] = true;
                sum += dfs(next);
            }
        }
        
        // 현재 노드의 가중치를 0으로 만들고 가중치를 반환
        sum += oa[idx];
        cnt += Math.abs(sum);
        oa[idx] = 0;
        
        return sum;
    }
}