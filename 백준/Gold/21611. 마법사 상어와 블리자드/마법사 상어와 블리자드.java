import java.io.*;
import java.util.*;

public class Main {

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int[] dr2 = {0, 1, 0, -1};
    static int[] dc2 = {-1, 0, 1, 0};

    static int[][] map, origin;
    static int[] beads;
    static int N, M, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        origin = new int[N][N];
        beads = new int[N * N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                origin[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        int sharkR = N/2, sharkC = N/2;
        search(sharkR, sharkC);

        // 블리자드 M번 반복
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            int[] colorCnt = new int[4];

            // 1. 얼음파편 던지기 (직선 거리 삭제)
            for (int j = 1; j <=s; j++) {
                int nr = sharkR + (dr[d]) * j;
                int nc = sharkC + (dc[d]) * j;

                int idx = map[nr][nc];
                beads[idx] = 0;
            }


            // 1-1. 당기기
            movedBeads();

            // 2. 구슬 폭발 (연속된 구슬 4개 이상 폭발 대상, 같은 번호가 연속)
            while (true) {
                boolean isExploded = false;
                int start = 1; // 같은 구슬 시작점
                for (int j = 1; j < beads.length; j++) {
                    if (beads[start] != beads[j]) {

                        if (j - start >= 4) {

                            isExploded = true;
                            for (int k = start; k < j; k++) {
                                colorCnt[beads[k]]++;
                                beads[k] = 0;
                            }
                        }
                        start = j;
                    }
                }

                // 더이상 폭발하지 않을때까지 반복
                if (!isExploded) break;

                // 2-1. 당기기
                movedBeads();

            }

            // 3. 구슬 변화 (그룹화, 칸 번호 순대로, (개수, 번호)로 새롭게 생성
            int start = 1; // 같은 구슬 시작점
            int idx = 1; // 새로운 구슬 배열 인덱스

            int[] tmpBeads = new int[beads.length];
            for (int j = 1; j < beads.length; j++) {
                // 하나의 그룹 생성
                if (beads[start] != beads[j]) {
                    int cnt = j - start; // 개수

                    tmpBeads[idx++] = cnt;
                    if (idx >= beads.length) break;

                    tmpBeads[idx++] = beads[start];
                    if (idx >= beads.length) break;

                    start = j;
                }
            }

            // 갱신
            beads = tmpBeads;
            updateOriginMap(sharkR, sharkC);

            // 점수 계산
            for (int j = 1; j <= 3; j++) {
                answer += colorCnt[j] * j;
            }

        }

        System.out.println(answer);

    }

    private static void movedBeads() {
        int left =1, right = 1;
        while (right < beads.length) {
            if (beads[left] == 0) {

                // 오른쪽이 빈칸이 아닌 구슬이 나올때까지
                while (right < beads.length && beads[right] == 0 ) {
                    right++;
                }

                // 당기기
                while (right < beads.length && beads[right] != 0) {
                    beads[left] = beads[right];
                    beads[right] = 0;

                    left++;
                    right++;
                }

            } else {
                left++;
                right++;
            }
        }
    }

    private static void search(int sharkR, int sharkC) {

        int r = sharkR, c = sharkC;
        int dir = 0, dist = 0;

        int num = 1;
        for (int j = 0; j < N * 2 - 1; j++) {

            // 거리 증가
            if (j % 2 == 0) {
                dist++;
            }

            for (int k = 0; k < Math.min(dist, N - 1); k++) {
                r += dr2[dir];
                c += dc2[dir];

                map[r][c] = num;
                beads[num++] = origin[r][c];
            }

            dir = (dir + 1) % 4;
        }
    }

    private static void updateOriginMap(int sharkR, int sharkC) {
        int[][] tmp = new int[N][N];
        int r = sharkR, c = sharkC;
        int dir = 0, dist = 0;

        int num = 1;
        for (int j = 0; j < N * 2 - 1; j++) {

            // 거리 증가
            if (j % 2 == 0) {
                dist++;
            }

            for (int k = 0; k < Math.min(dist, N - 1); k++) {
                r += dr2[dir];
                c += dc2[dir];

                origin[r][c] = beads[num++];
            }

            dir = (dir + 1) % 4;
        }
    }



}

