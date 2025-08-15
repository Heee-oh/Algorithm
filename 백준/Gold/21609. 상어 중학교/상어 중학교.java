import java.beans.Customizer;
import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {-1, 0, 1, 0};

    static int maxCnt, maxRainbowCnt;
    static int[] pos = new int[2]; // 블록 기준 기록

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        // 블록 그룹이 존재하는 동안 계속 반복
        while (true) {

            // 1. 블록 그룹 찾기
            maxCnt = 0;
            maxRainbowCnt = 0;
            boolean[][] visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // 일반 블록이면 그룹 탐색
                    if (map[i][j] > 0 && !visited[i][j]) {
                        bfs(visited, i, j);
                    }
                }
            }

            if (maxCnt <= 1 || maxCnt - maxRainbowCnt == 0) {
                break;
            }

            // 2. 그룹 삭제후 B^2 점 획득
            // 그룹 삭제
            delete(pos[0], pos[1]);
            answer += maxCnt * maxCnt;

            // 중력 작용
            applyGravity();
            // 90도 회전
            rotate();
            // 중력 작용
            applyGravity();
        }

        System.out.println(answer);
    }

    private static void rotate() {
        int[][] tmp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tmp[N - j - 1][i] = map[i][j];
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = tmp[i][j];
            }
        }
    }

    private static void applyGravity() {
        for (int c = 0; c < N; c++) {
            int empty = -1;
            for (int r = N-1; r >= 0; r--) {

                if (empty == -1 && map[r][c] == -2) {
                    empty = r;

                } else if (map[r][c] == -1) {
                    empty = -1;

                } else if (empty != -1 && map[r][c] >= 0) {

                    for (int i = r; i >= 0; i--) {
                        if (map[i][c] == -1) break;
                        if (map[i][c] == -2) continue;

                        map[empty--][c] = map[i][c];
                        map[i][c] = -2;
                    }
                    empty = -1;
                }
            }
        }
    }

    private static void bfs(boolean[][] visited, int r, int c) {
        boolean[][] isRainbow = new boolean[N][N];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c});
        visited[r][c] = true;
        int total = 1, rainbow = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];

                if (nr < 0 || nr >= N
                        || nc < 0 || nc >= N
                        || visited[nr][nc]
                        || map[nr][nc] <= -1
                        || isRainbow[nr][nc]) continue;

                // 일반블록은 방문 체크
                if (map[nr][nc] == map[r][c]) {
                    visited[nr][nc] = true;

                    // 0인 무지개는 따로 추가
                }else if (map[nr][nc] == 0){
                    isRainbow[nr][nc] = true;
                    rainbow++;

                    // 다른 일반 블록일시 넘어감
                } else {
                    continue;
                }

                q.add(new int[]{nr, nc});
                total++;
            }
        }

        // 찾은 그룹이 기존 최대 그룹보다 우선순위가 높은지 확인
//        if (total > maxCnt) {
//            // 1. 크기가 더 큰 그룹을 찾았을 때
//            maxCnt = total;
//            maxRainbowCnt = rainbow;
//            pos[0] = r;
//            pos[1] = c;
//        } else if (total == maxCnt) {
//            // 2. 크기는 같지만, 무지개 블록이 더 많은 그룹을 찾았을 때
//            if (rainbow > maxRainbowCnt) {
//                maxRainbowCnt = rainbow;
//                pos[0] = r;
//                pos[1] = c;
//            } else if (rainbow == maxRainbowCnt) {
//                // 3. 크기와 무지개 블록 수가 모두 같을 때, 기준 블록의 행/열 비교
//                if (r > pos[0]) {
//                    pos[0] = r;
//                    pos[1] = c;
//                } else if (r == pos[0]) {
//                    if (c > pos[1]) {
//                        pos[1] = c;
//                    }
//                }
//            }
//        }


        if (maxCnt == total) {
            if (maxRainbowCnt == rainbow) {
                pos[0] = r;
                pos[1] = c;

            } else if (maxRainbowCnt < rainbow) {
                pos[0] = r;
                pos[1] = c;

                maxRainbowCnt = rainbow;
            }
        } else if (maxCnt < total) {
            pos[0] = r;
            pos[1] = c;
            maxCnt = total;
            maxRainbowCnt = rainbow;
        }

    }

    private static void delete(int r, int c) {
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c});
        int target = map[r][c];
        map[r][c] = -2;


        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];

                if (nr < 0 || nr >= N
                        || nc < 0 || nc >= N
                        || visited[nr][nc]
                        || map[nr][nc] <= -1) continue;

                // 일반블록은 방문 체크
                if (map[nr][nc] == target || map[nr][nc] == 0) {
                    map[nr][nc] = -2;
                    visited[nr][nc] = true;
                    q.add(new int[]{nr, nc});
                }
            }

        }
    }
}

