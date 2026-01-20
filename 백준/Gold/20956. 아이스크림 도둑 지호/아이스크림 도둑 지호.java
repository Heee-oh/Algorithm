import java.io.*;
import java.util.*;

public class Main {
    // n/2 절반만 체인지 진행
    //

    static class IceCream {
        int amount;
        int num;

        public IceCream(int amount, int num) {
            this.amount = amount;
            this.num = num;
        }
    }

    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        StringBuilder sb = new StringBuilder();

        int N = fr.nextInt();
        int M = fr.nextInt();
        boolean[] visited = new boolean[N + 1];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> {
            if (b[0] == a[0]) return a[1] - b[1];
            return b[0] - a[0];
        });

        PriorityQueue<int[]> reversePq = new PriorityQueue<>((a,b) -> {
            if (b[0] == a[0]) return a[1] - b[1];
            return b[0] - a[0];
        });

        IceCream[] iceCreams = new IceCream[N + 1];
        IceCream[] iceCreamsReverse = new IceCream[N + 1];

        for (int i = 1; i <= N; i++) {
            int amount = fr.nextInt();
            iceCreams[i] = new IceCream(amount, i);
            iceCreamsReverse[N - i + 1] = new IceCream(amount, i);

            pq.add(new int[] {amount, i});
            reversePq.add(new int[]{amount, N - i + 1});
        }

        boolean mintFlag = false;

        // 양이 가장 많은 앙이스크림순으로
        while (M > 0) {
            int[] next;
            int idx, number;

            if (mintFlag) {
                next = reversePq.poll();
                idx = next[1];
                number = iceCreamsReverse[idx].num;

            } else {
                next = pq.poll(); // 양, 인덱스
                idx = next[1];
                number = iceCreams[idx].num;
            }


            if (visited[number]) continue;
            M--;
            visited[number] = true;

            sb.append(number).append("\n");
            visited[number] = true;


            // 민트라면 같은 양이 가지는 여러 idx들의 우선순위 교체
            if (isMint(next[0])) {
                mintFlag = !mintFlag;
            }

        }

        System.out.print(sb.toString().trim());

    }

    private static boolean isMint(int type) {
        return type % 7 == 0;
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
            if (b == -1) return -1;
            while (b > 32) {
                n = n * 10 + (b - '0');
                b = read();
            }
            return n;
        }
    }

}
