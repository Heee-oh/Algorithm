import java.io.*;
import java.util.*;

public class Main {
    static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};

    static int[] sharkDr = {-1, 0, 1, 0};
    static int[] sharkDc = {0, -1, 0, 1};

    static int M, S;
    static int[][][] map;
    static int[][] smell, fishCntMap;
    static int[] shark = new int[2];
    static List<int[]> sharkDir = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        map = new int[4][4][8];
        smell = new int[4][4];
        fishCntMap = new int[4][4];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int fx = Integer.parseInt(st.nextToken()) - 1;
            int fy = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;

            map[fx][fy][d]++;
            fishCntMap[fx][fy]++;
        }

        st = new StringTokenizer(br.readLine());
        shark[0] = Integer.parseInt(st.nextToken()) - 1;
        shark[1] = Integer.parseInt(st.nextToken()) - 1;

        createSharkDir();

        for (int i = 0; i < S; i++) {
            // 1. 복제 마법 시전
            int[][][] tmp = new int[4][4][8];
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    tmp[j][k] = map[j][k].clone();
                }
            }


            // 2. 모든 물고기 한 칸 이동
            int[][][] moved = new int[4][4][8];
            for (int r = 0; r < 4; r++) {
                for (int c = 0; c < 4; c++) {
                    for (int j = 0; j < 8; j++) {
                        if (map[r][c][j] == 0) continue;

                        for (int d = 0; d < 8; d++) {
                            int nextDir = (j - d + 8) % 8;
                            int nr = r + dr[nextDir];
                            int nc = c + dc[nextDir];

                            if (nr < 0 || nr >= 4
                                    || nc < 0 || nc >= 4
                                    || smell[nr][nc] > 0
                                    || (shark[0] == nr && shark[1] == nc)) {
                                continue;
                            }

                            // 물고기 이동
                            moved[nr][nc][nextDir] += map[r][c][j];
                            moved[r][c][j] -= map[r][c][j];
                            break;
                        }
                    }
                }
            }

            for (int r = 0; r < 4; r++) {
                for (int c = 0; c < 4; c++) {
                    for (int d = 0; d < 8; d++) {
                        map[r][c][d] += moved[r][c][d];
                        fishCntMap[r][c] += moved[r][c][d];

                    }
                }
            }

            // 3. 상어 3칸 이동
            int cnt = -1;
            int nextSharkDir = 0;
            for (int j = 0; j < sharkDir.size(); j++) {
                boolean[][] visited = new boolean[4][4];
                int[] dir = sharkDir.get(j);
                int tmpR = shark[0];
                int tmpC = shark[1];
                int tmpSum = 0;

                for (int k = 0; k < 3; k++) {
                    int nextDir = dir[k];
                    int nr = tmpR + sharkDr[nextDir];
                    int nc = tmpC + sharkDc[nextDir];

                    // 이동 불가
                    if (nr < 0 || nr >= 4
                            || nc < 0 || nc >= 4 ) {
                        tmpSum = -1; // 갱신되지 않게 방지
                        break;
                    }

                    // 물고기 중복 계산 방지
                    if (!visited[nr][nc]) {
                        tmpSum += fishCntMap[nr][nc];
                        visited[nr][nc] = true;
                    }

                    tmpR = nr;
                    tmpC = nc;
                }

                // 더 많다면
                if (cnt < tmpSum) {
                    nextSharkDir = j;
                    cnt = tmpSum;
                }
            }

            // 상어 이동 , 냄새 풍기기
            int[] dir = sharkDir.get(nextSharkDir);

            for (int j = 0; j < 3; j++) {
                int nr = shark[0] + sharkDr[dir[j]];
                int nc = shark[1] + sharkDc[dir[j]];

                // 물고기 다 잡기
                if (fishCntMap[nr][nc] > 0) {
                    fishCntMap[nr][nc] = 0;

                    smell[nr][nc] = 3;

                    for (int k = 0; k < 8; k++) {
                        map[nr][nc][k] = 0;
                    }
                }

                // 이동
                shark[0] = nr;
                shark[1] = nc;
            }


            // 4. 두 번 전 연습에서 생긴 물고기 냄새 삭제
            for (int r = 0; r < 4; r++) {
                for (int c = 0; c < 4; c++) {
                    if (smell[r][c] > 0) {
                        smell[r][c]--;
                    }
                }
            }

            // 5. 물고기 복제 완료
            for (int r = 0; r < 4; r++) {
                for (int c = 0; c < 4; c++) {
                    for (int d = 0; d < 8; d++) {
                        map[r][c][d] += tmp[r][c][d];
                        fishCntMap[r][c] += tmp[r][c][d];
                    }
                }
            }
        }



        int answer = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                answer += fishCntMap[i][j];
            }
        }

        System.out.println(answer);
    }

    private static void createSharkDir() {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    sharkDir.add(new int[]{i, j, k});
                }
            }
        }
    }
    private static void print() {
        System.out.println("===============");
        for (int j = 0; j < 4; j++) {
            for (int k = 0; k < 4; k++) {
                if (smell[j][k] > 0) {
                    System.out.print("x");
                } else if (j == shark[0] && k == shark[1]) {
                    System.out.print("S");
                }else {
                    System.out.print(fishCntMap[j][k]);

                }
            }
            System.out.println();
        }
    }


}
