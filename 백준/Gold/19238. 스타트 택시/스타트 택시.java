import java.beans.Customizer;
import java.io.*;
import java.util.*;

public class Main {
    // 19238번 스타트 택시
    // 손님 태우기, 최단경로 손님 먼저 (같을 시 작은 행, 같을 시 작은 열) ,
    // 택시와 손님이 같은 위치라면 거리 0

    // 도착하자마자 연료 바닥난것은 성공

    // 연료는 1씩 소모, 도중 연료 바닥시 실패, 못태워도 실패

    static int N, M, fuel;
    static int[][] map, custom, goal;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];
        custom = new int[N + 1][N + 1];
        goal = new int[N + 1][N + 1];

        List<int[]> list = new ArrayList<>();
        list.add(new int[]{0});

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        int texiR = Integer.parseInt(st.nextToken());
        int texiC = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int startR = Integer.parseInt(st.nextToken());
            int startC = Integer.parseInt(st.nextToken());
            int endR = Integer.parseInt(st.nextToken());
            int endC = Integer.parseInt(st.nextToken());

            custom[startR][startC] = i;
            goal[endR][endC] = i;

            list.add(new int[]{endR, endC});
        }

        int answer = -1;

        while (M-- > 0) {
            boolean[][] visited = new boolean[N + 1][N + 1];
            Queue<int[]> q = new LinkedList<>();

            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
                // 경로 비교
                if (o1[2] != o2[2]) return o1[2] - o2[2];
                // 행 비교
                if (o1[0] != o2[0]) return o1[0] - o2[0];
                return o1[1] - o2[1];
            });

            q.add(new int[]{texiR, texiC, 0});
            visited[texiR][texiC] = true;

            while (!q.isEmpty()) {
                int[] cur = q.poll();
                int r = cur[0];
                int c = cur[1];

                if (custom[r][c] > 0
                        && cur[2] <= fuel) {
                    pq.add(new int[]{r, c, cur[2]});
                }

                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];

                    if (nr <= 0 || nr > N
                            || nc <= 0 || nc > N
                            || visited[nr][nc] || map[nr][nc] == 1) {
                        continue;
                    }

                    q.add(new int[]{nr, nc, cur[2] + 1});
                    visited[nr][nc] = true;
                }
            }

            q.clear();

            if (pq.isEmpty()) {
                System.out.println(-1);
                return;
            }

            // 택시 이동
            int[] target = pq.poll();
            texiR = target[0]; texiC = target[1];
            fuel = fuel - target[2];

            visited = new boolean[N + 1][N + 1];
            q.add(new int[]{texiR, texiC, 0});
            visited[texiR][texiC] = true;

            int targetN = custom[texiR][texiC];
            int[] goal2 = list.get(targetN);
            boolean delivered = false; // 성공 플래그
            custom[texiR][texiC] = 0;

            while (!q.isEmpty()) {
                int[] cur = q.poll();
                int r = cur[0];
                int c = cur[1];

                if (goal2[0] == r && goal2[1] == c) {
                    if (cur[2] > fuel) {
                        System.out.println(-1);
                        return;
                    }

                    fuel = (fuel - cur[2]) + cur[2] * 2;
                    texiR = r; texiC = c;
                    answer = fuel;
                    delivered = true;
                    break;
                }

                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];

                    if (nr <= 0 || nr > N
                            || nc <= 0 || nc > N
                            || visited[nr][nc] || map[nr][nc] == 1) {
                        continue;
                    }

                    q.add(new int[]{nr, nc, cur[2]+1});
                    visited[nr][nc] = true;
                }
            }

            if (!delivered) {
                System.out.println(-1);
                return;
            }

            pq.clear();
        }

        System.out.println(answer);

    }


}

