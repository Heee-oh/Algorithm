import java.io.*;
import java.util.*;

public class Main {

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int r;
    static int c;

    static int[][] map;
    static boolean[][] visited;
    static int[] airPurifier;
    static Queue<int[]> q = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        map = new int[r][c];
        visited = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 공기 청정기 위치 저장
        airPurifier = new int[2];
        for (int i = 0; i < r; i++) {
            if (map[i][0] == -1) {
                airPurifier[0] = i; // 위
                airPurifier[1] = i + 1; // 아래
                break;
            }
        }


        while (t-- > 0) {
            dustInit();
            bfs();
            runUpAirFilter();
            runDownAirFilter();
        }

        int sum = Arrays.stream(map).mapToInt(x -> Arrays.stream(x).filter(dust -> dust != -1).sum()).sum();

        System.out.println(sum);
    }

    private static void dustInit() {
        visited = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] > 0) {
                    q.add(new int[]{i, j});
                }
            }
        }
    }

    // 이전에 있던 미세먼지에는 전파되지 않는다.
    private static void bfs() {
        int size = q.size();
        int[][] tmp = new int[r][c];
        while (size-- > 0) {
            int[] current = q.poll();
            int dustAmount = map[current[0]][current[1]] / 5; // 확산될 양
            int diffusionCnt = 0; // 확산 개수

            for (int i = 0; i < 4; i++) {
                int nextR = current[0] + dy[i];
                int nextC = current[1] + dx[i];

                if (nextR < 0 || nextR >= r
                        || nextC < 0 || nextC >= c
                         || map[nextR][nextC] == -1) continue;

                diffusionCnt++;
                tmp[nextR][nextC] += dustAmount;
            }

            // (r,c)에 남은 미세먼지의 양은 Arc - Arc/5 * 확산된 방향의 개수
            map[current[0]][current[1]] -= dustAmount * diffusionCnt;
        }


        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] != -1) {
                    map[i][j] += tmp[i][j];
                }
            }
        }
    }

    // 공기청정기를 돌린다.
    private static void runUpAirFilter() {
        int[] airDy = {-1, 0, 1, 0};
        int[] airDx = {0, 1, 0, -1};

        int airPosR = airPurifier[0];
        int airPosC = 0;

        for (int i = 0; i < 4; i++) {
            while (true) {
                airPosR += airDy[i];
                airPosC += airDx[i];

                if (map[airPosR -airDy[i]][airPosC - airDx[i]] == -1) continue;
                // 이전 값에 현재값 넣기
                map[airPosR - airDy[i]][airPosC - airDx[i]] = map[airPosR][airPosC];

                // 공청기값 -1이 이동했다면 0으로
                if (map[airPosR - airDy[i]][airPosC - airDx[i]] == -1) {
                    map[airPosR - airDy[i]][airPosC - airDx[i]] = 0;
                }

                if (airPosR + airDy[i] < 0 || airPosR + airDy[i] > airPurifier[0]
                        || airPosC + airDx[i] >= c || airPosC + airDx[i] < 0) break;

            }
        }
    }


    private static void runDownAirFilter() {
        int[] airDy = {1, 0, -1, 0};
        int[] airDx = {0, 1, 0, -1};

        int airPosR = airPurifier[1];
        int airPosC = 0;

        for (int i = 0; i < 4; i++) {
            while (true) {
                airPosR += airDy[i];
                airPosC += airDx[i];

                if (map[airPosR -airDy[i]][airPosC - airDx[i]] == -1) continue;
                // 이전 값에 현재값 넣기
                map[airPosR - airDy[i]][airPosC - airDx[i]] = map[airPosR][airPosC];

                // 공청기값 -1이 이동했다면 0으로
                if (map[airPosR - airDy[i]][airPosC - airDx[i]] == -1) {
                    map[airPosR - airDy[i]][airPosC - airDx[i]] = 0;
                }

                if (airPosR + airDy[i] >= r || airPosR + airDy[i] < airPurifier[1]
                        || airPosC + airDx[i] >= c || airPosC + airDx[i] < 0) break;

            }
        }
    }
}
