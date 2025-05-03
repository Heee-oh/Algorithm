import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        if (n % 2 != 0) {
            System.out.println(0);
            return;
        }

        int[] dp = new int[n + 1];
        dp[0] = 1; // 아무것도 두지 않으면 되기때문에 1가지 경우의 수가 있다.
        dp[2] = 3;

        // 이전 i-2 도형에 3가지(dp[2])를 오른쪽에 붙이는 모든 경우
        for (int i = 4; i <= n; i+= 2) {
            dp[i] = dp[i-2] * 3;

            // dp[j]: 남은 왼쪽 영역을 채우는 방법의 수
            // i-2k (k >= 2, k += 2) 개수 * (2k 크기의 도형의 특수한 구조  2가지)
            // 각 dp[j]에 대해 오른쪽 2k 칸(i-j)을 특수한 구조 2가지를 붙일 수 있음
            for (int j = i - 4; j >= 0; j -= 2) {
                dp[i] += dp[j] * 2;
            }
        }

        System.out.println(dp[n]);
    }

}