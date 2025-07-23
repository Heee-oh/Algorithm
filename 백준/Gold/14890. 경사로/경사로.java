import java.io.*;
import java.util.*;

public class Main {

    //  14890번 스타트와 링크

    static int n, l;
    static int[][] arr;



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        int answer = 0;
        for (int i = 0; i < n; i++) {
            if (checkColLine(arr[i][0], i)) {
                answer++;
            }

            if (checkRowLine(arr[0][i], i)) {
                answer++;
            }

        }

        System.out.println(answer);


    }

    private static boolean checkColLine(int prev, int fixRow) {
        int cnt = 1;
        int col = 1;
        while (col < n) {
            // 높이 차가 1보다 크다면 불가능
            if (Math.abs(prev - arr[fixRow][col]) > 1) return false;

            // 이전 값이 현재 값보다 작다면
            if (prev < arr[fixRow][col]) {
                prev = arr[fixRow][col];

                if (cnt >= l) {
                    cnt = 1;
                } else {
                    return false;
                }

                // 현재가 더 높다면
            } else if (prev > arr[fixRow][col]) {

                // 현재~ 현재 + l 까지 설치 가능한지 확인
                int temp = arr[fixRow][col];
                int tempCnt = 1;

                for (int j = col + 1; j < n; j++) {
                    if (temp != arr[fixRow][j]) break;

                    tempCnt++;
                }

                // 설치가능이라면
                if (tempCnt >= l) {
                    prev = arr[fixRow][col]; // 이전값은 현재 값으로
                    col += l;
                    cnt = 0; //col + l 위치부터 시작하므로 col+l부터 탐색하게 0으로 초기화
                    continue;

                    // 설치 불가능이라면 이 행은 불가능
                } else {
                    return false;
                }

            } else {
                cnt++;
            }

            col++;
        }

        return true;
    }
    private static boolean checkRowLine(int prev, int fixCol) {
        int cnt = 1;
        int row = 1;

        while (row < n) {
            // 높이 차가 1보다 크다면 불가능
            if (Math.abs(prev - arr[row][fixCol]) > 1) return false;

            // 이전 값이 현재 값보다 작다면
            if (prev < arr[row][fixCol]) {
                prev = arr[row][fixCol];

                if (cnt >= l) {
                    cnt = 1;
                } else {
                    return false;
                }

                // 현재가 더 높다면
            } else if (prev > arr[row][fixCol]) {

                // 현재~ 현재 + l 까지 설치 가능한지 확인
                int temp = arr[row][fixCol];
                int tempCnt = 1;

                for (int j = row + 1; j < n; j++) {
                    if (temp != arr[j][fixCol]) break;

                    tempCnt++;
                }

                // 설치가능이라면
                if (tempCnt >= l) {
                    prev = arr[row][fixCol]; // 이전값은 현재 값으로
                    row += l;
                    cnt = 0;
                    continue;

                    // 설치 불가능이라면 이 행은 불가능
                } else {
                    return false;
                }

            } else {
                cnt++;
            }

            row++;
        }

        return true;
    }


}
