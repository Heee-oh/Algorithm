import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        StringBuilder sb = new StringBuilder();
        int N = fr.nextInt();
        int M = fr.nextInt();


        List<Integer>[] out = new ArrayList[N + 1];
        TreeSet<Integer> ts = new TreeSet<>();
        int[] in = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            out[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int A = fr.nextInt();
            int B = fr.nextInt();

            out[A].add(B);
            in[B]++;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 1; i <= N; i++) {
            if (in[i] == 0) {
                pq.offer(i);
            }
        }

        while (!pq.isEmpty()) {
            int cur = pq.poll();

            sb.append(cur).append(" ");

            for (int next : out[cur]) {
                in[next]--; // 위상정렬에서 선 작업 처리
                if (in[next] != 0) continue;
                pq.offer(next);
            }
        }

        System.out.println(sb.toString().trim());







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
