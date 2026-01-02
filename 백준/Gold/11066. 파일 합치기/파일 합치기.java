import java.io.*;
import java.util.*;

public class Main {
    // 두 개의 파일을 합칠 때 필요한 비용 (두 파일의 크기의 합)
    // 최종적으로 한개의 파일 완성시 필요한 비용의 총 합 구하기

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int K = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[][] dp = new int[K][K];

            for (int i = 0; i < K; i++) {
                Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
            }

            int[] chapters = new int[K];
            int[] prefix = new int[K + 1]; // 구간합용
            for (int i = 0; i < K; i++) {
                chapters[i] = Integer.parseInt(st.nextToken());
                prefix[i + 1] = prefix[i] + chapters[i];
                dp[i][i] = 0; // 파일 1개는 합칠 비용 0
            }

            for (int i = K - 1; i >= 0; i--) {
                for (int j = i + 1; j < K; j++) {

                    int sum = prefix[j + 1] - prefix[i]; //  i..j 구간 파일 크기 합

                    // mid는 i ~ j-1까지만
                    for (int mid = i; mid < j; mid++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][mid] + dp[mid + 1][j] + sum);
                    }
                }
            }

            System.out.println(dp[0][K - 1]); // 정답 출력
        }
    }
}
