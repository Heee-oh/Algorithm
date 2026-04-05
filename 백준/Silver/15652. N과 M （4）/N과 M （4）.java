import java.io.*;
import java.util.*;


public class Main {

    static int n;
    static int m;
    static StringBuilder sb = new StringBuilder();
    static StringBuilder answer = new StringBuilder();
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        StringBuilder sb = new StringBuilder();

        n = fr.nextInt();
        m = fr.nextInt();

        dfs(1, 0);

        System.out.print(answer.toString().trim());

    }
    private static void dfs(int num, int depth) {

        if (depth == m) {
            sb.append("\n");
            answer.append(sb.toString());
            sb.deleteCharAt(sb.length() - 1);
            return;
        }
        for (int i = num; i <= n; i++) {
            sb.append(i).append(" ");
            dfs(i, depth+1);
            sb.delete(sb.length() - 2, sb.length());

        }
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