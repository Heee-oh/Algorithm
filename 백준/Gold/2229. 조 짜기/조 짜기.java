import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] score = new int[n + 1];
        int[] dp = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            score[i] = Integer.parseInt(st.nextToken());
        }


        for (int i = 2; i <= n; i++) {
            int max = -1, min = 10001;

            for (int j = i; j >= 1; j--) {
                max = Math.max(max, score[j]);
                min = Math.min(min, score[j]);

                // j~i까지를 새로운 조로 했을때 정도 + 이전 j-1까지 여러개 조의 정도의 최댓값
                dp[i] = Math.max(dp[i], max - min + dp[j - 1]);
            }
        }

        System.out.println(dp[n]);

    }
}
