import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        String str2 = br.readLine();

        int str1Len = str1.length();
        int str2Len = str2.length();

        int[][] LCS = new int[str1Len + 1][str2Len + 1];
        int t = 0;

        for (int i = 1; i <= str1Len; i++) {
            for (int j = 1; j <= str2Len; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    LCS[i][j] = LCS[i-1][j-1] + 1;
                }

                t = Math.max(LCS[i][j], t);
            }
        }

        System.out.println(t);


    }
}
