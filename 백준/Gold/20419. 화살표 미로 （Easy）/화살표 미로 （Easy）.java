import java.io.*;
import java.sql.SQLOutput;
import java.util.*;

public class Main {

    static int R, C, K;
    static char[][] map;
    static boolean[][][] visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static char[] dir = {'U', 'R', 'D', 'L'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[R][C][4];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < 4; i++) {
            visited[R-1][C-1][i] = true;
        }

        System.out.println(bfs() ? "Yes" : "No");
    }

    private static boolean bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{R - 1, C - 1, 0}); // R, C, bitmask

        while (!q.isEmpty()) {
            int[] current = q.poll();

            // 입구로 도달했다면 주어진 스크롤로 충분하다.
            if (current[0] == 0 
                    && current[1] == 0) return true;
            
            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int nextR = current[0] + dr[i];
                int nextC = current[1] + dc[i];

                if (nextR < 0 || nextR >= R 
                        || nextC < 0 || nextC >= C
                        || visited[nextR][nextC][current[2]]) continue;

                // 해당 방향의 방의 방향이 현재 방을 가리키지 않는다면
                char ch = dir[(i + 2) % 4];

                if (map[nextR][nextC] != ch) {
                    if (K == 0) continue; // 주문서가 없으면 방향을 못바꿈

                    int curDir = getDirection(map[nextR][nextC]);
                    char counterclockwise = dir[(curDir - 1 + 4) % 4]; // 반시계방향
                    char clockwise = dir[(curDir + 1) % 4]; // 시계방향

                    for (int j = 0; j < 2; j++) {
                        int bit = current[2] | (1 << j);

                        if (current[2] != bit) {
                            if (j == 0 && clockwise == ch
                                    || j == 1 && counterclockwise == ch) {
                                q.add(new int[]{nextR, nextC, bit});
                                visited[nextR][nextC][bit] = true;
                            }
                        }
                    }

                    // 방향이 같다면
                } else {
                    q.add(new int[]{nextR, nextC, current[2]});
                }

            }

        }

        return false;
    }
    
    private static int getDirection(char c) {
        switch (c) {
            case 'U' : return 0;
            case 'R' : return 1;
            case 'D' : return 2;
            case 'L' : return 3;
            default: return -1;
        }
    }

}
