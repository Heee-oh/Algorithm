import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    static final int MOD = 1000000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][][] dp = new int[n + 1][10][1<<10];

        for (int num = 1; num < 10; num++) {
            dp[1][num][1<<num] = 1;
        }

        // dp[len][num][bit] : 현재 길이, 뒤에 추가할 숫자, 추가했을 때 bit값 
        // 이전 길이의 num +-1 인 계단수의 비트값을 더한 값이 현재 길이의 num을 뒤에 추가했을때 bit값
        for (int len = 2; len <= n; len++) {
            for (int num = 0; num < 10; num++) {
                for (int mask = 0; mask < 1024; mask++) {
                    int bit = mask | (1 << num);
                    if (num == 0) {
                        dp[len][num][bit] = (dp[len][num][bit] + dp[len - 1][num + 1][mask]) % MOD;
                    } else if (num == 9) {
                        dp[len][num][bit] = (dp[len][num][bit] + dp[len - 1][num - 1][mask]) % MOD;
                    } else {
                        dp[len][num][bit] = (dp[len][num][bit] + dp[len - 1][num - 1][mask] + dp[len - 1][num + 1][mask]) % MOD;
                    }
                }
            }
        }
        
        long sum = 0;
        for (int i = 0; i < 10; i++) {
            sum = (sum + dp[n][i][1023]) % MOD;
        }
        
        System.out.println(sum);
    }
}
