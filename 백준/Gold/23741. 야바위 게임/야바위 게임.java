import java.io.*;
import java.util.*;

public class Main {
    static int N, M, X, Y;
    static List<Integer>[] graph;
    static boolean[][] visited; // [섞은 횟수][현재 노드(컵)]

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1][2];

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

        int flag = Y % 2;// 홀, 짝 중 하나로 선택됨
        for (int i = 1; i <= N; i++) {
            if (visited[i][flag]) {
                sb.append(i).append(" ");
            }
        }


        System.out.print(sb.length() == 0 ? "-1" : sb.toString().trim());
    }

    private static void dfs(int cnt, int curCup) {
        if (cnt > Y || visited[cnt][curCup]) {
            return;
        }

        visited[cnt][curCup] = true;

        for (int i = 0; i < graph[curCup].size(); i++) {
            int nextCup = graph[curCup].get(i);
            dfs(cnt+1, nextCup);
        }

    }
    private static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{X, 0});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int num = cur[0];
            int cnt = cur[1];

            if (cnt >= Y) continue;

            int nextDepth = cnt + 1;
            int flag = nextDepth % 2;

            for (int i = 0; i < graph[num].size(); i++) {
                int nextCup = graph[num].get(i);

                if (!visited[nextCup][flag]) {
                    q.add(new int[]{nextCup, nextDepth});
                    visited[nextCup][flag] = true;
                }

            }
        }
    }
}
