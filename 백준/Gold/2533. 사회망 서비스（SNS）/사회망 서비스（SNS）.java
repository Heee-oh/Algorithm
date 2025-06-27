import java.io.*;
import java.util.*;

public class Main {

    static List<Integer>[] graph;
    static boolean[] visited;
    static int[][] dp; // [n개의 정점, 얼리 아답터 여부 ]

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        visited = new boolean[n + 1];
        graph = new List[n + 1];
        dp = new int[n + 1][2];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            graph[v1].add(v2);
            graph[v2].add(v1);
        }
        visited[1] = true;
        dfs(1);

        System.out.println(Math.min(dp[1][0], dp[1][1]));

    }


    // 켜져있을 시 , 자식노드는 키거나 끌 수 있음
    // 꺼져있을 시 , 자식노드는 무조건 켜야함
    private static void dfs(int node) {
        dp[node][1] = 1;
        for (int next : graph[node]) {
            if (!visited[next]) {
                visited[next] = true;
                dfs(next);

                dp[node][0] += dp[next][1];
                dp[node][1] += Math.min(dp[next][0], dp[next][1]);

            }
        }
    }
}
