import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
//
//    static int[] arr;
//    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
//        int n = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        String[][] chess = new String[n][m];

        // 체스판 초기화
        for (int i = 0; i < n; i++) {
            chess[i] = br.readLine().split("");
        }

        // 8x8 중 다시칠하는 횟수 최촛값 구하기
        // 0,0 이 W || B 인 경우 오른쪽과 아래가 그 반대여야함
        // 열에 대하여 n-8번

        int min = 50000;
        for (int i = 0; i <= n-8; i++) {

            // 행에 대하여 m-8번
            for (int j = 0; j <= m - 8; j++) {
                // 시작이 화이트면
                // 블랙 true, 화이트 false
                min = Math.min(min, Math.min(getMin(i, j, chess, false), getMin(i, j, chess, true)));

            }

        }
        System.out.println(min);
    }

    private static int getMin(int i, int j, String[][] chess, boolean black) {
        int count = 0;
        for (int k = i; k < i + 8; k++) {

            for (int l = j; l < j + 8; l++) {
            // 시작이 블랙이고 현재 B이면서 true면 cout++;
            if (chess[k][l].equals("B") && !black) {

              count++;
            } else if (chess[k][l].equals("W") && black) {
                count++;
            }
            // 블랙 화이트 뒤집기
            black = !black;
            }
            black = !black;
        }

        return count;
    }


}