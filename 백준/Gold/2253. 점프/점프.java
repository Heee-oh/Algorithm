import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] dist;          // dist[pos][lastJump] = 최소 점프 횟수
    static boolean[] small;       // 작은 돌 여부
    static int MAXJ;              // lastJump 상한

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        small = new boolean[N + 1];
        for (int i = 0; i < M; i++) {
            int idx = Integer.parseInt(br.readLine());
            if (idx >= 1 && idx <= N) small[idx] = true;
        }

        // 즉시 판정 가능한 예외
        if (N == 1) { System.out.println(0); return; }
        if (small[2]) { System.out.println(-1); return; }  // 첫 착지 불가능

        // lastJump 상한: √(2N) + 여유
        MAXJ = (int) Math.sqrt(2 * N) + 2;

        dist = new int[N + 1][MAXJ + 1];
        for (int i = 0; i <= N; i++) Arrays.fill(dist[i], -1);

        bfs();

        int ans = Integer.MAX_VALUE;
        for (int j = 1; j <= MAXJ; j++) {
            if (dist[N][j] != -1) ans = Math.min(ans, dist[N][j]);
        }
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    static void bfs() {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        dist[2][1] = 1;                  // 1→2로 첫 점프
        q.add(new int[]{2, 1});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int pos = cur[0];
            int k = cur[1];

            if (pos == N) return;        // BFS 특성상 최초 도달이 최소

            // k-1
            int nk = k - 1, np = pos + nk;
            if (nk >= 1 && nk <= MAXJ && np <= N && !small[np] && dist[np][nk] == -1) {
                dist[np][nk] = dist[pos][k] + 1;
                q.add(new int[]{np, nk});
            }

            // k
            nk = k; np = pos + nk;
            if (nk >= 1 && nk <= MAXJ && np <= N && !small[np] && dist[np][nk] == -1) {
                dist[np][nk] = dist[pos][k] + 1;
                q.add(new int[]{np, nk});
            }

            // k+1
            nk = k + 1; np = pos + nk;
            if (nk >= 1 && nk <= MAXJ && np <= N && !small[np] && dist[np][nk] == -1) {
                dist[np][nk] = dist[pos][k] + 1;
                q.add(new int[]{np, nk});
            }
        }
    }
}
