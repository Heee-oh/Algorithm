import java.io.*;
import java.util.*;

public class Main {
    // 아직 집합이 없다면, 정방향, 반대방향으로 탐색해서 집합 번호 부여
    // 큐를 이용하여 탐색
    // 집합 번호가 있거나 범위를 넘어가면 continue

    static int N, M;
    static int[][] set;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int setN = 1;


        set = new int[N][M];
        char[][] map = new char[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            Arrays.fill(set[i], -1);
        }


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (set[i][j] != -1) continue;
                bfs(map, i, j, setN++);
            }
        }

        System.out.println(setN- 1);
    }

    private static void bfs(char[][] map, int i, int j, int setN) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {i, j}); // 정 방향

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];

                if (nr < 0 || nr >= N
                        || nc < 0 || nc >= M
                        || set[nr][nc] != -1) continue;


                int dir = getDirection(map[nr][nc]);

                if (k == getDirection(map[r][c])) {
                    set[nr][nc] = setN;
                    q.add(new int[]{nr, nc});

                } else if (r == nr + dr[dir]
                        && c == nc + dc[dir]) {
                    set[nr][nc] = setN;
                    q.add(new int[]{nr, nc});
                }
            }
        }


    }

    private static int getDirection(char d) {
        switch (d) {
            case 'N' : return 0;
            case 'E' : return 1;
            case 'S' : return 2;
            case 'W' : return 3;
            default: return -1;
        }
    }


}
