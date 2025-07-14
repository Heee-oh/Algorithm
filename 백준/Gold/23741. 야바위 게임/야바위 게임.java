import java.io.*;
import java.util.*;

public class Main {
    static int N, M, X, Y;
    static List<Integer>[] graph;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        visited = new boolean[Y + 1][N + 1];

        // 그래프 초기화
        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        bfs();
        for (int i = 1; i < visited[Y].length; i++) {
            if (visited[Y][i]) {
                sb.append(i + " ");
            }
        }


        System.out.print(sb.length() == 0 ? "-1" : sb.toString().trim());
    }

    private static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{X, 0});
        visited[0][X] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int num = cur[0];
            int cnt = cur[1];

            if (cnt >= Y) continue;

            for (int i = 0; i < graph[num].size(); i++) {
                int nextCup = graph[num].get(i);

                if (!visited[cnt + 1][nextCup]) {
                    q.add(new int[]{nextCup, cnt + 1});
                    visited[cnt + 1][nextCup] = true;
                }

            }
        }
    }
}
