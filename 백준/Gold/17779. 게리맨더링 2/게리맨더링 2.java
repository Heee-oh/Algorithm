import java.io.*;
import java.util.*;

public class Main {

    // 16235번 나무 재태크
    // K년이 지난 후 살아남은 나무의 '수'를 출력
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    static int N;
    static int[][] map;
    static int[] area;

    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N + 1][N + 1];
        area = new int[6];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        // x : 1 ~  x + d1 + d2 - 1
        // y : 2 ~ N

        // d1,2 : 1이상

        for (int d1 = 1; d1 <= N; d1++) {
            for (int d2 = 1; d2 <= N; d2++) {

                for (int x = 1; x <= N - d1 - d2; x++) {
                    for (int y = 1 + d1; y <= N - d2; y++) {
                        boolean[][] fiveArea = new boolean[N + 1][N + 1];
                        Arrays.fill(area, 0);

                        for (int d = 0; d <= d1; d++) {
                            fiveArea[x + d][y - d] = true; // 1경계선
                            fiveArea[x + d2 + d][y + d2 - d] = true; // 4 경계선
                            area[5] += map[x + d][y - d] + map[x + d2 + d][y + d2 - d];
                        }

                        for (int d = 0; d <= d2; d++) {
                            fiveArea[x + d][y + d] = true; // 2 경계선
                            fiveArea[x + d1 + d][y - d1 + d] = true; // 3 경계선
                            area[5] += map[x + d][y + d] + map[x + d1 + d][y - d1 + d];
                        }

                        for (int d = 1; d <= d1; d++) {
                            for (int c = y - d + 1; c <= N; c++) {
                                if (fiveArea[x+d][c]) break;
                                fiveArea[x+d][c] = true;
                                area[5] += map[x + d][c];
                            }
                        }

                        for (int d = 1; d < d2; d++) {
                            for (int c = y - d1 + d + 1; c <= N; c++) {
                                if (fiveArea[x+ d1 + d][c]) break;
                                fiveArea[x+d1+d][c] = true;
                                area[5] += map[x + d1 + d][c];
                            }
                        }

                        // 중복 연산 빼기
                        area[5] -= (map[x][y] + map[x + d2][y + d2] + map[x + d1][y - d1] + map[x + d1 + d2][y - d1 + d2]);


                        // 1선거구 인구수 계산
                        for (int r = 1; r < x+d1; r++) {
                            for (int c = 1; c <= y; c++) {
                                if (!fiveArea[r][c]) {
                                    area[1] += map[r][c];
                                }
                            }
                        }

                        // 2선거구
                        for (int r = 1; r <= x+d2; r++) {
                            for (int c = y + 1; c <= N; c++) {
                                if (!fiveArea[r][c]) {
                                    area[2] += map[r][c];
                                }
                            }
                        }

                        // 3선거구
                        for (int r = x + d1; r <= N; r++) {
                            for (int c = 1; c < y -d1 + d2; c++) {
                                if (!fiveArea[r][c]) {
                                    area[3] += map[r][c];
                                }
                            }
                        }
                        for (int r = x+d2 + 1; r <= N; r++) {
                            for (int c = y-d1+d2; c <= N; c++) {
                                if (!fiveArea[r][c]) {
                                    area[4] += map[r][c];
                                }
                            }
                        }


                        int max = 0;
                        int min = Integer.MAX_VALUE;
                        for (int i = 1; i <= 5; i++) {
                            max = Math.max(area[i], max);
                            min = Math.min(area[i], min);
                        }

                        answer = Math.min(answer, max - min);
                    }
                }

            }
        }


        System.out.println(answer);



    }
}
