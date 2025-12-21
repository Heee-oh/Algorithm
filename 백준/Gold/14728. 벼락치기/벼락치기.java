import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.*;

class Main {

    // 어떤 단원의 문제 맞추기 : 그 단원의 예상 공부 시간 이상 해야 가능
    // 남은 시간 동안 공부해서 얻을 수 있는 최대 점수 구하기

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());


        int[] dp = new int[T + 1];

        // 단원 별 예상 공부 시간
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());

            // 중복 연산 없이하기위해 뒤에서부터 dp
            for (int t = T; t >= time; t--) {
                dp[t] = Math.max(dp[t], dp[t - time] + score);
            }

        }

        System.out.println(dp[T]);
    }

}