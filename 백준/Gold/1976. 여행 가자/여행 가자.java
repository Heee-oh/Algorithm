import java.io.*;
import java.util.*;

public class Main {
    static boolean[] visited;
    static boolean[][] graph;

    // 방문한 곳을 여행 계획에서 체크하면 된다.
    // 여행계획 섬을 방문하지 않았다면 이는 불가능한 것
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        // 그래프 초기화
        graph = new boolean[n + 1][n + 1];
        visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= n; j++) {
                graph[i][j] = st.nextToken().equals("1");
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] searchList = new int[m];
        for (int i = 0; i < m; i++) {
            searchList[i] = Integer.parseInt(st.nextToken());
        }


        bfs(searchList[0]);
        for (int i = 0; i < m; i++) {
            if (!visited[searchList[i]]) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
    }

    private static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int next = q.poll();

            for (int i = 1; i < graph.length; i++) {
                if (graph[next][i] && !visited[i]) {
                    visited[i] = true;
                    q.add(i);
                }
            }
        }
    }


}
