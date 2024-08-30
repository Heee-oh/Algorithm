import java.io.*;
import java.util.*;

public class Main {

    static long max = 0;
    static int[] trees;
    static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 절단기에 설정할 수 있는 높이는 양의 정수 또는 0
        // 적어도 M미터의 나무를 집에 가져가기 위해서 절단기에 설정할 수 있는 높이의 최댓값
        int n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        trees = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
        }

        // M의 높이는 20억 이하
        binarySearch(1, Integer.MAX_VALUE);

        bw.write(max + "");
        bw.flush();
        bw.close();
    }

    private static void binarySearch(long left, long right) {

        if (left <= right) {
            long sum = 0;
            long mid = (left + right) / 2;

            for (int i = 0; i < trees.length; i++) {
                // 자르는 높이보다 작은 나무는 무시
                if (trees[i] - mid > 0) {
                    sum +=trees[i] - mid;
                }
            }

            // 나무를 m미터 보다 많이 얻었다면 자르는 높이를 더 높여서 환경보호 (*ㅅ*)
            if (sum >= m) {
                max = Math.max(max, mid);
                binarySearch(mid + 1, right); // 자르는 높이를 높여서 최소한의 m미터 이상만 가져가기 위해 탐색

                // 자르는 높이가 너무 높기에 높이를 낮춘다.
            } else if (sum < m) {
                binarySearch(left, mid - 1);
            }

        }

    }


}