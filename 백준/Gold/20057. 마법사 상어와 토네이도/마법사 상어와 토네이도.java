import java.beans.Customizer;
import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] map;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int r = N/2, c = N/2;
        int answer = 0;
        int dist = 1, dir = -1, cnt = 0;
        while (r != 0 || c != 0) {
            // 돌리기
            // 거리 , 방향 증가
            if (cnt != 0 && cnt % 2 == 0) {
                dist++;
            }

            dir = (dir + 1) % 4;

            for (int i = 0; i < dist; i++) {
                r = r + dr[dir];
                c = c + dc[dir];

                if (isBoundary(r, c)) {
                    System.out.println(answer);
                    return;
                }

                int[] tmp = new int[10];
                tmp[1] = (int) (map[r][c] * 0.05);
                tmp[2] = tmp[4] = (int) (map[r][c] * 0.07);
                tmp[3] = tmp[5] = (int) (map[r][c] * 0.02);
                tmp[6] = tmp[8] = (int) (map[r][c] * 0.1);
                tmp[7] = tmp[9] = (int) (map[r][c] * 0.01);

                // a로 이동하는 양은 이동하지 않은 남은 모래양
                // 다른 비율이 있는 칸은 해당 비율만큼 소수점 제거하고 가져가고
                // 소수점 제거한 뒤 가져간 것을 제외한 나머지가 a로 이동
                tmp[0] = map[r][c];
                for (int j = 1; j < 10; j++) {
                    tmp[0] -= tmp[j];
                }


                map[r][c] = 0;

                int idx = 0;
                // 바라보는 방향 2칸
                for (int j = 1; j <= 2; j++) {
                    int nextR = r + (dr[dir]) * j;
                    int nextC = c + (dc[dir]) * j;

                    if (isBoundary(nextR, nextC)) {
                        answer += tmp[idx];

                    } else {
                        map[nextR][nextC] += tmp[idx];
                    }

                    idx++;
                }

                // 상대적 왼쪽
                for (int j = 1; j <= 2; j++) {
                    int nextR = r + (dr[(dir - 1 + 4) % 4]) * j;
                    int nextC = c + (dc[(dir - 1 + 4) % 4]) * j;

                    if (isBoundary(nextR, nextC)) {
                        answer += tmp[idx];
                    } else {
                        map[nextR][nextC] += tmp[idx];
                    }
                    idx++;
                }

                // 상대적 오른쪽
                for (int j = 1; j <= 2; j++) {
                    int nextR = r + (dr[(dir + 1) % 4]) * j;
                    int nextC = c + (dc[(dir + 1) % 4]) * j;

                    if (isBoundary(nextR, nextC)) {
                        answer += tmp[idx];

                    } else {
                        map[nextR][nextC] += tmp[idx];
                    }
                    idx++;
                }

                // 위 대각 방향
                int nextR = r + dr[dir];
                int nextC = c + dc[dir];
                for (int j = 0; j < 2; j++) {
                    // 6번 가리킴
                    int nextDir = (dir + (j == 0 ? -1 : 1) + 4) % 4;
                    int tmpR = nextR + dr[nextDir];
                    int tmpC = nextC + dc[nextDir];
                    if (isBoundary(tmpR, tmpC)) {
                        answer += tmp[j == 0 ? 6 : 8];

                    } else {
                        map[tmpR][tmpC] += tmp[j == 0 ? 6 : 8];
                    }

                }

                // 아래 대각 방향
                nextR = r - dr[dir];
                nextC = c - dc[dir];
                for (int j = 0; j < 2; j++) {
                    int nextDir = (dir + (j == 0 ? -1 : 1) + 4) % 4;
                    int tmpR = nextR + dr[nextDir];
                    int tmpC = nextC + dc[nextDir];


                    if (isBoundary(tmpR, tmpC)) {
                        answer += tmp[j == 0 ? 7 : 9];

                    } else {
                        map[tmpR][tmpC] += tmp[j == 0 ? 7 : 9];
                    }
                }
            }
            cnt++;

        }

        System.out.println(answer);
    }

    private static boolean isBoundary(int nextR, int nextC) {
        return nextR >= N || nextR < 0 || nextC >= N || nextC < 0;
    }


}

