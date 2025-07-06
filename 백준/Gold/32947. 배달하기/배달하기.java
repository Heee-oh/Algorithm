import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int S, E;
    static int[] cctv;
    static List<Integer>[] graph;
    static boolean[] visited;
    static boolean[] blocked;    // 매번 감시당하면 진입 금지
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        K = Integer.parseInt(br.readLine());
        cctv = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            cctv[i] = Integer.parseInt(st.nextToken());
        }

        // “매번” 감시당하는 정점 찾기
        int[] watchCount = new int[N + 1];
        for (int x : cctv) watchCount[x]++;
        blocked = new boolean[N + 1];
        for (int v = 1; v <= N; v++) {
            if (watchCount[v] == K) {
                blocked[v] = true;
            }
        }
        // 출발점이 막히면 곧장 실패
        if (blocked[S]) {
            System.out.println(-1);
            return;
        }

        // 그래프 초기화
        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        // BFS 준비
        visited = new boolean[N + 1];
        dist = new int[N + 1];
        Arrays.fill(dist, -1);

        Deque<Integer> q = new ArrayDeque<>();
        visited[S] = true;
        dist[S] = 0;
        q.addLast(S);

        while (!q.isEmpty()) {
            int u = q.pollFirst();
            int t = dist[u];
            int tmod = t % K;
            int watching = cctv[tmod];

            // 목적지 도착 & 감시받지 않을 때
            if (u == E && u != watching) {
                System.out.println(t);
                return;
            }

            // 현재 위치가 감시 중이면 1분 대기
            if (u == watching) {
                // 다시 대기열에 넣되 visited 체크는 하지 않음
                dist[u] = t + 1;
                q.addLast(u);
                continue;
            }

            // 인접 정점으로 이동 (감시 여부 관계없이 push, 단 blocked이면 제외)
            for (int v : graph[u]) {
                if (blocked[v] || visited[v]) continue;
                visited[v] = true;
                dist[v] = t + 1;
                q.addLast(v);
            }
        }

        // 못 가면 -1
        System.out.println(-1);
    }
}
