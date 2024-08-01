import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int[] arr = new int[n + 1];
        int start = 1, end = 1;
        int sum = 0;
        int count = n;

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

            }
        }

        if (flag) {
            System.out.println(count);
        } else {
            System.out.println(0);
        }


    }

}