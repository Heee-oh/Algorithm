import java.util.*;
class Solution {
    boolean[][] visited;
    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};
    int answer = 0;

    class Token {
        int y;
        int x;
        int step;

        public Token(int y, int x, int step) {
            this.y = y;
            this.x = x;
            this.step = step;
        }
    }

    public int solution(String[] board) {
        visited = new boolean[board.length][board[0].length()];
        Token robot = getRobotInfo(board);

        // 로봇 시작 위치는 방문 처리

        visited[robot.y][robot.x] = true;
        

        findGoalPointDistance(robot, board);

        return answer == 0 ? -1 : answer;
    }


    private void findGoalPointDistance(Token robot, String[] board) {
        Queue<Token> q = new LinkedList<>();
        q.add(robot);

        while(!q.isEmpty()) {
            Token currentRB = q.poll();

            for (int i = 0; i < 4; i++) {
                int tmp_y = currentRB.y;
                int tmp_x = currentRB.x;

                // 한 방향으로 쭉 이동시키기
                while (true) {
                    tmp_y += dy[i];
                    tmp_x += dx[i];

                    // 벽을 만나거나, D인 장애물을 만나면 멈춤
                    if (tmp_y == -1 || tmp_y == board.length
                            || tmp_x == -1|| tmp_x == board[0].length()
                            || board[tmp_y].charAt(tmp_x) == 'D') {
                        tmp_y -= dy[i];
                        tmp_x -= dx[i];
                        break;
                    }

                }

                if (visited[tmp_y][tmp_x]) continue;
                if (checkCurrentPoint(board, tmp_y, tmp_x, currentRB, i, q)) return;
            }
        }
    }

    private boolean checkCurrentPoint(String[] board, int tmp_y, int tmp_x, Token currentRB, int i, Queue<Token> q) {
        if (board[tmp_y].charAt(tmp_x) == 'G') {
            answer = currentRB.step + 1;
            return true;
        }

        q.add(new Token(tmp_y, tmp_x, currentRB.step + 1));
        visited[tmp_y][tmp_x] = true;
        return false;
    }

    private Token getRobotInfo(String[] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length(); j++) {
                if (board[i].charAt(j) == 'R') {
                    return new Token(i, j, 0);
                }
            }
        }
        return null;
    }
}
