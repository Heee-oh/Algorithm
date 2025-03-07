import java.io.*;
import java.util.*;

public class Main {

    // 주사위 굴려서 나온 결과 + 현재 위치  >= 100칸 넘어가면 이동 불가
    // 사다리 칸이면 사다리 타고 위로 이동
    // 뱀이면 뱀따라서 내려감
    // BFS를 이용한 최단경로 완전탐색
    static boolean[] visited = new boolean[101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());


        // 전체 맵을 일차원 배열로 본다.
        int[] map = new int[101];
        for (int i = 0; i < n + m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // a->b로 간다.
            map[a] = b;
        }

        System.out.println(bfs(map));



    }

    private static int bfs(int[] map) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {1, 0});
        visited[1] = true;

        while (!q.isEmpty()) {

            int[] curPos = q.poll();

            // 도착했다면 주사위 굴린 수 반환
            if (curPos[0] == 100) {
                return curPos[1];
            }

            // 1~6까지 주사위를 하나씩 굴림
            for (int i = 1; i <= 6; i++) {
                int next = curPos[0] + i;
                if (next > 100) break; // 100을 넘어가면 이동하지 않고 굴리지 않음

                // 주사위를 던져 이동한 곳이 사다리 나 뱀이면 이동
                if (map[next] > 0) {
                    next = map[next];
                }

                // 방문하지 않았다면 이동
                if (!visited[next]) {
                    q.add(new int[] {next, curPos[1] + 1});
                    visited[next] = true;
                }
            }
        }

        return 0;

    }


}
