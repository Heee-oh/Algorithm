class Solution {

    // 이동 방향 (상, 하, 좌, 우)
    private static final int[] dx = {0, 0, -1, 1};
    private static final int[] dy = {-1, 1, 0, 0};

    private int[][] board;
    private int n, m;

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        this.board = board;
        n = board.length;
        m = board[0].length;

        return dfs(aloc[0], aloc[1], bloc[0], bloc[1]);
    }

    /**
     * @param ax A의 현재 x좌표
     * @param ay A의 현재 y좌표
     * @param bx B의 현재 x좌표
     * @param by B의 현재 y좌표
     * @return 현재 턴 플레이어가 이길 때 최소 턴, 질 때 최대 턴
     */
    private int dfs(int ax, int ay, int bx, int by) {
        // 현재 턴 플레이어 위치
        int x = ax, y = ay;

        // 발판이 사라졌으면 이동 불가 → 패배
        if (board[x][y] == 0) return 0;

        int result = 0; // 현재 상태에서의 턴 수 (승/패 여부 포함)
        for (int dir = 0; dir < 4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (!inRange(nx, ny) || board[nx][ny] == 0) continue;

            board[x][y] = 0; // 현재 위치 발판 제거
            int next = dfs(bx, by, nx, ny) + 1; // 턴 교체 후 진행
            board[x][y] = 1; // 원상 복구

            if (next % 2 == 1) { 
                // 내가 이길 수 있는 경우
                if (result % 2 == 0) result = next; // 첫 번째 승리 갱신
                else result = Math.min(result, next); // 더 빨리 이기는 쪽 선택
            } else {
                // 내가 질 수밖에 없는 경우
                if (result % 2 == 0) result = Math.max(result, next); // 최대한 버팀
            }
        }

        return result;
    }

    private boolean inRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }
}
