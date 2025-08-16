import java.beans.Customizer;
import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;
    static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};

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

        Queue<int[]> q = new LinkedList<>();

        // N,1  N,2  N-1,1  N-2, 2 초기화
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                q.add(new int[]{N - 1 - i, j});
            }
        }

        // M번 이동
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1; // 방향
            int s = Integer.parseInt(st.nextToken()); // 속력

            boolean[][] isCloud = new boolean[N][N];
            // d방향으로 s칸 이동
            int size = q.size();
            while (size-- > 0) {
                int[] cur = q.poll();

                int nr = (cur[0] + (dr[d] * s) % N + N) % N;
                int nc = (cur[1] + (dc[d] * s) % N + N) % N;

                // 구름 있는 칸에 물 양 + 1 (칸 기록)
                isCloud[nr][nc] = true; // 구름이 이동한 칸 기록
                q.add(new int[]{nr, nc});
                map[nr][nc]++;
            }

            // 구름 있던 물 증가한 칸에 물 복사 버그 시전 (대각 거리 1인 칸 물 있는 바구니 수만큼 증가
            // 이때 경계를 넘어가지 않음
            while (!q.isEmpty()) {
                int[] cur = q.poll();
                int r = cur[0], c = cur[1];

                int cnt = 0;
                for (int j = 1; j < 8; j += 2) {
                    int nr = r + dr[j];
                    int nc = c + dc[j];

                    if (nr < 0 || nr >= N
                            || nc < 0 || nc >= N
                            ||map[nr][nc] <= 0) continue;

                    cnt++;
                }

                map[r][c] += cnt;

            }

            // 물 양 2인 바구니의 모든 칸에 구름 생성( 단, 삭제된 구름칸이 아니여야함)
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (!isCloud[r][c] && map[r][c] >= 2) {
                        q.add(new int[]{r, c});
                        map[r][c] -= 2;
                    }
                }
            }
        }

        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += Arrays.stream(map[i]).sum();
        }

        System.out.println(sum);
    }
}

