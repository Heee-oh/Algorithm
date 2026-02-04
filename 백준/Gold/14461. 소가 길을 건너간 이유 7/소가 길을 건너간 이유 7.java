import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    // 풀 먹는시간 합산도 long
    // 집 도착해도 3번째라면 풀을 먹음

    static class Road {
        int r, c, cnt;
        long time;

        public Road(int r, int c, int cnt, long time) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.time = time;
        }
    }
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long T = Long.parseLong(st.nextToken());

        long[][][] dist = new long[4][N][N]; // 풀을 먹은 횟수, r, c
        int[][] map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(dist[i][j], Long.MAX_VALUE);

            }
        }


        PriorityQueue<Road> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1.time,o2.time));

        pq.add(new Road(0, 0, 0, 0));
        dist[0][0][0] = 0;

        while (!pq.isEmpty()) {
            Road cur = pq.poll();
            int cnt = cur.cnt;


            if (cur.r == N - 1 && cur.c == N - 1) {
                System.out.println(cur.time);
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                long nTime = cur.time + T;


                if ((cnt + 1) == 3) {
                    nTime += map[nr][nc];
                }

                if (dist[cnt + 1][nr][nc] > nTime) {
                    dist[cnt + 1][nr][nc] = nTime;
                    pq.offer(new Road(nr, nc, (cnt + 1) == 3 ? 0 : cnt + 1, nTime));
                }
            }
        }
    }
}
