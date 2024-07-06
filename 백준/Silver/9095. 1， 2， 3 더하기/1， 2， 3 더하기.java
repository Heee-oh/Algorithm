import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;



public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        int[] DP = new int[12];
        DP[1] = 1;
        DP[2] = 2;
        DP[3] = 4;

        for (int j = 4; j < 12; j++) {
            DP[j] = DP[j - 1] + DP[j - 2] + DP[j - 3];
        }

        for (int i = 1; i <= T; i++) {
            int n = Integer.parseInt(br.readLine());
            System.out.println(DP[n]);
        }

    }

}
