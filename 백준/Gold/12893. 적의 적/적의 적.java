import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        FastReader fr = new FastReader();
        int N = fr.nextInt();
        int M = fr.nextInt();

        List<Integer>[] graph = new List[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= M; i++) {
            int A = fr.nextInt();
            int B = fr.nextInt();

            graph[A].add(B);
            graph[B].add(A);
        }



        Queue<Integer> q = new LinkedList<>();
        int[] friend = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            if (friend[i] != 0) continue;
            q.offer(i);
            friend[i] = 1; // 0 미방문, 1 친구, 2 적

            while (!q.isEmpty()) {
                int A = q.poll();

                for (int B : graph[A]) {
                    if (friend[B] == 0) {
                        friend[B] = 3 - friend[A];
                        q.offer(B);
                        continue;
                    }

                    if (friend[B] == friend[A]) {
                        System.out.println(0);
                        return;
                    }

                }
            }

        }

        System.out.println(1);

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
                sb.append((char)c);
                c = read();
            }
            return sb.toString();
        }
        int nextInt() throws IOException {
            int c = read();
            while (c <= 32) c = read();
            int sign = 1;
            if (c == '-') { sign = -1; c = read(); }
            int val = 0;
            while (c > 32) {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }
        long nextLong() throws IOException {
            int c = read();
            while (c <= 32) c = read();
            int sign = 1;
            if (c == '-') { sign = -1; c = read(); }
            long val = 0;
            while (c > 32) {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }
    }
}
