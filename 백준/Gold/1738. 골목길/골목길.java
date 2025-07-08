import java.io.*;
import java.util.*;

public class Main {
    static class Edge {
        int to, w;
        Edge(int to, int w) { this.to = to; this.w = w; }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 정방향 그래프와 역방향 그래프
        List<Edge>[] graph    = new ArrayList[n+1];
        List<Integer>[] rev   = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            rev[i]   = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Edge(v, w));
            rev[v].add(u);
        }

        // 1→n 경로 상에 있는 노드만 사이클 검사 대상이 되도록 표시
        boolean[] canReachEnd = new boolean[n+1];
        Deque<Integer> dq = new ArrayDeque<>();
        canReachEnd[n] = true;
        dq.add(n);
        while (!dq.isEmpty()) {
            int cur = dq.poll();
            for (int prev : rev[cur]) {
                if (!canReachEnd[prev]) {
                    canReachEnd[prev] = true;
                    dq.add(prev);
                }
            }
        }

        // dist: 1번에서 i번까지 얻을 수 있는 최대 이득, parent: 경로 복원을 위한 직전 노드
        long[] dist   = new long[n+1];
        int[]  parent = new int[n+1];
        Arrays.fill(dist, Long.MIN_VALUE);
        dist[1] = 0;
        parent[1] = 1;

        // Bellman–Ford (n-1번 relax)
        for (int i = 0; i < n-1; i++) {
            for (int u = 1; u <= n; u++) {
                if (dist[u] == Long.MIN_VALUE) continue;
                for (Edge e : graph[u]) {
                    long cand = dist[u] + e.w;
                    if (dist[e.to] < cand) {
                        dist[e.to]   = cand;
                        parent[e.to] = u;
                    }
                }
            }
        }

        // 마지막 relax에서 1→n 경로에 사이클이 영향을 주는지 검사
        for (int u = 1; u <= n; u++) {
            if (dist[u] == Long.MIN_VALUE) continue;
            for (Edge e : graph[u]) {
                long cand = dist[u] + e.w;
                if (dist[e.to] < cand && canReachEnd[e.to]) {
                    // n번으로 가는 경로 상에 무한히 증가하는 사이클
                    System.out.println(-1);
                    return;
                }
            }
        }

        // 도달 불가 체크
        if (dist[n] == Long.MIN_VALUE) {
            System.out.println(-1);
            return;
        }

        // 경로 복원
        List<Integer> path = new ArrayList<>();
        int cur = n;
        while (cur != parent[cur]) {
            path.add(cur);
            cur = parent[cur];
        }
        path.add(1);
        Collections.reverse(path);

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int v : path) {
            sb.append(v).append(' ');
        }
        System.out.println(sb.toString().trim());
    }
}
