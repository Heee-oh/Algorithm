import java.io.*;
import java.util.*;

public class Main {
    //  15684번 사다리 조작

    static int N;
    static boolean[][] map;
    static int[] dy = {0, -1, 0, 1};
    static int[] dx = {1, 0, -1, 0};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // 0 ~ N 까지
        map = new boolean[101][101];

        // 연결 여부는 왼쪽 사다리 기준 H개의 가로선, N개의 세로선
        for (int i = 0; i < N; i++) {
            Deque<Integer> dq = new ArrayDeque<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()); // 시작 방향
            int g = Integer.parseInt(st.nextToken()); // 세대

            int endY = y + dy[d], endX = x + dx[d];

            dq.addLast(-1); // 시작점, 시작점은 1-로 가정
            dq.addLast(d); // 끝지점으로 들어오는 방향을 저장

            map[y][x] = true;
            map[endY][endX] = true;

            int curY = endY, curX = endX;

            Queue<Integer> tmp = new LinkedList<>(); // 새로운 세대 임시 저장
            for (int generation = 1; generation <= g; generation++) {
                int size = dq.size();

                // 끝지점부터 시작지점까지 탐색, 회전해서 생긴 새로운 세대 생성
                while (size --> 0) {
                    int curDir = dq.pollLast();

                    // 머리이면 멈춤
                    if (curDir == -1) {
                        dq.addFirst(curDir);
                        break;
                    }

                    int nextDir = (curDir + 1) % 4;

                    int nextY = curY + dy[nextDir];
                    int nextX = curX + dx[nextDir];

                    curY = nextY; curX = nextX;
                    map[nextY][nextX] = true;

                    tmp.add(nextDir);
                    dq.addFirst(curDir);
                }

                // 임시 저장한 새로운 세대들 다 추가
                while (!tmp.isEmpty()) {
                    dq.addLast(tmp.poll());
                }
            }
        }


        int answer = 0;
        for (int y = 0; y < 100; y++) {
            for (int x = 0; x < 100; x++) {

                if (map[y][x] && map[y + 1][x]
                        && map[y][x + 1] && map[y + 1][x + 1]) {

                    answer++;
                }

            }
        }

        System.out.println(answer);
    }


}
