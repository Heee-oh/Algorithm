import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;

public class Main {

    static int[] number = {6, 2, 5, 5, 4, 5, 6, 3, 7, 6};
    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        FastReader fr = new FastReader();

        int T = fr.nextInt();

        String[] maxDp = new String[101];
        String[] minDp = new String[101];

        String maxValue = "9".repeat(100);
        minDp[0] = minDp[1] = "";
        minDp[2] = "1";

        maxDp[0] = maxDp[1] = "";
        maxDp[2] = "1";

        for (int i = 3; i <= 100; i++) {

            // 최댓값 배낭
            BigInteger base = BigInteger.ZERO;
            for (int j = 0; j < 10; j++) {
                // i개 - 숫자j 필요개수 < 0 이면 범위 초과, 1개는 만드는게 불가능
                if (i - number[j] < 0 || i - number[j] == 1) continue;


                // i개로 만드는 숫자 = dp[i개수 - j개수]로 만드는 최대 값 + j숫자
                String newNumber = maxDp[i - number[j]] + j;
                BigInteger newN = new BigInteger(newNumber);

                if (newNumber.startsWith("0")) continue;

                // 더 크다면 변경
                if (base.compareTo(newN) < 0) {
                    base = newN;
                }
            }
            maxDp[i] = base.toString();

            // 최솟값 배낭
            base = new BigInteger(maxValue);
            for (int j = 0; j < 10; j++) {
                if (i - number[j] < 0 || i - number[j] == 1) continue;

                String newNumber = minDp[i - number[j]] + j;
                BigInteger newN = new BigInteger(newNumber);

                if (newNumber.startsWith("0")) continue;

                if (base.compareTo(newN) > 0) {
                    base = newN;
                }
            }
            minDp[i] = base.toString();

        }

        while (T-- > 0) {
            int N =  fr.nextInt();

            sb.append(minDp[N])
                    .append(" ")
                    .append(maxDp[N])
                    .append("\n");
        }

        System.out.println(sb.toString().trim());

    }


    static class FastReader {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' ');

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }

            int val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }

        long nextLong() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' ');

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }

            long val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }
    }
}