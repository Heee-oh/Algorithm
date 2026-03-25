import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.stream.IntStream;

public class Main {

    static int[] parent;
    static int[] cost;
    static int[] sum;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        FastReader fr = new FastReader();


        int N = fr.nextInt();
        int M = fr.nextInt();
        int K = fr.nextInt();

        parent = IntStream.range(0, N + 1).toArray();
        cost = new int[N + 1];
        sum = new int[N + 1];
        int[] dp = new int[K + 1];

        for (int i = 1; i <= N; i++) {
            cost[i] = fr.nextInt();
        }

        // union-set 으로 집합 생성
        for (int i = 0; i < M; i++) {
            int a = fr.nextInt();
            int b = fr.nextInt();

            union(a, b);
            
        }

        int[] count = new int[N + 1]; // 각 집합의 대표가 해당 집합의 개수를 가짐
        TreeSet<Integer> ts = new TreeSet<>(); // 집합 대표 수 저장

        for (int i = 1; i <= N; i++) {
            int p = find(i);
            count[p]++; // 대표 수 카운팅

            sum[p] += cost[i]; // 해당 집합의 사탕 수 더하기
            ts.add(p); // 대푯값 기록
        }

        int[] num = new int[ts.size()]; // 각 집합의 대푯값들

        int idx = 0;
        for (Integer t : ts) {
            num[idx++] = t;
        }


        // 배낭문제 0/1로 처리
        for (int i = 0; i < num.length; i++) {
            int cur = num[i]; // 각 집합의 대푯값을 꺼냄

            for (int k = K - 1; k >= count[cur]; k--) {
                dp[k] = Math.max(dp[k], dp[k - count[cur]] + sum[cur]); // dp[k - 해당집합 개수] + 해당 집합의 사탕개수
            }
        }

        System.out.println(dp[K-1]);
    }


    private static void union(int a, int b) {

        a = find(a);
        b = find(b);

        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }

    }

    private static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
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