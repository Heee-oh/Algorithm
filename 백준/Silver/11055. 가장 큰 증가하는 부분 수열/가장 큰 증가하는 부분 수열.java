import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    static int[] arr;
    static int[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N + 1];
        dp = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[1] = arr[1];

        int max = 0;
        for (int i = 1; i <= N; i++) {
            max = Math.max(recurve(i), max);
        }

        System.out.println(max);


    }

    private static int recurve(int n) {

        if (dp[n] > 0) {
            return dp[n];
        }

        if (dp[n] == 0) {
            dp[n] = arr[n];
        }

        for (int i = n - 1; i > 0; i--) {
            if ( arr[i] < arr[n] ) {
                dp[n] = Math.max(dp[n], recurve(i) + arr[n]);
            }
        }

        return dp[n];
    }


}