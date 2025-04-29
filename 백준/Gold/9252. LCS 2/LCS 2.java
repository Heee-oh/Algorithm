import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    static int[][] LCS;
    static String str1;
    static String str2;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str1 = br.readLine();
        str2 = br.readLine();

        int str1Len = str1.length() + 1;
        int str2Len = str2.length() + 1;

        LCS = new int[str1Len][str2Len];


        for (int i = 1; i < str1Len; i++) {
            for (int j = 1; j < str2Len; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    LCS[i][j] = LCS[i - 1][j - 1] + 1;

                } else {
                    LCS[i][j] = Math.max(LCS[i - 1][j], LCS[i][j - 1]);
                }
            }
        }

        System.out.println(LCS[str1Len - 1][str2Len - 1]);

        dfs(str1Len - 1, str2Len - 1, LCS[str1Len - 1][str2Len - 1]);
        System.out.print(sb.reverse().toString());
    }

    static StringBuilder sb = new StringBuilder();
    // 대각 위 , 왼, 위
    static int[] dx = {-1, -1, 0};
    static int[] dy = {-1, 0, -1};

    private static void dfs(int r, int c, int length) {

        if (r < 0 || c < 0 || length <= 0) return;

        for (int i = 0; i < 3; i++) {
            int nextR = r + dy[i];
            int nextC = c + dx[i];

            if (nextR < 0 || nextC < 0) continue;

            // 같은 값을 가진 이전 위치가 존재한다면 그 위치로 이동
            if (length == LCS[nextR][nextC]) {
                dfs(nextR, nextC, length);
                return;
            }
        }

        sb.append(str1.charAt(r - 1));
        dfs(r - 1, c - 1, length - 1);


    }

}