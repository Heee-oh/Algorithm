import java.io.*;
import java.util.*;

public class Main {

    // 14503번 로봇 청소기

    static int N, M;
    // 북 동 남 서
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    static int[][] map;
    static boolean[][] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 1) {
                    visited[i][j] = true;
                }
            }
        }


        System.out.println(bfs(r,c,d));

    }

    private static int bfs(int r, int c, int d) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c, d});
        int cnt = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curR = cur[0];
            int curC = cur[1];
            int curD = cur[2];


            // 현재 칸 청소 (안되어있다면)
            if (!visited[curR][curC]) {
                visited[curR][curC] = true;
                cnt++;
            }

            // 4방향 체크
            int cleanRoomCnt = 0;
            for (int i = 0; i < 4; i++) {
                int nextR = curR + dr[i];
                int nextC = curC + dc[i];

                if (visited[nextR][nextC]) {
                    cleanRoomCnt++;
                }
            }

            // 전부 청소되어있다면 후진 가능한지 (불가능시 청소 종료)
            if (cleanRoomCnt == 4) {
                int dir = getDirection(curD, true);
                int backR = curR + dr[dir];
                int backC = curC + dc[dir];

                // 후진한 곳이 벽이라면 종료
                if (map[backR][backC] == 1) {
                    return cnt;
                }

                q.offer(new int[]{backR, backC, curD});

                // 청소되지 않은 곳이 있다면 반시계방향 90도 회전 후 전진
            } else {
                int dir = getDirection(curD, false);
                int newR = curR + dr[dir];
                int newC = curC + dc[dir];

                // 90도 회전 후 청소하지 않았다면 이동
                if (!visited[newR][newC]) {
                    q.offer(new int[]{newR, newC, dir});

                    // 회전한 곳이 청소되어있다면 다시 90도 회전하기위해 큐에 삽입
                } else {
                    q.offer(new int[]{curR, curC, dir});
                }
            }



        }

        return cnt;
    }

    private static int getDirection(int d, boolean isBack) {
        if (isBack) {
            return (d + 2) % 4;
        }
        return (d - 1 + 4) % 4;
    }
}
