import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        StringBuilder sb = new StringBuilder();
        int N = fr.nextInt();

        int[] arr = new int[N + 2];
        int[] LIS = new int[N + 2];
        int[] pos = new int[N + 2];
        int[] parent = new int[N + 2];

        // sentinel for LNDS
        LIS[0] = Integer.MIN_VALUE;
        pos[0] = 0;

        for (int i = 1; i <= N; i++) {
            arr[i] = fr.nextInt();
        }
        arr[N + 1] = 1_000_000_000;

        int s = 0, e = 0;
        for (int i = 1; i <= N + 1; i++) {

            if (LIS[e] <= arr[i]) {
                LIS[++e] = arr[i];
                pos[e] = i;
                parent[i] = pos[e - 1];
            } else {
                int ts = s, te = e;
                while (ts < te) {
                    int mid = (ts + te) >>> 1;
                    if (LIS[mid] <= arr[i]) ts = mid + 1;
                    else te = mid;
                }
                parent[i] = pos[ts - 1];
                pos[ts] = i;
                LIS[ts] = arr[i];
            }
        }

        int changes = (N + 1) - e;
        if (changes > 3) {
            System.out.print("NO\n");
            return;
        }

        boolean[] keep = new boolean[N + 2];
        int cur = pos[e];
        while (cur != 0) {
            keep[cur] = true;
            cur = parent[cur];
        }

        ArrayList<int[]> ops = new ArrayList<>();
        for (int i = N; i >= 1; i--) {
            if (keep[i]) continue;
            arr[i] = arr[i + 1];
            ops.add(new int[]{i, arr[i]});
        }

        sb.append("YES\n");
        sb.append(ops.size()).append('\n');
        for (int[] op : ops) {
            sb.append(op[0]).append(' ').append(op[1]).append('\n');
        }
        System.out.print(sb.toString());
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
