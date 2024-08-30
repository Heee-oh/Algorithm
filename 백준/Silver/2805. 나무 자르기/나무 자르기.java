import java.io.*;
import java.util.*;

public class Main {

    static long max = 0;
    static int[] trees;
    static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 절단기에 설정할 수 있는 높이는 양의 정수 또는 0
        // 적어도 M미터의 나무를 집에 가져가기 위해서 절단기에 설정할 수 있는 높이의 최댓값
        int n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        trees = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
        }

        // 나무의 높이는 10억 이하 || 0

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
                if (trees[i] - mid > 0) {
                    sum +=trees[i] - mid;
                }
            }

            if (sum >= k) {
                max = Math.max(max, mid);
                binarySearch(mid + 1, right);
            } else if (sum < k) {
                binarySearch(left, mid - 1);
            }

        }

    }


}