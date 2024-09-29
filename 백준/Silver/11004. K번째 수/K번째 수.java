import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());


        // k를 피벗으로 설정

        int[] arr = new int[n + 1];

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }


        quickSort(arr, 1, n);




        bw.write(arr[k] + "");
        bw.flush();
        bw.close();
    }

    private static void quickSort(int[]arr, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        int pivot = partition(arr, lo, hi);
        quickSort(arr, lo, pivot);
        quickSort(arr, pivot + 1, hi);
    }

    private static int partition(int[] arr, int left, int right) {

        int lo = left - 1;
        int hi = right + 1;
        int pivot = arr[(left + right) / 2];

        while (true) {

            do {
                lo++;
            } while (arr[lo] < pivot);


            do {
                hi--;
            } while (arr[hi] > pivot && lo <= hi);

            if (lo >= hi) {
                return hi;
            }

            swap(arr, lo, hi);
        }
    }

    private static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }


}
