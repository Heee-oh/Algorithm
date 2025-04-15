import java.io.*;
import java.util.*;

public class Main {

    static class Fish {
        int r,c, size, dist;

        public Fish(int r, int c, int size, int dist) {
            this.r = r;
            this.c = c;
            this.size = size;
            this.dist = dist;
        }
    }

    static int[][] map;
    static Fish shark;
    static int eatFishCnt = 0;

    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    //상어 위치에서 일단 BFS 탐색을 한다. 먹을 수 있는 물고기를 만나면
    // 위치값과 거리를 기록하고 다음 먹을 수 있는 물고기를 탐색한다.
    // 같은 거리의 물고기를 만나면 조건에 맞게 비교해서 갱신
    // 이후 선택한 물고기를 먹고 다시 반복

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // 상어 위치 저장
                if (map[i][j] == 9) {
                    shark = new Fish(i, j, 2, 0);
                    map[i][j] = 0;
                }
            }
        }

        int time = 0;

        while (true) {
            Fish targetFish = bfs(n);

            // 아무것도 먹지 못했으므로 엄마 호출
            if (targetFish.dist == Integer.MAX_VALUE) break;

            eatFishCnt++; // 먹었으므로 카운트 1 추가
            map[targetFish.r][targetFish.c] = 0;
            shark.r = targetFish.r;
            shark.c = targetFish.c;
            time += targetFish.dist;

            if (eatFishCnt == shark.size) {
                shark.size++;
                eatFishCnt = 0;
            }
        }

        System.out.println(time);
    }

    private static Fish bfs(int n) {
        boolean[][] visited = new boolean[n][n];
        Queue<Fish> q = new LinkedList<>();
        visited[shark.r][shark.c] = true;
        q.add(shark);

        // 목표 물고리를 만들어 갱신
        Fish target = new Fish(100,100, 0, Integer.MAX_VALUE);

        while (!q.isEmpty()) {
            Fish current = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextR = current.r + dy[i];
                int nextC = current.c + dx[i];

                // 맵 범위 체크 및 크기 체크
                if (nextR < 0 || nextR >= n
                        || nextC < 0 || nextC >= n
                        || visited[nextR][nextC] || map[nextR][nextC] > shark.size) continue;

                q.add(new Fish(nextR, nextC, map[nextR][nextC], current.dist + 1));
                visited[nextR][nextC] = true;

                // 빈 공간이 아니면서 사이즈가 샤크보다 작다면
                if (0 < map[nextR][nextC] && map[nextR][nextC] < shark.size) {

                    // 최단 경로의 물고기가 있다면
                    if (target.dist > current.dist + 1) {
                        target.r = nextR;
                        target.c = nextC;
                        target.dist = current.dist + 1;
                        continue;

                    } else if (target.dist < current.dist + 1) {
                        continue;
                    }

                    // 다음 물고기가 더 위에 있다면
                    if (target.r > nextR) {
                        target.r = nextR;
                        target.c = nextC;
                        continue;
                    } else if (target.r < nextR) {
                        continue;
                    }

                    // 같은 행에 있고 더 왼쪽에 있다면
                    if (target.c > nextC) {
                        target.c = nextC;
                    }

                }

            }
        }

        return target;
    }


}