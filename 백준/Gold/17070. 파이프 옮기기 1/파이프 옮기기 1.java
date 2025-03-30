import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {

    static int[] dy = {0, 1, 1};
    static int[] dx = {1, 0, 1};
    static boolean[][][] visited; // map x 3가지 방법
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int[][] map = new int[n][n];
        visited = new boolean[n][n][3]; // 가로, 세로, 대각

        // 맵 저장
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        if (map[n - 1][n - 1] == 1) {
            System.out.println(0);
            return;
        }

        System.out.println(bfs(map, n - 1));
    }

    private static int bfs(int[][] map, int n) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 1, 1}); // r, c , 가로 : 1 세로  : 0 대각 : 3

        int cnt = 0;

        while (!q.isEmpty()) {
            int[] curPos = q.poll();

            // (n,n) 도착시 카운팅
            if (curPos[0] == n && curPos[1] == n) {
                cnt++;
                continue;
            }

            // 대각선을 가기 위한 가로 세로 카운트, 3이면 가로세로대각 다 가능하다는 의미로 대각선 진행 가능
            int diagonalCheck = 0;

            for (int i = 0; i < 3; i++) {
                int nextR = curPos[0] + dy[i];
                int nextC = curPos[1] + dx[i];

                if (nextR < 0 || nextR >= map.length
                        || nextC < 0 || nextC >= map[0].length
                        ||map[nextR][nextC] == 1) continue;

                // 해당 방향이 가능하므로 +1
                diagonalCheck++;

                // 각 방향에 맞게 이동 방향 필터
                // 대각선 차례일 때 앞서 탐색한 가로,세로,대각이 불가능했다면 diagonalCheck값이 3미만이 되었을 것이다.
                // 불가능하다면 다음 위치의 요소를 꺼낸다.
                if (curPos[2] == i || i == 2 && diagonalCheck < 3) continue;

                q.add(new int[]{nextR, nextC, getDirection(i)});
            }
        }

        return cnt;
    }

    private static int getDirection(int i) {
        switch (i) {
            case 0 : return 1; // 가로
            case 1 : return 0; // 세로
            case 2 : return 3; // 대각
            default: return -1;
        }

    }
}
