import java.io.*;
import java.util.*;

public class Main {

    // 오 왼 위 아래
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};

    static int[][] adr = {
            {-1, 0, 1},
            {-1, 0, 1},
            {-1, -1, -1},
            {1, 1, 1}
    };
    static int[][] adc = {
            {1, 1, 1},
            {-1, -1, -1},
            {-1, 0, 1},
            {-1, 0, 1}
    };

    static int[][] map;
    static boolean[][] wallUp;
    static boolean[][] wallRight;

    static int R, C, K, W, answer;
    static List<int[]> heaters = new ArrayList<>();
    static List<int[]> cond = new ArrayList<>();
    static Queue<int[]> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        wallUp = new boolean[R][C];
        wallRight = new boolean[R][C];

        int choco = 0;

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                int value = Integer.parseInt(st.nextToken());

                if (value == 5) {
                    cond.add(new int[]{i, j});
                } else if (value != 0) {
                    heaters.add(new int[]{i, j, value - 1});
                }

            }
        }

        W = Integer.parseInt(br.readLine());
        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int t = Integer.parseInt(st.nextToken());

            if (t==0) wallUp[r][c] = true;
            else wallRight[r][c] = true;
        }

        while (true) {
            // 1. 집에 있는 모든 온풍기에서 바람이 한 번 나옴
            for (int i = 0; i < heaters.size(); i++) {
                int[] heater = heaters.get(i);
                int dir = heater[2];

                int r = heater[0] + dr[dir];
                int c = heater[1] + dc[dir];

                // 처음 온풍기 나오는 쪽은 무조건 가능하므로 바로 계산
                bfs(r, c, dir);

            }

            // 2. 온도 조절
            // 임시 배열 사용할 것
            int[][] tmp = new int[R][C];
            boolean[][] visited = new boolean[R][C];

            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    for (int k = 0; k < 4; k++) {

                        int nr = i + dr[k];
                        int nc = j + dc[k];
                        if (nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc]) continue;

                        // 벽이 아니라면
                        if (check2(i, j, k)) {
                            int diff = Math.abs(map[i][j] - map[nr][nc]) / 4;
                            if (map[i][j] > map[nr][nc]) {
                                tmp[i][j] -= diff;
                                tmp[nr][nc] += diff;
                            } else if (map[i][j] < map[nr][nc]) {
                                tmp[i][j] += diff;
                                tmp[nr][nc] -= diff;
                            }

                            visited[i][j] = true;
                        }
                    }
                }
            }

            // 온도 실제 적용
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    map[i][j] += tmp[i][j];
                }
            }


            // 3. 온도가 1 이상인 가장 바깥쪽 칸의 온도 1씩 감소
            for (int j = 0; j < C; j++) {
                if (map[0][j] != 0) {
                    map[0][j]--;
                }

                if (map[R - 1][j] != 0) {
                    map[R - 1][j]--;
                }
            }

            for (int i = 1; i < R - 1; i++) {
                if (map[i][0] != 0) {
                    map[i][0]--;
                }
                if (map[i][C - 1] != 0) {
                    map[i][C-1]--;
                }
            }

            // 4. 초콜릿 1개 먹기
            choco++;

            if (choco > 100) {
                System.out.println(101);
                return;
            }

            int cnt = 0;
            // 5. 조사하는 모든 칸의 온도가 K이상이면 중단, 아니면 1번부터 다시
            for (int i = 0; i < cond.size(); i++) {
                int[] pos = cond.get(i);
                int r = pos[0];
                int c = pos[1];

                if (map[r][c] >= K) {
                    cnt++;
                }
            }

            // (초콜릿 개수 > 100) ? 101을 출력
            if (cnt == cond.size()) {
                System.out.println(choco);
                return;
            }

        }

    }

    private static void bfs(int startR, int startC, int dir) {
        boolean[][] visited = new boolean[R][C];
        Queue<int[]> q = new LinkedList<>();
        map[startR][startC] += 5;
        visited[startR][startC] = true;
        q.add(new int[]{startR, startC, 5});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1], power = cur[2];

            if (power == 1) continue;

            for (int i = 0; i < 3; i++) {
                int nr = r + adr[dir][i];
                int nc = c + adc[dir][i];

                if (nr < 0 || nr >= R
                        || nc < 0 || nc >= C) {
                    continue;
                }

                if (!visited[nr][nc] && check(r, c, nr, nc, dir)) {
                    visited[nr][nc] = true;
                    map[nr][nc] += power - 1;
                    q.add(new int[]{nr, nc, power - 1});
                }

            }
        }
    }
    private static boolean check2(int r, int c, int dir) {
        // 오른
        if (dir == 0) {
            return !wallRight[r][c];
            // 왼
        } else if (dir == 1) {
            return !wallRight[r][c - 1];
            // 위
        } else if (dir == 2) {
            return !wallUp[r][c];
            // 아래
        } else {
            return !wallUp[r + 1][c];
        }
    }

    private static boolean check(int r, int c, int nr, int nc, int dir) {
        // 오른쪽
        if (dir == 0) {
            if (nr == r - 1 && nc == c + 1) {
                return !wallRight[r - 1][c] && !wallUp[r][c];
            } else if (nr == r + 1 && nc == c + 1) {
                return !wallUp[nr][c] && !wallRight[nr][c];
            }else if (nc == c + 1) {
                return !wallRight[r][c];
            }

            // 왼쪽
        } else if (dir == 1) {
            if (nr == r - 1 && nc == c - 1) {
                return !wallUp[r][c] && !wallRight[nr][nc];
            } else if (nr == r + 1 && nc == c - 1) {
                return !wallUp[r + 1][c] && !wallRight[nr][nc];
            } else if (nc == c - 1) {
                return !wallRight[r][c - 1];
            }
            // 위
        } else if (dir == 2) {
            if (nr == r - 1 && nc == c - 1) {
                return !wallRight[r][c - 1] && !wallUp[r][c - 1];
            } else if (nr == r - 1 && nc == c + 1) {
                return !wallRight[r][c] && !wallUp[r][c + 1];
            } else if (nr == r - 1) {
                return !wallUp[r][c];
            }
            // 아래
        } else {
            if (nr == r + 1 && nc == c - 1) {
                return !wallRight[r][c - 1] && !wallUp[nr][nc];
            } else if (nr == r + 1 && nc == c + 1) {
                return !wallRight[r][c] && !wallUp[nr][nc];
            } else if (nr == r + 1) {
                return !wallUp[nr][c];
            }
        }

        return false;
    }

}

