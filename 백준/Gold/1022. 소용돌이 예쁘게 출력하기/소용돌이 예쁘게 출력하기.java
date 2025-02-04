import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r1 = Integer.parseInt(st.nextToken());
        int c1 = Integer.parseInt(st.nextToken());
        int r2 = Integer.parseInt(st.nextToken());
        int c2 = Integer.parseInt(st.nextToken());
        
        // 소용돌이 순서 오른쪽 - 위 - 왼쪽 - 아래
        int[] dx = {1,  0, -1, 0};
        int[] dy = {0, -1,  0, 1};

        // 소용돌이를 출력할 배열 생성
        int[][] map = new int[r2- r1 + 1][c2 - c1 + 1];
        // 현 위치
        int[] pos = {0, 0};
        int fullCheck = 0; // 다 채워졌는지 확인
        int n = 1; // 현재 소용돌이 번호

        // 0,0 부터 확인
        if (checkBoundary(r2, pos, r1, c2, c1)) {
            map[pos[0] - r1][pos[1] - c1] = n;
            fullCheck++;
        }

        int moveDistance = 1; // 이동 길이
        int maxLengthNum = 1; // 최댓 값

        // 배열에 모두 채워질때까지
        while (fullCheck < map.length * map[0].length) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < moveDistance; k++) {
                    pos[0] += dy[j];
                    pos[1] += dx[j];
                    n++;

                    if (checkBoundary(r2, pos, r1, c2, c1)) {
                        map[pos[0] - r1][pos[1] - c1] = n;
                        maxLengthNum =  Math.max(maxLengthNum, n);
                        fullCheck++;
                    }
                }
                // 2개의 방향당 이동 거리를 1씩 늘림
                if (j % 2 == 1) moveDistance++;
            }
        }

        // 제일 긴 수 길이 얻기
        int len = String.valueOf(maxLengthNum).length() + 1;

        // 출력
        for (int[] arr : map) {
            for (int i = 0; i < arr.length; i++) {
                bw.write(String.format("%" + (i == 0 ? len - 1 : len)  + "d",arr[i]));
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
    }

    //r2 >= 현재 행 >= r1 && c2 >= 현재 열 >= c1
    private static boolean checkBoundary(int r2, int[] pos, int r1, int c2, int c1) {
        return r2 >= pos[0] && r1 <= pos[0] && c2 >= pos[1] && c1  <= pos[1];
    }
}