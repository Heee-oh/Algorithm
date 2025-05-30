import java.io.*;
import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static int[] topology;
    static List<Integer>[] graph;
    static boolean[] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        topology = new int[n + 1];
        graph = new List[n + 1];
        visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());

            int pre = Integer.parseInt(st.nextToken());

            // 순서 초기화
            for (int j = 1; j < cnt; j++) {
                int next = Integer.parseInt(st.nextToken());
                graph[pre].add(next);

                // 위상정렬
                topology[next]++;
                pre = next;
            }

        }

        for (int i = 1; i <= n; i++) {
            if (topology[i] == 0 && !visited[i]) {
                bfs(i);
            }
        }

        System.out.print(orderCount < n ? 0 : sb.toString());

    }

    static int orderCount = 0;

    private static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty()) {
            int current = q.poll();
            visited[current] = true;
            orderCount++;
            sb.append(current).append("\n");

            List<Integer> list = graph[current];
            for (int next : list) {
                topology[next]--;

                if (topology[next] == 0) {
                    q.add(next);
                }
            }
        }
    }

}
