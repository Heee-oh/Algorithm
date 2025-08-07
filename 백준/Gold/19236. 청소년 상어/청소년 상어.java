import java.io.*;
import java.util.*;

public class Main {

    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1}; // ↑, ↖, ←, ↙, ↓, ↘, →, ↗
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int maxScore = 0;

    // Fish 클래스는 물고기의 모든 정보를 담도록 수정 (위치, 생존 여부 등)
    static class Fish {
        int num, y, x, dir;
        boolean isAlive = true;

        public Fish(int num, int y, int x, int dir) {
            this.num = num;
            this.y = y;
            this.x = x;
            this.dir = dir;
        }

        // 객체 자체를 새로 만드는 '깊은 복사'용 생성자
        public Fish(Fish original) {
            this.num = original.num;
            this.y = original.y;
            this.x = original.x;
            this.dir = original.dir;
            this.isAlive = original.isAlive;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] map = new int[4][4];
        Fish[] fishes = new Fish[17]; // 1번부터 16번 물고기 정보 저장

        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken()) - 1;
                map[i][j] = num;
                fishes[num] = new Fish(num, i, j, dir);
            }
        }

        // 상어 시작: (0, 0) 물고기를 먹음
        int startFishNum = map[0][0];
        Fish startFish = fishes[startFishNum];
        int sharkY = 0, sharkX = 0, sharkDir = startFish.dir;
        int initialScore = startFishNum;

        startFish.isAlive = false; // 먹힌 물고기 처리
        map[0][0] = -1; // 상어 위치는 -1로 표시

        dfs(sharkY, sharkX, sharkDir, initialScore, map, fishes);

        System.out.println(maxScore);
    }

    public static void dfs(int sharkY, int sharkX, int sharkDir, int score, int[][] map, Fish[] fishes) {
        maxScore = Math.max(maxScore, score);

        // --- 핵심: 깊은 복사 (Deep Copy) ---
        // 재귀 호출마다 독립적인 상태를 갖도록 map과 fishes를 완전히 새로 복사합니다.
        int[][] mapCopy = new int[4][4];
        for (int i = 0; i < 4; i++) {
            System.arraycopy(map[i], 0, mapCopy[i], 0, 4);
        }

        Fish[] fishesCopy = new Fish[17];
        for (int i = 1; i <= 16; i++) {
            // Fish 객체 자체를 새로 생성하여 복사합니다.
            fishesCopy[i] = new Fish(fishes[i]);
        }

        // 1. 물고기 이동 (복사된 상태에 적용)
        moveFishes(mapCopy, fishesCopy);

        // 2. 상어 이동 (물고기가 이동한 후의 복사본 위에서)
        for (int i = 1; i < 4; i++) {
            int nextY = sharkY + dy[sharkDir] * i;
            int nextX = sharkX + dx[sharkDir] * i;

            if (nextY >= 0 && nextY < 4 && nextX >= 0 && nextX < 4) {
                if (mapCopy[nextY][nextX] > 0) { // 물고기가 있는 칸으로만 이동
                    
                    // 다음 재귀에 넘겨줄 새로운 상태를 만듭니다.
                    int eatenFishNum = mapCopy[nextY][nextX];
                    Fish eatenFish = fishesCopy[eatenFishNum];
                    
                    int nextSharkDir = eatenFish.dir;
                    int newScore = score + eatenFish.num;

                    eatenFish.isAlive = false;
                    mapCopy[sharkY][sharkX] = 0;
                    mapCopy[nextY][nextX] = -1;

                    // 이 새로운 상태를 가지고 다음 탐색을 떠납니다.
                    dfs(nextY, nextX, nextSharkDir, newScore, mapCopy, fishesCopy);
                    
                    // 재귀에서 돌아온 후, 이 루프의 다음 반복을 위해 상태를 원상 복구합니다.
                    // (이전 재귀 호출에는 영향을 주지 않습니다.)
                    eatenFish.isAlive = true;
                    mapCopy[sharkY][sharkX] = -1;
                    mapCopy[nextY][nextX] = eatenFishNum;
                }
            } else {
                break; 
            }
        }
    }

    public static void moveFishes(int[][] map, Fish[] fishes) {
        for (int i = 1; i <= 16; i++) {
            if (!fishes[i].isAlive) continue;

            Fish f = fishes[i];
            // 8방향 탐색
            for (int j = 0; j < 8; j++) {
                int nextDir = (f.dir + j) % 8; // 반시계 방향 회전
                int nextY = f.y + dy[nextDir];
                int nextX = f.x + dx[nextDir];

                if (nextY >= 0 && nextY < 4 && nextX >= 0 && nextX < 4 && map[nextY][nextX] != -1) {
                    // 이동 가능한 칸을 찾았을 때만 방향을 확정하고 상태를 변경합니다.
                    f.dir = nextDir;
                    int targetFishNum = map[nextY][nextX];
                    
                    map[f.y][f.x] = targetFishNum; // 내 위치에 타겟 물고기 놓기 (없으면 0)
                    if (targetFishNum != 0) {
                        fishes[targetFishNum].y = f.y;
                        fishes[targetFishNum].x = f.x;
                    }

                    f.y = nextY;
                    f.x = nextX;
                    map[nextY][nextX] = f.num;
                    
                    break; // 이동했으면 현재 물고기의 이동은 종료
                }
            }
        }
    }
}