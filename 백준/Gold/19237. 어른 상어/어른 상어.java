import java.io.*;
import java.util.*;

public class Main {
    // 19237번 어른 상어

    // 방향: 0:위, 1:아래, 2:왼쪽, 3:오른쪽
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int N, M, K;
    static Shark[] sharks;
    static int[][] smellOwnerMap; // 냄새 주인 상어 번호
    static int[][] smellTimeMap;  // 냄새 남은 시간
    static int[][][] movePriority; // [상어번호][현재방향][우선순위]

    // 상어 정보를 담을 클래스
    static class Shark {
        int id;
        int r, c;
        int dir;
        boolean isAlive = true;

        public Shark(int id, int r, int c) {
            this.id = id;
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        sharks = new Shark[M + 1];
        smellOwnerMap = new int[N][N];
        smellTimeMap = new int[N][N];
        movePriority = new int[M + 1][4][4];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num != 0) {
                    sharks[num] = new Shark(num, i, j);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            sharks[i].dir = Integer.parseInt(st.nextToken()) - 1;
        }

        for (int i = 1; i <= M; i++) {
            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 4; k++) {
                    movePriority[i][j][k] = Integer.parseInt(st.nextToken()) - 1;
                }
            }
        }

        solve();
    }

    static void solve() {
        int time = 0;
        int aliveCount = M;

        while (time <= 1000) {
            if (aliveCount == 1 && sharks[1].isAlive) {
                System.out.println(time);
                return;
            }
            if (time == 1000) break;

            // 1. 현재 위치에 냄새 뿌리기
            spreadSmell();

            // 2. 모든 상어의 다음 위치 결정 (동시 이동을 위해 실제 이동은 아직 안 함)
            int[][] nextMap = new int[N][N];
            for (int i = 1; i <= M; i++) {
                if (!sharks[i].isAlive) continue;
                
                findNextPos(sharks[i]); // 상어의 다음 위치와 방향을 내부적으로 결정
            }

            // 3. 냄새 시간 1씩 감소
            decreaseSmellTime();

            // 4. 상어 이동 및 충돌 처리
            for (int i = 1; i <= M; i++) {
                if (!sharks[i].isAlive) continue;

                int r = sharks[i].r;
                int c = sharks[i].c;
                
                if (nextMap[r][c] == 0) { // 이동할 칸이 비어있으면
                    nextMap[r][c] = i;
                } else { // 다른 상어가 이미 있으면
                    sharks[i].isAlive = false; // 현재 상어는 쫓겨남
                    aliveCount--;
                }
            }
            
            time++;
        }

        System.out.println(-1);
    }
    
    // 각 상어의 다음 위치와 방향을 결정하는 함수
    static void findNextPos(Shark s) {
        // 우선순위 1: 냄새 없는 칸
        for (int i = 0; i < 4; i++) {
            int nextDir = movePriority[s.id][s.dir][i];
            int nr = s.r + dr[nextDir];
            int nc = s.c + dc[nextDir];

            if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                if (smellOwnerMap[nr][nc] == 0) { // 냄새 없는 칸
                    s.r = nr;
                    s.c = nc;
                    s.dir = nextDir;
                    return;
                }
            }
        }

        // 우선순위 2: 자신의 냄새가 있는 칸
        for (int i = 0; i < 4; i++) {
            int nextDir = movePriority[s.id][s.dir][i];
            int nr = s.r + dr[nextDir];
            int nc = s.c + dc[nextDir];
            
            if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                if (smellOwnerMap[nr][nc] == s.id) { // 자신의 냄새
                    s.r = nr;
                    s.c = nc;
                    s.dir = nextDir;
                    return;
                }
            }
        }
    }

    // 현재 살아있는 상어들이 자신의 위치에 냄새를 남김
    static void spreadSmell() {
        for (int i = 1; i <= M; i++) {
            if (sharks[i].isAlive) {
                smellOwnerMap[sharks[i].r][sharks[i].c] = sharks[i].id;
                smellTimeMap[sharks[i].r][sharks[i].c] = K;
            }
        }
    }

    // 맵 전체의 냄새 시간을 1씩 감소
    static void decreaseSmellTime() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (smellTimeMap[i][j] > 0) {
                    smellTimeMap[i][j]--;
                    if (smellTimeMap[i][j] == 0) {
                        smellOwnerMap[i][j] = 0; // 시간이 다 되면 냄새 주인도 없앰
                    }
                }
            }
        }
    }
}