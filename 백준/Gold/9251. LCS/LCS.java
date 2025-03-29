import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[] str1 = br.readLine().toCharArray();
        char[] str2 = br.readLine().toCharArray();

        int[][] dp = new int[str1.length + 1][str2.length + 1];
        for (int i = 1; i <= str1.length; i++) {
            for (int j = 1; j <= str2.length; j++) {
                if (str1[i-1] == str2[j-1]) { // 서로 같은 문자라면
                    dp[i][j] = dp[i - 1][j - 1] + 1; // 이전 문자까지의 공통 문자 개수에 하나를 더 추가한다.
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]); // 같지 않다면 이전 문자의 공통 문자개수를 유지한다.
                }
            }
        }


        bw.write(dp[str1.length][str2.length] + "");
        bw.flush();
        bw.close();

    }



}
