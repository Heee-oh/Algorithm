import java.util.Queue;
import java.util.LinkedList;

class Solution {
    boolean[] visited = new boolean[1000001];
    Queue<int[]> q = new LinkedList<>();
    
    public int solution(int x, int y, int n) {
        int answer = bfs(x,y,n);
        return answer;
    }
    
    private int bfs(int x, int y, int n) {
        q.add(new int[]{x, 0});
        
        while(!q.isEmpty()) {
            int[] current = q.poll();
            // y값이면 반환하고 종료
            if (current[0] == y) return current[1];
            
            checkBound(current[0] + n, y, current[1]);
            checkBound(current[0] * 2, y, current[1]);
            checkBound(current[0] * 3, y, current[1]);
        }
        return -1;
    }
    
    private void checkBound(int value, int y, int count) {
        if (value <= y && !visited[value]) {
            q.add(new int[] {value, count + 1});
            visited[value] = true;
        }
    }
}
