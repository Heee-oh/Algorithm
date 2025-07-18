import java.io.*;
import java.util.*;

public class Main {
    static int N, K, L;
    static boolean[][] apples;
    static int[][] map;

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    static class Snake {
        int r, c, time, dir;

        public Snake(int r, int c, int time, int dir) {
            this.r = r;
            this.c = c;
            this.time = time;
            this.dir = dir;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        map = new int[N + 1][N + 1];
        apples = new boolean[N + 1][N+1];


        // 사과 초기화
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            apples[r][c] = true;
        }

        // 방향 변환 초기화
        L = Integer.parseInt(br.readLine());


        Map<Integer, Integer> moveDir = new HashMap<>();

        for (int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());

            if (st.nextToken().equals("D")) {
                moveDir.put(time, 1);
            } else {
                moveDir.put(time, -1);
            }
        }



        // 주어진 시간까지 이동
        Deque<Snake> dq = new ArrayDeque<>();
        dq.addLast(new Snake(1, 1, 0, 1));
        map[1][1] = 1;
        while (true) {

            int size = dq.size();
            boolean isApplePos = false;
            // 머리가 벽에 부딪혔을 경우 체크
            Snake head = dq.peek();

            int nextDir = head.dir;
            // 방향 전환 여부 확인
            if (moveDir.containsKey(head.time)) {
                int dirIdx = moveDir.get(head.time);
                nextDir = (head.dir + dirIdx + 4) % 4;
            }

            int headR = head.r + dr[nextDir];
            int headC = head.c + dc[nextDir];

            // 1 ~ N 범위 맵
            if (headR > N || headR <= 0
                    || headC > N || headC <= 0
                    || map[headR][head.c] > 1 // 현 위치가 몸에 부딪힌 상태
                    || map[headR][headC] >= 1) {  // 다음이동시 부딪힐 예정이라면

                System.out.println(head.time + 1);
                return;
            }

            //  다음 위치에 사과가 있다면
            if (apples[headR][headC]) {
                apples[headR][headC] = false; // 먹음 처리
                isApplePos = true;
            }

            int tailOriginDir = 0;

            // 뱀 이동
            while (size-- > 0) {
                Snake cur = dq.poll();

                if (size == 0) {
                    tailOriginDir = cur.dir;
                }

                // 방향 전환 여부 확인
                if (moveDir.containsKey(cur.time)) {
                    int dirIdx = moveDir.get(cur.time);
                    cur.dir = (cur.dir + dirIdx + 4) % 4;
                }

                int nextR = cur.r + dr[cur.dir];
                int nextC = cur.c + dc[cur.dir];


                map[cur.r][cur.c]--;
                map[nextR][nextC]++;

                dq.addLast(new Snake(nextR, nextC, cur.time + 1, cur.dir));
            }


            // 사과 먹음 처리
            if (isApplePos) {
                Snake tail = dq.peekLast();
                int tailR = tail.r - dr[tail.dir];
                int tailC = tail.c - dc[tail.dir];
                map[tailR][tailC]++; // 꼬리 위치

                dq.addLast(new Snake(tailR, tailC, tail.time - 1, tailOriginDir));
            }



        }





    }
}
