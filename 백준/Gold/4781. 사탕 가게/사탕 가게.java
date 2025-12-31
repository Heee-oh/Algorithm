import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            String line = br.readLine();
            if (line.equals("0 0.00")) break;

            StringTokenizer st = new StringTokenizer(line);
            int N = Integer.parseInt(st.nextToken());
            int M = (int) Math.round(Double.parseDouble(st.nextToken()) * 100.0);

            int[] dp = new int[M+1];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int c = Integer.parseInt(st.nextToken());
                int p = (int) Math.round(Double.parseDouble(st.nextToken()) * 100.0);

                for (int j = p; j <= M; j++) {
                    dp[j] = Math.max(dp[j], dp[j - p] + c);
                }
            }
            sb.append(dp[M]).append("\n");
        }


        System.out.print(sb.toString());
    }
}
