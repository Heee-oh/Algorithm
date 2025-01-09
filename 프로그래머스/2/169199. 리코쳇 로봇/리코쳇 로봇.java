import java.util.Queue;
import java.util.LinkedList;

class Solution {
    boolean[][][] visited;
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

        @Override
        public String toString() {
            return "Token{" +
                    "y=" + y +
                    ", x=" + x +
                    ", step=" + step +
                    '}';
        }
    }

    public int solution(String[] board) {
        visited = new boolean[4][board.length][board[0].length()];
        Token robot = getRobotInfo(board);

        // 로봇 시작 위치는 방문 처리
        for (int i = 0; i < 4; i++) {
            visited[i][robot.y][robot.x] = true;
        }

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
                    
                    // 벽을 만나거나, 이미 방문한 곳이면 멈춤
                    if (tmp_y == -1 || tmp_y == board.length
                            || tmp_x == -1|| tmp_x == board[0].length()) {
                        tmp_y -= dy[i];
                        tmp_x -= dx[i];
                        
                        if (board[tmp_y].charAt(tmp_x) == 'G') {
                            answer = currentRB.step + 1;
                            return;
                        }
                        
                        if (!visited[i][tmp_y][tmp_x]) {
                            q.add(new Token(tmp_y, tmp_x, currentRB.step + 1));
                            visited[i][tmp_y][tmp_x] = true;
                        }

                        break;
                    }

                    if (visited[i][tmp_y][tmp_x]) break;

                    // D인 장애물을 만났을 경우
                    if (board[tmp_y].charAt(tmp_x) == 'D') {
                        tmp_y -= dy[i];
                        tmp_x -= dx[i];

                        // 목표 지점에 도달하면 그것이 이동 최소값
                        // 큐를 이용한 BFS 활용 탐색이라 먼저 발견된 것이 가장 작은 값
                        // 장애물에 부딪혀서 현재 위치가 목표지점인 경우 값 저장후 리턴
                        if (board[tmp_y].charAt(tmp_x) == 'G') {
                            answer = currentRB.step + 1;
                            return;
                        }

                        if (!visited[i][tmp_y][tmp_x]) {
                            // 장애물 전 위치를 큐에 저장하고 방문 체크
                            q.add(new Token(tmp_y, tmp_x, currentRB.step + 1));
                            visited[i][tmp_y][tmp_x] = true;
                        }
                        break;
                    }
                }
            }
        }
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