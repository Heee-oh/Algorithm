import java.io.*;
import java.util.*;

public class Main {

    // 동 남 서 북
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static int[] dice = {2, 1, 5, 6, 4, 3};
    static int[][] map;
    static int[] beads;
    static int N, M, K, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        beads = new int[N * N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 점수 총합 구하기

        int answer = 0;
        // K번 실행
        int diceR = 0, diceC = 0, diceDir = 0;
        for (int i = 0; i < K; i++) {

            // 1. 이동방향 한 칸 이동, 막히면 반대로 한 칸 이동
            int nr = diceR + dr[diceDir];
            int nc = diceC + dc[diceDir];

            if (nr < 0 || nr >= N
                    || nc < 0 || nc >= M) {
                diceDir = (diceDir + 2) % 4; // 방향 반대로 전환
                nr = diceR + dr[diceDir];
                nc = diceC + dc[diceDir];
            }

            // 주사위 이동
            movedDice(diceDir);

            // 2. 도착 칸(x,y)에 점수를 획득
            // 2-1. 이 공식은 따로있음
            answer += bfs(nr, nc);

            // 3. 주사위 아랫면 있는 정수 A와  칸(x,y)에 있는 정수 B를 비교해 이동방향 결정
            // 3-1. A > B = 90도 시계
            // 3-2. A < B = 90 반시계
            // 3-3. A == B = X
            int A = dice[3];
            int B = map[nr][nc];

            // 90도 시계방향
            if (A > B) {
//                rotateDice(true);
                diceDir = (diceDir + 1) % 4;
                // 90도 반시계 방향
            } else if (A < B) {
//                rotateDice(false);
                diceDir = (diceDir + 3) % 4;
            }

            diceR = nr;
            diceC = nc;

        }

        System.out.println(answer);

    }

    private static void movedDice(int dir) {
        // 동쪽
        if (dir == 0) {
            int tmp = dice[1];
            dice[1] = dice[4];
            dice[4] = dice[3];
            dice[3] = dice[5];
            dice[5] = tmp;

            // 남쪽
        } else if (dir == 1) {
            int tmp = dice[0];
            dice[0] = dice[3];
            dice[3] = dice[2];
            dice[2] = dice[1];
            dice[1] = tmp;

            // 서쪽
        } else if (dir == 2) {
            int tmp = dice[4];
            dice[4] = dice[1];
            dice[1] = dice[5];
            dice[5] = dice[3];
            dice[3] = tmp;
            // 북쪽
        } else {
            int tmp = dice[0];
            dice[0] = dice[1];
            dice[1] = dice[2];
            dice[2] = dice[3];
            dice[3] = tmp;
        }
    }

    private static int bfs(int nr, int nc) {
        boolean[][] visited = new boolean[N][M];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{nr, nc});
        visited[nr][nc] = true;
        int cnt = 1;

        int B = map[nr][nc];

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int j = 0; j < 4; j++) {
                int tmpR = cur[0] + dr[j];
                int tmpC = cur[1] + dc[j];

                if (tmpR < 0 || tmpR >= N
                        || tmpC < 0 || tmpC >= M
                        || visited[tmpR][tmpC]
                        || map[tmpR][tmpC] != B) { // 정수 B가 있어야함
                    continue;
                }

                q.add(new int[]{tmpR, tmpC});
                visited[tmpR][tmpC] = true;
                cnt++;
            }
        }



        return cnt * B;
    }


}

