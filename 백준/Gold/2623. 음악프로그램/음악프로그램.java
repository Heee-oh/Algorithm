import java.io.*;
import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static int[] indegree;
    static List<Integer>[] graph;
    static boolean[] visited;
    static int orderCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        indegree = new int[n + 1];
        graph = new List[n + 1];
        visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());

            int pre = Integer.parseInt(st.nextToken());

            // cnt 개의 노드가 주어진 순서대로 연결되므로 간선과 진입 차수 설정
            for (int j = 1; j < cnt; j++) {
                int next = Integer.parseInt(st.nextToken());
                graph[pre].add(next);

                // 위상정렬
                indegree[next]++;
                pre = next;
            }

        }

        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0 && !visited[i]) {
                bfs(i);
            }
        }

        System.out.print(orderCount < n ? 0 : sb.toString());

    }

    private static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty()) {
            int current = q.poll();
            visited[current] = true;

            sb.append(current).append("\n");
            orderCount++;

            List<Integer> list = graph[current];
            for (int next : list) {
                indegree[next]--; // current에서 next로 가는 간선을 제거하므로 next의 진입 차수 감소

                if (indegree[next] == 0) {
                    q.add(next);
                }
            }
        }
    }


}
