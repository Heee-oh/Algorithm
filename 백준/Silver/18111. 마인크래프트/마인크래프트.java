import java.io.*;
import java.util.*;

public class Main {
    static int totalTime = Integer.MAX_VALUE;
    static int height = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        int min = Integer.MAX_VALUE;
        int max = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                // 최대 최소값 획득
                min = Math.min(min, map[i][j]);
                max = Math.max(max, map[i][j]);
            }
        }

        // min ~ max까지 다 해보기
        for (int targetHeight = min; targetHeight <= max; targetHeight++) {
            landLeveling(n, m, map, targetHeight, b);
        }

        System.out.println(totalTime + " " + height);


    }

    // 1. 가장 위 블록 제거후 인벤토린 넣기 : 2초
    // 2. 인벤토리에서 블록 하나 꺼내서 블록위에 쌓기 : 1초
    // 최소시간 구하기 , 같은게 많다면 높이가 가장 높은 걸로
    private static void landLeveling(int n, int m, int[][] map, int targetHeight, int inventory) {
        int currentTime = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(map[i][j] == targetHeight) continue;

                // 목표 블록 높이보다 높다면 1번 작업 실행
                if (map[i][j] > targetHeight) {
                    currentTime += (map[i][j] - targetHeight) * 2;
                    inventory += map[i][j] - targetHeight;
                    continue;
                }

                // 목표 블록 높이보다 낮다면 2번 작업 실행
                if (map[i][j] < targetHeight) {
                    int secondWork = targetHeight - map[i][j]; // 2번 작업 결과

                    // 작업 시간 추가
                    currentTime += secondWork;
                    // 2번 작업으로 인한 인벤토리 블록 꺼내기
                    inventory -= secondWork;
                }
            }
        }

        // 땅고르기 후 결과를 봤을때 인벤토리가 음수라면 불가능한 높이이므로 return
        if (inventory < 0) return;

        // 최소시간이면 갱신
        if (currentTime < totalTime || (currentTime == totalTime && targetHeight > height)) {
            totalTime = currentTime;
            height = targetHeight;
        }


    }
}
