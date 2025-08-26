import javax.lang.model.element.AnnotationValueVisitor;
import java.io.*;
import java.util.*;

public class Main {

    static int N, K;
    static int[] fishTank;

    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        fishTank = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            fishTank[i] = Integer.parseInt(st.nextToken());
        }

        int time = 0;
        if (checkAnswer(time)) return;

        while (true) {
            time++;

            // 가장 적은 물고기가 들어있는 어항에 물고기수 + 1
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                min = Math.min(fishTank[i], min);
            }

            for (int i = 0; i < N; i++) {
                if (fishTank[i] == min) {
                    fishTank[i]++;
                }
            }

            int[][] tmp = new int[N / 2][N];

            int endLine = N / 2 - 1;
            tmp[endLine] = fishTank.clone();
            int tmpLen = tmp.length;

            // 가장 오른쪽 어항을 위로 올리기
            tmp[endLine - 1][1] = tmp[endLine][0];
            tmp[endLine][0] = 0;

            // 올린 어항의 가로 세로 사이즈
            int width = 1, height = 2;
            // 바닥 현 위치
            int curIdx = 1;

            // 2개 이상 어항을 공중부양 후 시계 90도 회전후 올리기
            // 2개이상의 최대 높이, 최대 길이
            int cnt = 1;
            while (curIdx + width + height - 1 < N) {

                int targetR = endLine - 1;
                int targetC = curIdx + width;

                // 공중부양 시킬 어항
                for (int c = curIdx + width - 1; c >= curIdx; c--) {
                    for (int r = endLine; r > endLine - height; r--) {
                        tmp[targetR][targetC] = tmp[r][c];
                        tmp[r][c] = 0;

                        targetC++;
                    }

                    targetR--;
                    targetC = curIdx + width;
                }

                curIdx = curIdx + width;
                cnt++;

                if (cnt % 2 == 0) {
                    width++;
                } else {
                    height++;
                }

            }


            // 어항 이동후 새롭게 만듦
            int[][] tmp2 = new int[height][N - curIdx];

            int i1 = tmpLen - height;
            for (int i = i1; i < tmpLen; i++) {
                for (int j = curIdx; j < N; j++) {
                    tmp2[i - i1][j - curIdx] = tmp[i][j];
                }
            }

            // 인접한 두 어항에 대하여 물고기 수 차이 / 5  = D, D > 0 이면 가장 큰 곳에서 가장 작은 곳으로 이동
            fishMove(height, curIdx, tmp2);

            // 평탄화
            flatMap(tmp2);

            // /2 , /4 로 쌓기
            int[][] step1 = new int[2][N / 2];
            for (int i = step1[0].length - 1; i >= 0; i--) {
                step1[0][step1[0].length - 1 - i] = fishTank[i];
            }
            for (int i = N/2; i < N; i++) {
                step1[1][i - step1[0].length] = fishTank[i];
            }


            // 180도 회전
            int[][] rotate = new int[2][step1[0].length / 2];
            for (int j = 0; j < step1[0].length / 2; j++) {
                rotate[1][rotate[0].length - j - 1] = step1[0][j];
                rotate[0][rotate[0].length - j - 1] = step1[1][j];
            }

            for (int i = 0; i < step1[0].length / 2; i++) {
                step1[0][i] = rotate[0][i];
                step1[1][i] = rotate[1][i];
            }

            int[][] step2 = new int[4][N/4];
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < step2[0].length; j++) {
                    step2[i][j] = step1[i][j];
                }
            }

            for (int i = 2; i < 4; i++) {
                for (int j = step2[0].length; j < N/2; j++) {
                    step2[i][j - step2[0].length] = step1[i-2][j];
                }
            }

            // 물고기 수 조절

            Queue<int[]> q = new LinkedList<>();
            boolean[][] visited = new boolean[step2.length][step2[0].length];
            int[][] moveMap = new int[step2.length][step2[0].length];
            q.add(new int[]{0, 0});
            visited[0][0] = true;

            while (!q.isEmpty()) {
                int[] cur = q.poll();
                int curR = cur[0];
                int curC = cur[1];

                for (int i = 0; i < 4; i++) {
                    int nr = curR + dr[i];
                    int nc = curC + dc[i];

                    if (nr < 0 || nr >= step2.length
                            || nc < 0 || nc >= step2[0].length
                            || step2[nr][nc] == 0) continue;

                    // 두 어항의 차를 구함
                    int diff = step2[curR][curC] - step2[nr][nc];

                    // 0 보다 크면 이동
                    if (diff > 0) {
                        int fish = diff / 5;
                        moveMap[nr][nc] += fish;
                        moveMap[curR][curC] -= fish;
                    }

                    if (!visited[nr][nc]) {
                        q.add(new int[]{nr, nc});
                        visited[nr][nc] = true;
                    }
                }
            }
            for (int i = 0; i < step2.length; i++) {
                for (int j = 0; j < step2[0].length; j++) {
                    step2[i][j] += moveMap[i][j];


                }
            }

            // 평탄화
            flatMap(step2);

            if (checkAnswer(time)) return;
        }



    }

    private static boolean checkAnswer(int time) {
        int fishMax = 0;
        int fishMin = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            fishMax = Math.max(fishTank[i], fishMax);
            fishMin = Math.min(fishTank[i], fishMin);
        }

        if (fishMax - fishMin <= K) {
            System.out.println(time);
            return true;
        }
        return false;
    }

    private static void fishMove(int height, int curIdx, int[][] tmp2) {
        int[][] moveMap = new int[height][N - curIdx];
        boolean[][] visited = new boolean[height][N - curIdx];

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        visited[0][0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curR = cur[0];
            int curC = cur[1];

            for (int i = 0; i < 4; i++) {
                int nr = curR + dr[i];
                int nc = curC + dc[i];

                if (nr < 0 || nr >= tmp2.length
                        || nc < 0 || nc >= tmp2[0].length
                        || tmp2[nr][nc] == 0) continue;

                // 두 어항의 차를 구함
                int diff = tmp2[curR][curC] - tmp2[nr][nc];

                // 0 보다 크면 이동
                if (diff > 0) {
                    int fish = diff / 5;
                    moveMap[nr][nc] += fish;
                    moveMap[curR][curC] -= fish;
                }

                if (!visited[nr][nc]) {
                    q.add(new int[]{nr, nc});
                    visited[nr][nc] = true;
                }
            }
        }

        for (int i = 0; i < tmp2.length; i++) {
            for (int j = 0; j < tmp2[0].length; j++) {
                tmp2[i][j] += moveMap[i][j];
            }
        }
    }

    private static void flatMap(int[][] tmp2) {
        int idx = 0;
        for (int i = 0; i < tmp2[0].length; i++) {
            for (int j = tmp2.length - 1; j >= 0; j--) {
                if (tmp2[j][i] != 0) {
                    fishTank[idx++] = tmp2[j][i];
                }
            }
        }
    }

    private static void print2(int[][] tmp2) {
        for (int i = 0; i < tmp2.length; i++) {
            System.out.println(Arrays.toString(tmp2[i]));
        }
    }

    private static void print1(int[][] tmp) {
        for (int i = 0; i < N/2; i++) {
            System.out.println(Arrays.toString(tmp[i]));
        }
    }
}
