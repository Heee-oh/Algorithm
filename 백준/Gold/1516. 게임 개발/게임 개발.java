import java.io.*;
import java.util.*;


public class Main {


    static int[] dp;
    static int[] w;
    static int N, INF = (int) 1e8;;

    static List<Integer>[] in;
    static List<Integer>[] out;

    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        StringBuilder sb = new StringBuilder();
        N = fr.nextInt();

        in = new ArrayList[N+1];
        out = new ArrayList[N+1];
        dp = new int[N + 1];
        w =  new int[N + 1];


        for (int i = 1; i <= N; i++) {
            in[i] = new ArrayList<>();
            out[i] = new ArrayList<>();
        }


        Arrays.fill(dp, INF);

        Queue<Integer> q = new LinkedList<>();
        // 초기화
        for (int i = 1; i <= N; i++) {
            int v = fr.nextInt();
            w[i] = v;

            while ((v = fr.nextInt()) != -1) {
                in[i].add(v);
                out[v].add(i);
            }

            if (in[i].isEmpty()) {
                dp[i] = w[i];
            }
        }


        for (int i = 1; i <= N; i++) {
            if (in[i].isEmpty()) continue;
            dfs(i);
        }



        for (int i = 1; i <= N; i++) {
            sb.append(dp[i]).append("\n");
        }

        System.out.println(sb.toString().trim());


    }

    private static int dfs(int cur) {
        if (dp[cur] != INF) {
            return dp[cur];
        }

        int max = 0;
        for (Integer need : in[cur]) {
            if (dp[need] == INF) {
                max = Math.max(max, dfs(need));
            } else {
                max = Math.max(max, dp[need]);
            }
        }

        return dp[cur] = max + w[cur];
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