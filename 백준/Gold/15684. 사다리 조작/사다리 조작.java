import java.io.*;
import java.util.*;

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
        
        dfs(1, 1, 0);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);


    }

    static int answer = Integer.MAX_VALUE;

    private static void dfs(int ladder, int r, int depth) {
        if (answer <= depth) {
            return;
        }
        
        if (validLadder()) {
            answer = Math.min(answer, depth);
            return; // 더 깊어지면 결국 depth가 늘어나기에 의미없음
        }

        if (depth >= 3) {
            return;
        }

        // 사다리 세로
        for (int c = ladder; c < N; c++) {
            if (r >= H) r = 1;
            // 가로
            for (; r <= H; r++) {
                if (ladderMap[r][c] // 현재 가로 체크
                        || ladderMap[r][c - 1]  // 이전 사다리 가로 체크
                        || (c + 1 <= N &&  ladderMap[r][c+1])) { // 앞의 사다리 가로 체크
                    continue;
                }

                ladderMap[r][c] = true; // 사다리 설치
                dfs(ladder, r+1,depth + 1);
                ladderMap[r][c] = false; // 사다리 철거
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
