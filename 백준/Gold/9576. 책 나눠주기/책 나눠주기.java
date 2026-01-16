import java.io.*;
import java.util.*;

public class Main {

    static int[] parent;

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[][] req = new int[M][2];
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                req[i][0] = Integer.parseInt(st.nextToken());
                req[i][1] = Integer.parseInt(st.nextToken());
            }

            // 끝점 e 오름차순, 같으면 s 오름차순
            Arrays.sort(req, (a, b) -> {
                if (a[1] == b[1]) return a[0] - b[0];
                return a[1] - b[1];
            });

            // 1..N, 그리고 N+1은 더 이상 책 없음
            parent = new int[N + 2];
            for (int i = 1; i <= N + 1; i++) parent[i] = i;

            int answer = 0;

            for (int i = 0; i < M; i++) {
                int s = req[i][0];
                int e = req[i][1];

                int x = find(s);   // s 이상에서 가장 작은 미사용 책
                if (x <= e) {      // 구간 안이면 배정 가능
                    answer++;
                    parent[x] = find(x + 1); // x 사용 처리
                }
            }

            sb.append(answer).append('\n');
        }

        System.out.print(sb.toString());
    }
}
