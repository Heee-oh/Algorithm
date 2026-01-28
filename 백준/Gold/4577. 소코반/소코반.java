import java.io.*;
import java.util.*;

public class Main {

    static int R, C;
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int game = 0;
        while (true) {
            game++;
            StringTokenizer st = new StringTokenizer(br.readLine());

            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if (R == 0 &&  C == 0) {
                break;
            }

            char[][] map = new char[R][C];
            int wr = 0, wc = 0;
            int cnt = 0;

            int box = 0;

            for (int i = 0; i < R; i++) {
                String line = br.readLine();
                map[i] = line.toCharArray();

                if (line.indexOf('w') != -1) {
                    wr = i;
                    wc = line.indexOf('w');

                } else if (line.indexOf('W') != -1) {
                    wr = i;
                    wc = line.indexOf('W');
                }

                for (int j = 0; j < C; j++) {
                    cnt += (map[i][j] == '+' ) ? 1 : 0;
                }
            }

            // 시작지점이 W(목표지점)인 경우 고려
            cnt += map[wr][wc] == 'W' ? 1 : 0;

            char[] commands = br.readLine().toCharArray();

            if (canCompleted(map, commands, wr, wc, cnt, box)) {
                sb.append("Game ").append(game).append(": complete\n");
                for (int i = 0; i < R; i++) {
                    sb.append(map[i]).append("\n");
                }

            } else {
                sb.append("Game ").append(game).append(": incomplete\n");
                for (int i = 0; i < R; i++) {
                    sb.append(map[i]).append("\n");
                }
            }
        }

        System.out.print(sb.toString().trim());

    }


    private static boolean canCompleted(char[][] map, char[] commands, int wr, int wc, int target, int box) {
        int curR = wr;
        int curC = wc;
        int cnt = box;

        for (char c : commands) {

            int dir = getDir(c);

            int nr = curR + dr[dir];
            int nc = curC + dc[dir];

            if (map[nr][nc] == '.') {
                map[curR][curC] = (map[curR][curC] == 'W') ? '+' : '.'; // 목표지점일 경우 +로 복구, 빈칸이라면 .으로
                map[nr][nc] = 'w';

                curR = nr;
                curC = nc;
                continue;
            }

            // 목표지점에 오면 w -> W로 되야함
            if (map[nr][nc] == '+') {
                map[curR][curC] = (map[curR][curC] == 'W') ? '+' : '.';
                map[nr][nc] = 'W';
                curR = nr;
                curC = nc;
                continue;
            }

            if (map[nr][nc] == 'b' || map[nr][nc] == 'B') {
                int nnr = nr + dr[dir];
                int nnc = nc + dc[dir];

                // 박스가 있고, 그 뒤에 박스(. + 지점에 있음)나 벽일시 움직이지 않음
                if (map[nnr][nnc] == 'b' || map[nnr][nnc] == '#' || map[nnr][nnc] == 'B') {
                    continue;
                }

                map[curR][curC] = (map[curR][curC] == 'W') ? '+' : '.'; // 현재 w의 위치를 정상화
                map[nr][nc] = (map[nr][nc] == 'B') ? 'W' : 'w'; // 다음 위치로 w를 이동

                curR = nr;
                curC = nc;

                // 다다음이 목표지점이라면
                if (map[nnr][nnc] == '+') {
                    map[nnr][nnc] = 'B';

                    // 빈칸에서 목표지점이므로 + 1
                    if (map[nr][nc] == 'w') {
                        cnt++;
                    }

                    // 다다음이 빈칸이라면
                } else {
                    map[nnr][nnc] = 'b';

                    // +에서 빈칸으로 이동했으므로 - 1
                    if (map[nr][nc] == 'W') {
                        cnt--;
                    }
                }

                if (cnt == target) return true;
            }

        }

        return false;

    }

    private static int getDir(char c) {
        switch (c) {
            case 'D':
                return 0;
            case 'R' :
                return 2;
            case 'U':
                return 1;
            default:
                return 3;
        }
    }


}
