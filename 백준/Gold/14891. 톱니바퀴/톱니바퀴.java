import java.io.*;
import java.util.*;

public class Main {

    //  14891번 톱니바퀴

    // 시계방향 -1
    // 반시계방향 +1

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[][] arr = new char[4][8];
        int[] dist = new int[4]; // 이동 값

        for (int i = 0; i < 4; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        int K = Integer.parseInt(br.readLine());

        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int idx = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken()); // 돌아가는 변동 거리로 볼 수 있음

            int[] tmp = new int[4];

            // 주변 톱니 먼저 회전
            // 왼쪽은 현재 2와 다음 6이 맞닿음
            int rightDir = dir;
            for (int j = idx + 1; j < 4; j++) {
                int curRightIdx = (2 + dist[j-1] + 8) % 8;
                int nextLeftIdx = (6 + dist[j] + 8) % 8;


                // 서로 다른 극이라면 회전
                if (arr[j-1][curRightIdx] != arr[j][nextLeftIdx]) {
                    tmp[j] = rightDir;
                    rightDir *= -1;

                } else {
                    break;
                }
            }

            int leftDir = dir;
            for (int j = idx-1; j >= 0; j--) {
                int curLeftIdx = (6 + dist[j + 1] + 8) % 8;
                int nextRightIdx = (2 + dist[j] + 8) % 8;

                if (arr[j+1][curLeftIdx] != arr[j][nextRightIdx]) {
                    tmp[j] = leftDir;
                    leftDir *= -1;

                } else {
                    break;
                }
            }

            for (int j = 0; j < 4; j++) {
                dist[j] += tmp[j];
                dist[j] %= 8;
            }

            // 그 후 현재 톱니 회전
            dist[idx] += (dir * - 1);

        }

        int sum = 0;
        // 12시 정각은 0번 인덱스
        for (int i = 0; i < 4; i++) {
            int dir12hour = (dist[i] + 8) % 8;

            if (arr[i][dir12hour] == '1') {
                sum += 1 << i;
            }
        }

        System.out.println(sum);
    }




}
