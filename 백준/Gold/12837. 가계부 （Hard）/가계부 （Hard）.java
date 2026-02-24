import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    static long[] tree;
    static int h;
    static int treeSize;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        StringBuilder sb = new StringBuilder();
        int N = fr.nextInt();
        int Q = fr.nextInt();

        h = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
        treeSize = (int) Math.pow(2, h);
        tree = new long[treeSize];
        arr = new int[N + 1];

        for (int i = 0; i < Q; i++) {
            int query = fr.nextInt();
            int p = fr.nextInt();
            int x = fr.nextInt();

            if (query == 1) {
                arr[p] = x;
                sum(1, 1, N, p);

            } else {
                long result = query(1, 1, N, p, x);
                sb.append(result).append("\n");
            }
        }

        System.out.print(sb.toString().trim());

    }



    private static void sum(int node, int s, int e, int idx) {
        if (idx < s || e < idx) return;

        tree[node] += arr[idx];
        if (s == e) return;


        int mid = (s + e) >>> 1;
        sum(node * 2, s, mid, idx);
        sum(node * 2 + 1, mid + 1, e, idx);

    }


    private static long query(int node, int s, int e, int l, int r) {
        if (e < l || r < s) return 0L;

        if (l <= s && e <= r) return tree[node];

        int mid = (s + e) >>> 1;

        return query(node * 2, s, mid, l, r)
                        + query(node * 2 + 1, mid + 1, e, l, r);
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
