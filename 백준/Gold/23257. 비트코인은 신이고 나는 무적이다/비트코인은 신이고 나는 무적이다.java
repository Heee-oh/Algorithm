import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        boolean[] dp = new boolean[1024];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Math.abs(Integer.parseInt(st.nextToken()));
            dp[arr[i]] = true;
        }


        for (int i = 1; i < M; i++) {
            boolean[] tmp = new boolean[1024];
            
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < dp.length; k++) {
                    if (!dp[k]) continue;
                    tmp[k^arr[j]] = true;
                }
            }

            dp = tmp;
        }

        for (int i = 1023; i >= 0; i--) {
            if (dp[i]) {
                System.out.println(i);
                return;
            }
        }

    }
}
