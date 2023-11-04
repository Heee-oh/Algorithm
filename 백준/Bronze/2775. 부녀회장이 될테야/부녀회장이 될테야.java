import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        int dp[][] = new int[15][14];      // 층 / 호
            // 호는 -1된 상태 층은 0~14층까지 정상적으로 인덱스 적용

        for(int i = 0; i < 14; i++) { //0~13 호까지
            dp[0][i] = i + 1 ; // 0층을 다 초기화
        }
        for(int i = 1; i < 15; i++) {  // 0층을 제외한 1~14층까지 1호 초기화
            dp[i][0] = 1; // 1호를 1로
        }

        // 현재 층은 이전 층의 총합
        for(int i = 1; i < 15; i++) {
            for(int j = 1; j < 14; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }


        }
        while(T--> 0) {
            int k = Integer.parseInt(br.readLine());  // 층
            int n = Integer.parseInt(br.readLine()) - 1; // 호
            bw.write(dp[k][n] +"\n");
            bw.flush();
        }





        bw.close();
    }
}