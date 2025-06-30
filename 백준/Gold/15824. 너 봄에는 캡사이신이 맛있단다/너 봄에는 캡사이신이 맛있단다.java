import java.io.*;
import java.util.*;

public class Main {

    static final long MOD = 1_000_000_007;
    // 계산은 long
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] arr = new long[n];
        long[] pow = new long[300001];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        pow[0] = 1;
        for (int i = 1; i < pow.length; i++) {
            pow[i] = (pow[i - 1] * 2) % MOD;
        }

        Arrays.sort(arr);

        long sum = 0;
        for (int i = 0; i < n; i++) {
            long maxContribution = (arr[i] * pow[i]) % MOD;
            long minContribution = (arr[i] * pow[n - 1 - i]) % MOD;

            sum = (sum + maxContribution - minContribution + MOD) % MOD;
        }

        System.out.println(sum);


    }

}
