import java.util.Queue;
import java.util.LinkedList;

class Solution {

    boolean[] visited = new boolean[1000001];
    // x -> y
    // x + n || x * 2 || x * 3
    //bfs 최단 경로 

    public int solution(int x, int y, int n) {
        int answer = bfs(x,y,n);

        return answer;
    }

    private int bfs(int x, int y, int n) {
        Queue<int[]> q = new LinkedList<>();

        if (y % 2 == 0 || y % 3 == 0 || y % n == x || y % n == 0) {
            q.add(new int[]{x, 0});
        }

        while(!q.isEmpty()) {

            int[] current = q.poll();

            if (current[0] > y) continue;

            // y값이면 반환하고 종료
            if (current[0] == y) {
                return current[1];
            }

            if (current[0] + n <= y && !visited[current[0] + n]) {
                q.add(new int[] {current[0] + n, current[1] + 1});
                visited[current[0] + n] = true;
            }
            if (current[0] * 2 <= y && !visited[current[0] * 2]) {
                q.add(new int[] {current[0] * 2, current[1] + 1});
                visited[current[0] * 2] = true;
            }

            if (current[0] * 3 <= y && !visited[current[0] * 3]) {
                q.add(new int[] {current[0] * 3, current[1] + 1});
                visited[current[0] * 3] = true;
            }



        }


        return -1;

    }
}