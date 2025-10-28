import java.util.*;

class Solution {
    private static int cnt;
    private static List<Integer>[] winGraph;
    private static List<Integer>[] defeatGraph;
    
    public int solution(int n, int[][] results) {
        int answer = 0;
        
        // 이긴 그래프, 지는 그래프 생성
        winGraph = new List[n+1];
        defeatGraph = new List[n+1];
        
        for (int i = 0; i <= n; i++) {
            winGraph[i] = new ArrayList<>();
            defeatGraph[i] = new ArrayList<>();
        }
        
        
        for (int[] res : results) {
            int win = res[0];
            int defeat = res[1];
            
            // 나에게 진사람을 리스트에 저장
            defeatGraph[win].add(defeat);
            // 현재 나를 이긴 사람을 리스트에 저장 
            winGraph[defeat].add(win); 
        }
        
        for (int i = 1; i <= n; i++) {
            cnt = 0;
            boolean[] visited = new boolean[n+1];
            upDfs(i, visited);
            downDfs(i, visited);
            if (cnt == n - 1 ) {
                answer++;
            }
        }
        
        return answer;
    }
    
    private void upDfs(int cur, boolean[] visited) {
       
        // 상위 탐색 (현재 나를 이긴 위의 랭커들 탐색)
        for (int i = 0; i < winGraph[cur].size(); i++) {
            int next = winGraph[cur].get(i);
            
            if (!visited[next]) {
                visited[next] = true;
                cnt++;
                upDfs(next, visited);
            }
        }
    }
    
    private void downDfs(int cur, boolean[] visited) {
        for (int i = 0; i < defeatGraph[cur].size(); i++) {
            int next = defeatGraph[cur].get(i);
            
            if (!visited[next]) {
                visited[next] = true;
                cnt++;
                downDfs(next, visited);
            }
        }
    }
    
    
}