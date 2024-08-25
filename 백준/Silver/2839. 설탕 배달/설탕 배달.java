import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int num = Integer.parseInt(br.readLine());
        int[] dp = new int[50001];
        dp[1] = dp[2] = dp[4] = dp[7] = -1;
        dp[3] = dp[5] = 1;
        dp[6] = 2;


        if (num > 7) {
            dp[1] = dp[2] = dp[4] = dp[7]= 100;
        }

        for (int i = 8; i <= num; i++) {

            dp[i] = Math.min(dp[i - 3], dp[i - 5]) + 1;
        }

        bw.write(dp[num]+ "");
        bw.flush();
        bw.close();
    }


}