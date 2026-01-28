import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        FastReader fr = new FastReader();
        int t = fr.nextInt();

        while (t-- > 0) {
            int N = fr.nextInt();
            Set<String> set = new HashSet<>();
            boolean flag = false;
            String[] arr = new String[N];

            for (int i = 0; i < N; i++) {
                arr[i] = fr.next();
            }

            // 길이가 짧은 순으로 정렬
            Arrays.sort(arr, ((o1, o2) -> o1.length() - o2.length()));

            for (String number : arr) {
                for (int j = 0; j < number.length(); j++) {
                    String str = number.substring(0, j + 1);

                    if (set.contains(str)) {
                        flag = true;
                        break;
                    }
                }

                set.add(number);

            }

            if (flag) {
                sb.append("NO\n");
            } else {
                sb.append("YES\n");
            }
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
