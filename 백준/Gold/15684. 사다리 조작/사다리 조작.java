import java.io.*;
import java.util.*;
import java.util.jar.JarEntry;

public class Main {
    //  15684번 사다리 조작

    static int N, M, H;
    static boolean[][] ladderMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        // 연결 여부는 왼쪽 사다리 기준 H개의 가로선, N개의 세로선
        ladderMap = new boolean[H + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // a 가로선
            int b = Integer.parseInt(st.nextToken()); // b세로선

            ladderMap[a][b] = true;
        }


        if (validLadder()) {
            System.out.println(0);
            return;
        }

        for (int i = 1; i <= 3; i++) {
            limit = i;
            dfs(1, 0);

            if (answer != Integer.MAX_VALUE) {
                System.out.println(answer);
                return;
            }
        }


        System.out.println(-1);


    }

    static int answer = Integer.MAX_VALUE;
    static int limit;

    private static void dfs(int row, int depth) {
        if (answer != Integer.MAX_VALUE) {
            return;
        }

        if (depth == limit) {
            if (validLadder()) {
                answer = depth;
            }
            return; // 더 깊어지면 결국 depth가 늘어나기에 의미없음
        }


        for (int i = row; i <= H; i++) {
            for (int j = 1; j < N; j++) {
                // 현재 위치, 왼쪽, 오른쪽에 사다리가 없는지 확인
                if (ladderMap[i][j] || ladderMap[i][j - 1] ||  ladderMap[i][j + 1]) {
                    continue;
                }

                ladderMap[i][j] = true;      // 사다리 놓기
                dfs(i, depth + 1);
                ladderMap[i][j] = false;     // 백트래킹 (사다리 치우기)
            }
        }

    }

    private static boolean validLadder() {
        for (int ladder = 1; ladder <= N; ladder++) {
            int r = 1, c = ladder;

            while (r <= H) {
                // 이전 세로선에 가로선이 있다면
                if (ladderMap[r][c - 1]) {
                    c--;

                    // 현재 세로선에 가로선이 있다면
                } else if (ladderMap[r][c]) {
                    c++;
                }
                r++;
            }

            if (c != ladder) {
                return false;
            }
        }

        return true;
    }
}
