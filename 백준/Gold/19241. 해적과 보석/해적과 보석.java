import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        StringBuilder sb = new StringBuilder();
        int T = fr.nextInt();
        
        while (T-- > 0) {
            int N = fr.nextInt();
            long[][] boxes = new long[N][3];

            for (int i = 0; i < N; i++) {
                boxes[i][0] = fr.nextInt();
                boxes[i][1] = fr.nextInt();
                boxes[i][2] = boxes[i][0] + boxes[i][1];
            }

            Arrays.sort(boxes, (o1, o2) -> Long.compare(o2[2], o1[2]));

            long total = 0;
            for (int i = 0; i < N; i++) {
                if (i % 2 == 0) {
                    total += boxes[i][0];
                } else {
                    total -= boxes[i][1];
                }
            }

            sb.append(total).append("\n");
        }

        System.out.print(sb.toString().trim());

    }



    // 메모리 효율을 극대화한 입력 클래스
    private static class FastReader {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1024 * 16];
        private int ptr = 0;
        private int len = 0;

        private int read() throws IOException {
            if (ptr < len) return buffer[ptr++];
            len = in.read(buffer);
            ptr = 0;
            if (len <= 0) return -1;
            return buffer[ptr++];
        }

        public String next() throws IOException {
            int b = read();
            while (b != -1 && b <= 32) b = read();
            if (b == -1) return null;
            StringBuilder sb = new StringBuilder();
            while (b > 32) {
                sb.append((char) b);
                b = read();
            }
            return sb.toString();
        }

        public int nextInt() throws IOException {
            int n = 0;
            int b = read();
            while (b != -1 && b <= 32) b = read();

            // -도 처리
            int sign = 1;
            if (b =='-') {
                sign = -1;
                b = read();
            }
            while (b > 32) {
                n = n * 10 + (b - '0');
                b = read();
            }
            return n * sign;
        }
    }

}
