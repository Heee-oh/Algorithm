import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[1001];

        dp[1] = 1; //2x1 로 하나 채우기
        dp[2] = 2; // 2x1로 || 1x2로 2가지 방법

        for(int i = 3; i <= n; i++) {
            dp[i] = (dp[i-2] + dp[i-1]) % 10007;
        }



        bw.write(dp[n] + "\n"); // 문자열 or 변수명+""
        // 백준은 값도 줄바꿈 처리되어있으면 해줘야함
        bw.flush();  //버퍼 비우기  이것을 계속 호출하면 시간초과 뜸 한번만 아니면 필요할때만 호
        bw.close(); // 종료
    }
}
