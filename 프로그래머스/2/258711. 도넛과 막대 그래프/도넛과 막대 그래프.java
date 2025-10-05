import java.util.*;

class Solution {
    
    class Node {
        
        List<Integer> in;
        List<Integer> out;
        
        public Node() {
            in = new ArrayList<>();
            out = new ArrayList<>();
        }
    }
    
    static Node[] arr;
    static boolean[] visited;
    static int[] answer = new int[4];
    
    public int[] solution(int[][] edges) {
        int max = 0;
        
        for (int i = 0; i < edges.length; i++) {
            max = Math.max(max, Math.max(edges[i][0], edges[i][1]));
        }
        arr = new Node[max + 1];
        
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Node();
        }
        
        for (int i = 0; i < edges.length; i++) {
            int a = edges[i][0];
            int b = edges[i][1];

            arr[a].out.add(b);
            arr[b].in.add(a);
        }

        
                
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].out.size() >= 2 && arr[i].in.isEmpty()) {
                answer[0] = i;
                break;
            }
        }
        
        visited = new boolean[max+1];
        List<Integer> out = arr[answer[0]].out;
        visited[answer[0]] = true;
        
        for (int num : out) {
            dfs(num);
            
        }
        
        return answer;
    }
    
    
    private void dfs(int num) {
        
        if (arr[num].in.size() >= 2 && arr[num].out.size() == 2) {
            answer[3]++;
            return;
        }
        
        if (arr[num].out.isEmpty()) {
            answer[2]++;
            return;
        }
        
        if (visited[arr[num].out.get(0)]) {
            answer[1]++;
            return;
        }
        
        for (int next : arr[num].out) {
            visited[next] = true;
            dfs(next);
        }
        
        return;
        
    }
    
}