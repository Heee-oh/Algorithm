import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    // 공부 시간 한계 초과 X, 과목의 중요도 합이 최대 가 되는 과목 선택
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] dp = new int[N + 1];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int important = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            for (int j = N; j >= time; j--) {
                dp[j] = Math.max(dp[j], dp[j - time] + important);
            }
        }

        System.out.println(dp[N]);

    }

}