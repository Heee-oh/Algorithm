import java.io.*;
import java.util.*;

public class Main {

    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    
    
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        StringBuilder sb = new StringBuilder();
        int[] moveMask = new int[9];

        for (int i = 0; i < 9; i++) {
            int r= i / 3;
            int c = i % 3;
            int m = 0;

            m |= (1 << (r * 3 + c)); // 현재 선택 위치 마킹

            // 상하좌우 마킹
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= 3 || nc < 0 || nc >= 3) continue;
                m |= (1 << (nr * 3 + nc));
            }
            
            moveMask[i] = m; // i번 선택했을때 비트 
        }


        int[] dist = new int[1 << 9];
        Arrays.fill(dist, -1);

        Queue<Integer> q = new LinkedList<>();
        dist[0] = 0;
        q.add(0);

        while (!q.isEmpty()) {
            int cur = q.poll();
            int curDist = dist[cur];

            // 현재 비트 (맵상태)에 9가지중 하나 선택
            for (int i = 0; i < 9; i++) {
                int next = cur ^ moveMask[i];
                if (dist[next] != -1) continue;
                dist[next] = curDist + 1;
                q.add(next);
            }
        }


        int P = fr.nextInt();
        while (P--> 0) {
            int target = 0;
            for (int i = 0; i < 3; i++) {
                String str = fr.next();
                for (int j = 0; j < 3; j++) {
                    if (str.charAt(j) == '*') {
                        target |= (1 << (i * 3 + j));
                    }
                }

            }
            sb.append(dist[target]).append("\n");
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
