import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        int[] dp = new int[n + 1];

        // 포도주 테이블 초기화
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        if (n < 2) {
            System.out.println(arr[0]);
            return;
        }

        dp[1] = arr[0];
        dp[2] = arr[0] + arr[1];

        int max = dp[2];

        for (int i = 2; i < n; i++) {
            int cur = arr[i];
            int pre = arr[i - 1];
            int dpIndex = i + 1;
            dp[dpIndex] = Math.max(cur + pre,
                    Math.max(dp[dpIndex - 2] + cur, dp[dpIndex - 3] + cur + pre));
            dp[dpIndex] = Math.max(max, dp[dpIndex]);
            max = dp[dpIndex];
        }

        System.out.println(max);





    }

}