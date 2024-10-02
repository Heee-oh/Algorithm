import java.io.*;
import java.util.*;

public class Main {

    private static int[] sorted;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        sorted = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(br.readLine());
        }

        merge_sort(a, 0, n - 1);

        for (int i : a) {
            sb.append(i).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void merge_sort(int[] a, int left, int right) {

        // 한 개의 원소로 분할되었으면 return;
        if (left == right) return;

        int mid = (left + right) / 2;
        merge_sort(a, left, mid);
        merge_sort(a, mid + 1, right);

        merge(a, left, mid, right);

    }

    private static void merge(int[] a, int left, int mid, int right) {
        int l = left;
        int r = mid + 1;
        int idx = left;

        while (l <= mid && r <= right) {
            if (a[l] <= a[r]) {
                sorted[idx] = a[l];
                idx++;
                l++;
            } else {
                sorted[idx] = a[r];
                idx++;
                r++;
            }
        }

        // 오른쪽 남은 원소 처리
        while (r <= right) {
            sorted[idx] = a[r];
            idx++;
            r++;
        }

        // 왼쪽 남은 원소 처리
        while (l <= mid) {
            sorted[idx] = a[l];
            idx++;
            l++;
        }



        for (int i = left; i <= right; i++) {
            a[i] = sorted[i];
        }

    }

}
