import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    static int[][] map; // 전체 맵
    static int[][] possibleArea; // 0인 영역의 번호
    static boolean[][] zeroVisited; // 0 방문 체크
    static int[] possibleCnt; // 0인 영역 별 개수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        possibleArea = new int[n][m];
        zeroVisited = new boolean[n][m];
        possibleCnt = new int[n * m + 1];
        
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }


        int number = 1; // 영역 번호
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0 && !zeroVisited[i][j]) {
                    int cnt = bfs(i, j, number);
                    possibleCnt[number] = cnt; // 영역번호에 해당하는 영역 내 0 의 개수를 기록
                    number++;
                }
            }
        }

        int[][] answer = new int[n][m];
        Set<Integer> vistiedSet = new HashSet<>();

        int one = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 1인 벽을 중심으로 상하좌우 탐색
                if (map[i][j] == 0) continue;
                one++;

                answer[i][j] = 1; // 1번 조건, 벽을 부수고 이동할 수 있는 곳으로 변경, 즉 1있는곳을 0으로 하여 이동가능 칸 +1
                vistiedSet.clear();
                // 상하좌우 탐색
                for (int k = 0; k < 4; k++) {
                    int nextR = i + dy[k];
                    int nextC = j + dx[k];

                    // 범위를 넘어서거나 벽이거나 이미 방문했다면 다음 방향 탐색
                    if (n <= nextR || 0 > nextR
                            || m <= nextC || 0 > nextC || map[nextR][nextC] == 1) continue;

                    // 탐색한 위치의 영역 번호를 얻고 영역 번호에 해당하는 영역 내 0의 개수를 얻음
                    int num = possibleArea[nextR][nextC];
                    int cnt = possibleCnt[num];

                    if (!vistiedSet.contains(num)) {
                        vistiedSet.add(num);
                        // 해당 벽을 부쉈을 때 이동 가능한 칸의 개수 갱신
                        answer[i][j] = (answer[i][j] + cnt) % 10;
                    }
                }

            }
        }

        StringBuilder sb = new StringBuilder();
        for (int[] row : answer) {
            for (int col : row) sb.append(col);

            sb.append("\n");
        }

        System.out.print(sb.toString());


    }

    // 0인 영역을 BFS 탐색
    private static int bfs(int r, int c, int num) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c});
        zeroVisited[r][c] = true;
        possibleArea[r][c] = num;
        int cnt = 0;

        while (!q.isEmpty()) {
            int[] current = q.poll();
            cnt++;

            for (int i = 0; i < 4; i++) {
                int nextR = current[0] + dy[i];
                int nextC = current[1] + dx[i];
                if (validate(nextR, nextC)) continue;

                zeroVisited[nextR][nextC] = true;
                possibleArea[nextR][nextC] = num;
                q.add(new int[]{nextR, nextC});
            }


        }

        return cnt;
    }

    private static boolean validate(int nextR, int nextC) {
        return map.length <= nextR || 0 > nextR
                || map[0].length <= nextC || 0 > nextC
                || zeroVisited[nextR][nextC] || map[nextR][nextC] == 1;
    }



}
