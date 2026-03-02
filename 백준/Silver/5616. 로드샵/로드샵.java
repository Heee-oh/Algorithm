import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {

    static final int MAX = (int) 1e6;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long n = Long.parseLong(st.nextToken());
        long m = Long.parseLong(st.nextToken());
        long r = Long.parseLong(st.nextToken());

        // x1 + x2 ... xn = r , xi >= m // 0이상으로 변경하기 위해 m을 이동
        // yi = xi -m (>= 0)
        // xi = yi + m // x를 다 이거로 치환
        // y1 + y2 .. yn = r - nm (m이 n개)

        long remain = r - n * m; // 0 미만이라면 최소 m 개 조합으로 만들기 불가 판정
        if (remain < 0) {
            System.out.println(0);
            return;
        }

        // 중복 조합 공식 적용
        long N = remain + n - 1;   // (r-nm)+n-1
        long K = n - 1;            // n-1


        System.out.println(comb(N, K));
    }

    private static BigInteger comb(long n, long k) {
        if (k < 0 || k > n) return BigInteger.ZERO;
        if (k == 0 || k == n) return BigInteger.ONE;

        k = Math.min(k, n - k); // nCk = nC(n-k)
        BigInteger res = BigInteger.ONE;
        
        // ((n-1) * (n-2).. *(n-k + i)) / (1 * 2 ..k)
        for (long i = 1; i <= k; i++) {
            long num = n - k + i;
            res = res.multiply(BigInteger.valueOf(num))
                    .divide(BigInteger.valueOf(i));


        }
        return res;

    }

        static class FastReader {
        private final StringBuilder sb = new StringBuilder();
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


        String next() throws IOException {
            sb.delete(0, sb.length());
            int c = read();
            while (c != '\n') {
                sb.append((char) c);
                c = read();
            }

            return sb.toString();
        }

        int nextInt() throws IOException {
            int c = read();
            while (c <= 32) c = read(); // 특수문자 공백등 무시
            int sign = 1;
            if (c == '-') {sign = -1; c = read();}
            int val = 0;

            while (c > 32) { // 숫자면 계속 읽음
                val = val * 10 + (c - '0');
                c = read();
            }

            return val * sign;
        }
    }


}
