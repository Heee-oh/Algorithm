import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Map {
        int[][] map;
        int cnt;

        public Map(int[][] map, int cnt) {
            this.map = map;
            this.cnt = cnt;
        }
    }
    static int n;
    static int[][] direction;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        int[][] map = new int[n][n];

        direction = new int[][]{{1, 0}, {n-2, 0}, {n - 2, 2}, {0, 1}};

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs(map);

        System.out.println(max);

    }


    static int max = 0;

    private static void bfs(int[][] map) {
        Queue<Map> q = new LinkedList<>();
        q.add(new Map(map, 0));

        while (!q.isEmpty()) {
            Map current = q.poll();

            // 5번 이동했으면 결산
            if (current.cnt == 5) {
                for (int i = 0; i < n; i++) {
                    max = Math.max(max, Arrays.stream(current.map[i]).max().getAsInt());
                }
                continue;
            }

            int[][] newMap = getMap(current.map);
            // 위 방향
            moveUp(newMap);
            q.add(new Map(newMap, current.cnt + 1));

            newMap = getMap(current.map);
            // 아래
            moveDown(newMap);
            q.add(new Map(newMap, current.cnt + 1));


            newMap = getMap(current.map);
            // 왼
            moveLeft(newMap);
            q.add(new Map(newMap, current.cnt + 1));

            newMap = getMap(current.map);
            //오른
            moveRight(newMap);
            q.add(new Map(newMap, current.cnt + 1));
        }
    }

    private static int[][] getMap(int[][] current) {
        int[][] newMap = new int[n][n];
        for (int j = 0; j < n; j++) {
            newMap[j] = current[j].clone();
        }
        return newMap;
    }

    private static void moveUp(int[][] newMap) {
        boolean[][] isUnion = new boolean[n][n];
        for (int c = 0; c < n; c++) {
            int notUinonIdx = 0;
            for (int r = 1; r < n; r++) {

                // 현재 위치의 블록이 없다면 넘어감
                if (newMap[r][c] == 0) continue;

                // 합쳐지지않은 곳부터 ~ r까지 이동 가능한 곳 탐색
                for (int j = notUinonIdx; j < r; j++) {
                    if (newMap[j][c] == newMap[r][c] && !isUnion[j][c]) {
                        newMap[j][c] *= 2;
                        newMap[r][c] = 0;
                        isUnion[j][c] = true;
                        notUinonIdx = j + 1;
                        break;

                    } else if (newMap[j][c] == 0){
                        newMap[j][c] = newMap[r][c];
                        newMap[r][c] = 0;
                        break;

                    } else {
                        notUinonIdx = j + 1;
                    }
                }
            }
        }
    }

    private static void moveDown(int[][] newMap) {
        boolean[][] isUnion = new boolean[n][n];
        for (int c = 0; c < n; c++) {

            int notUinonIdx = n-1;
            for (int r = n-2; r >= 0; r--) {

                // 현재 위치의 블록이 없다면 넘어감
                if (newMap[r][c] == 0) continue;

                // 합쳐지지않은 곳부터 ~ r까지 이동 가능한 곳 탐색
                for (int j = notUinonIdx; j > r; j--) {
                    if (newMap[j][c] == newMap[r][c] && !isUnion[j][c]) {
                        newMap[j][c] *= 2;
                        newMap[r][c] = 0;
                        isUnion[j][c] = true;
                        notUinonIdx = j - 1;
                        break;

                    } else if (newMap[j][c] == 0){
                        newMap[j][c] = newMap[r][c];
                        newMap[r][c] = 0;
                        break;
                    } else {
                        notUinonIdx = j - 1;
                    }
                }
            }
        }
    }

    private static void moveLeft(int[][] newMap) {
        boolean[][] isUnion = new boolean[n][n];
        for (int r = 0; r < n; r++) {
            int notUinonIdx = 0;

            for (int c = 1; c < n; c++) {

                // 현재 위치의 블록이 없다면 넘어감
                if (newMap[r][c] == 0) continue;

                // 합쳐지지않은 곳부터 ~ r까지 이동 가능한 곳 탐색
                for (int j = notUinonIdx; j < c; j++) {
                    if (newMap[r][j] == newMap[r][c] && !isUnion[r][j]) {
                        newMap[r][j] *= 2;
                        newMap[r][c] = 0;
                        isUnion[r][j] = true;
                        notUinonIdx = j + 1;
                        break;

                    } else if (newMap[r][j] == 0){
                        newMap[r][j] = newMap[r][c];
                        newMap[r][c] = 0;
                        break;

                    } else {
                        notUinonIdx = j + 1;
                    }
                }
            }
        }
    }
    private static void moveRight(int[][] newMap) {
        boolean[][] isUnion = new boolean[n][n];
        for (int r = 0; r < n; r++) {
            int notUinonIdx = n-1;

            for (int c = n-2; c >= 0; c--) {

                // 현재 위치의 블록이 없다면 넘어감
                if (newMap[r][c] == 0) continue;

                // 합쳐지지않은 곳부터 ~ r까지 이동 가능한 곳 탐색
                for (int j = notUinonIdx; j > c; j--) {
                    if (newMap[r][j] == newMap[r][c] && !isUnion[r][j]) {
                        newMap[r][j] *= 2;
                        newMap[r][c] = 0;
                        isUnion[r][j] = true;
                        notUinonIdx = j - 1;
                        break;

                    } else if (newMap[r][j] == 0){
                        newMap[r][j] = newMap[r][c];
                        newMap[r][c] = 0;
                        break;

                    } else {
                        notUinonIdx = j - 1;
                    }
                }
            }
        }
    }

}
