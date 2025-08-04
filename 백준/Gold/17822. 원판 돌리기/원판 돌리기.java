import java.io.*;
import java.util.*;

public class Main {

    // 17822번 원판 돌리기
    static int N, M, T;

    static int[][] circle;

    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        circle = new int[N + 1][M];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                circle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            // 1. 원판 돌리기 , x의 배수 라인 선택해서 돌림
            for (int j = x; j <= N; j += x) {

                // d = 0 : 시계 , 1 : 반시계
                int[] line = circle[j];

                // 회전 저장용 임시 배열
                int[] tmp = new int[M];

                // 회전 방향 선택
                int dist = d == 0 ? k : M - k;
                for (int l = 0; l < M; l++) {
                    tmp[(l + dist) % M] = line[l];
                }

                for (int l = 0; l < M; l++) {
                    line[l] = tmp[l];
                }

            }


            boolean[][] check = new boolean[N+1][M];
            // 2. 원판 체크
            for (int j = 1; j <= N; j++) {
                // 모든 원판에 인접하면서 같은 수를 모두 지움
                int[] line = circle[j];

                for (int l = 0; l < M; l++) {
                    if (line[l] == 0) continue;

                    if (line[l] == line[(l + 1) % M]) {
                        check[j][l] = check[j][(l+1) % M] = true;
                    }

                    if (j < N && circle[j][l] == circle[j + 1][l]) {
                        check[j][l] = check[j + 1][l] = true;
                    }
                }
            }


            // 인접하면서 같은 수 지우기
            int sum = 0, count = 0;

            boolean flag = false; // 인접한 수가 존재했는지 플래그
            for (int j = 1; j <= N; j++) {
                for (int l = 0; l < M; l++) {
                    if (check[j][l]) {
                        circle[j][l] = 0;
                        flag = true;

                    } else {
                        sum += circle[j][l];
                        count += circle[j][l] != 0 ? 1 : 0;
                    }
                }
            }

            // 없다면 모든 원판에 적힌 수의 평균을 구하고 , 평균보다 크다면 -1 작다면 +1
            if (!flag) {
                double avg = (double) sum / count;


                for (int j = 1; j <= N; j++) {
                    for (int l = 0; l < M; l++) {
                        if (circle[j][l] <= 0) continue;

                        if (circle[j][l] > avg) circle[j][l]--;
                        else if (circle[j][l] < avg) circle[j][l]++;

                    }
                }
            }
        }

        int sum = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                sum += circle[i][j];
            }
        }

        System.out.println(sum);
    }

}

