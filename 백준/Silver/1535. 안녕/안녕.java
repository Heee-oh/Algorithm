import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    // 세준이의 체력 100, 기쁨 0
    // 체력을 기준으로 dp 점화식을 세움
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] hp = new int[N];
        int[] joy = new int[N];
        int[] dp = new int[101];

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            hp[i] = Integer.parseInt(st1.nextToken());
            joy[i] = Integer.parseInt(st2.nextToken());
        }


        for (int i = 0; i < N; i++) {

            for (int j = 100; j > hp[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - hp[i]] + joy[i]);
            }

        }

        System.out.println(dp[100]);
    }

}