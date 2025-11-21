import java.util.*;

class Solution {
    public int solution(int n, int[][] lighthouse) {
        int answer = 0;
        
        List<Integer>[] trees = new ArrayList[n+1];
        boolean[] removed = new boolean[n+1];
        int[] degree = new int[n+1];
        
        for (int i = 1; i <= n; i++) {
            trees[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < lighthouse.length; i++) {
            int a = lighthouse[i][0];
            int b = lighthouse[i][1];
            
            trees[a].add(b);
            trees[b].add(a);
            degree[a]++;
            degree[b]++;
        }
        
        // 연결이 1개인 리프노드만 저장
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (degree[i] == 1) {
                q.offer(i);
            }
        }
        
        while (!q.isEmpty()) {
            int leaf = q.poll();
            
            if (removed[leaf]) continue;
            removed[leaf] = true;
            
            for (int i = 0; i < trees[leaf].size(); i++) {
                int parent = trees[leaf].get(i);
                if (!removed[parent]) {
                    removed[parent] = true;
                    answer++;
                    
                    // 부모가 제거됨에 따라 부모에 연결된 자식들도 연결카운트 -1
                    for (int j = 0; j < trees[parent].size(); j++) {
                        int next = trees[parent].get(j);
                        
                        degree[next]--;
                        
                        // 연결이 1개인 리프노드가 됐다면 해당 등대 저장
                        if (degree[next] == 1) {
                            q.offer(next);
                        }
                    }
                    
                    
                }
            }
            
            
        }
        
        
        
        return answer;
    }
}
