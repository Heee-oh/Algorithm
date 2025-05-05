import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] boxes = new int[n];
        int[] dp = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            boxes[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1; // 각 상자들은 현재 최대 1개의 상자를 넣을 수 있다 (즉 자기 자신)
        }

        int max = 0;

        for (int i = 1; i < n; i++) {
            // i번째 이전 즉, 앞의 박스들과 비교하여 크기가 작은 상자들을 넣는다.
            for (int j = i - 1; j >= 0; j--) {
                if (boxes[j] < boxes[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            // 이전에 구한 상자개수가 최댓값일 수 있으므로 max를 갱신
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }

}