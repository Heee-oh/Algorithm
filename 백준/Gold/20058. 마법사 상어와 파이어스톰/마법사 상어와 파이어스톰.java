import java.beans.Customizer;
import java.io.*;
import java.util.*;

public class Main {

    static int N, Q, size;
    static int[][] map;
    static boolean[][] visited;
    static int[] L;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        Queue<int[]> q = new LinkedList<>();
        size = (int) Math.pow(2, N);
        map = new int[size][size];

        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        L = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();


        // Q번 파이어스톰 시전
        for (int i = 0; i < Q; i++) {

            // 2^L * 2^L 크기의 격자를 나눔

            int dist = (int) Math.pow(2, L[i]);

            for (int r = 0; r < size; r += dist) {
                for (int c = 0; c < size; c += dist) {
                    rotate(dist, r, c);
                }
            }

            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    if (map[j][k] == 0) continue;


                    // 인접한 얼음이 있는칸 3개 미만이면 얼음 탐색
                    // 인접한 칸은 상하좌우
                    int cnt = 0;
                    for (int dir = 0; dir < 4; dir++) {

                        int nr = j + dr[dir];
                        int nc = k + dc[dir];
                        if (!isBoundary(nr, nc)) cnt++;
                    }

                    if (cnt < 3) {
                        q.add(new int[]{j, k});
                    }
                }
            }


            // 인접한 얼음이 있는칸 3개 미만이면 얼음 양 -1
            while (!q.isEmpty()) {
                int[] cur = q.poll();
                map[cur[0]][cur[1]]--;
            }
        }

        int sum = 0; // 총 합
        visited = new boolean[size][size]; // 덩어리 방문 처리 용

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[i][j] != 0) {
                    sum += map[i][j];

                    if (!visited[i][j]) {
                        bfs(i,j);
                    }
                }
            }
        }

        // 남아있는 얼음 A[r][c]의 합
        // 남아있는 얼음 중 가장 큰 덩어리가 차지하는 칸의 개수 (얼음 있고 인접해있다면 연결됨, 해당 집합을 덩어리라 함)
        System.out.println(sum);
        System.out.println(maxBlockSize);

    }

    static int maxBlockSize = 0;

    private static void bfs(int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c});
        visited[r][c] = true;

        int cnt = 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {

                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];

                if (nr >= size || nr < 0
                        || nc >= size || nc < 0
                        || visited[nr][nc]
                        || map[nr][nc] == 0) continue;
                visited[nr][nc] = true;
                q.add(new int[]{nr, nc});
                cnt++;
            }
        }

        maxBlockSize = Math.max(maxBlockSize, cnt);
    }

    private static void rotate(int dist, int r, int c) {
        // 회전시킬 부분 격자를 저장할 임시 배열 생성
        int[][] temp = new int[dist][dist];

        // 1. 기존 map의 부분 격자를 temp 배열에 복사
        for (int i = 0; i < dist; i++) {
            for (int j = 0; j < dist; j++) {
                temp[i][j] = map[r + i][c + j];
            }
        }

        // 2. temp 배열을 90도 회전시켜 원래 map에 덮어쓰기
        // (회전 공식: map[i][j] -> temp[dist-1-j][i])
        for (int i = 0; i < dist; i++) {
            for (int j = 0; j < dist; j++) {
                map[r + i][c + j] = temp[dist - 1 - j][i];
            }
        }
    }

    private static boolean isBoundary(int nr, int nc) {
        return nr >= size || nr < 0 || nc >= size || nc < 0 || map[nr][nc] == 0;
    }

}

