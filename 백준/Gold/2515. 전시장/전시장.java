import java.io.*;
import java.util.Arrays;

public class Main {

    static class Painting {
        int h, price;

        public Painting(int h, int price) {
            this.h = h;
            this.price = price;
        }
    }
    static Painting[] arr;
    static int[] dp;
    static int N, S;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();

        N = fr.nextInt();
        S = fr.nextInt();

        arr = new Painting[N];
        for (int i = 0; i < N; i++) {
            arr[i] = new Painting(fr.nextInt(), fr.nextInt());
        }

        Arrays.sort(arr, (o1, o2) -> Integer.compare(o1.h, o2.h));

        dp = new int[N];
        dp[0] = arr[0].price;

        for (int i = 1; i < N; i++) {
            int j = findLastIndex(i); // 이분 탐색으로 이전 놓기 가능한 그림을 찾음

            int pick = arr[i].price;
            if (j != -1) pick += dp[j]; // 존재한다면 그 값을 더함

            // 현재 그림 고르지 않기, 고르기 중 큰 값 선택
            dp[i] = Math.max(dp[i - 1], pick);

        }

        System.out.println(dp[N - 1]);
    }


    static int findLastIndex(int i) {
        int target = arr[i].h - S;
        int left = 0, right = i - 1;
        int ans = -1;

        while (left <= right) {
            int mid = (left + right) >>> 1;

            if (arr[mid].h <= target) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return ans;
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