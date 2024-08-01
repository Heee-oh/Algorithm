import java.io.*;
import java.util.*;

public class Main {
    static String N;
    static Map<Integer, Boolean> map = new HashMap<>();
    static StringBuilder sb = new StringBuilder();
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int start = 1, end = 1;
        int sum = 0;
        int count = n;
        int[] arr = new int[n + 1];

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        
        boolean flag = false;
        while (true) {
            if (end >= arr.length  && sum < s) {
                break;
            }

            if (sum < s) {
                sum += arr[end++];

            } else {
                count = Math.min((end - start), count);
                sum -= arr[start++];

                flag = true;
//                if (end >= arr.length) {
//                    sum -= arr[start++];
//                } else {
//                    sum += arr[end++];
//
//                }
            }
        }

        if (flag) {
            System.out.println(count);
        } else {
            System.out.println(0);
        }


    }

}