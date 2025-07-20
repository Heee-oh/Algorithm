import java.io.*;
import java.util.*;

public class Main {

    static int[] dy = {0, 1, -1, 0, 0}; // 동 서 북 남
    static int[] dx = {0, 0, 0, -1, 1};

    static int[] dice; // 위, 아래, 동, 서, 남, 북


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        int[] command = new int[k];
        dice = new int[7];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            command[i] = Integer.parseInt(st.nextToken());
        }

        int top = 1, bottom = 6;


        for (int i = 0; i < k; i++) {
            int nextY = y + dy[command[i]];
            int nextX = x + dx[command[i]];

            // 바깥 이동시 무시
            if (nextY < 0 || nextY >= m || nextX < 0 || nextX >= n) {
                continue;
            }

            // 위치 이동
            change(command[i]);

            // 칸이 0 이 아니면 주사위의 바닥에 복사하고 0으로 바꿈
            if (map[nextX][nextY] > 0) {
                dice[bottom] = map[nextX][nextY];
                map[nextX][nextY] = 0;

                // 바닥이 0이면 바닥면 수를 칸에 복사
            } else {
                map[nextX][nextY] = dice[bottom];
            }

            y = nextY;
            x = nextX;

            sb.append(dice[top] + "\n");
        }


        System.out.print(sb.toString());
    }

    private static void change(int dir) {
        int temp = dice[1];

        // 동쪽
        if (dir == 1) {
            dice[1] = dice[3];
            dice[3] = dice[6];
            dice[6] = dice[4];
            dice[4] = temp;

            // 서쪽
        } else if (dir == 2) {
            dice[1] = dice[4];
            dice[4] = dice[6];
            dice[6] = dice[3];
            dice[3] = temp;

            // 북쪽
        } else if (dir == 3) {
            dice[1] = dice[2];
            dice[2] = dice[6];
            dice[6] = dice[5];
            dice[5] = temp;

            // 남쪽
        } else {
            dice[1] = dice[5];
            dice[5] = dice[6];
            dice[6] = dice[2];
            dice[2] = temp;
        }
    }

}
