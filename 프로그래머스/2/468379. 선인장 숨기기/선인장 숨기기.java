import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Solution {
    static final int INF = 1_000_000_000;

    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        int[][] time = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(time[i], INF);
        }

        for (int i = 0; i < drops.length; i++) {
            int r = drops[i][0];
            int c = drops[i][1];
            time[r][c] = i + 1;
        }

        int cols = n - w + 1;
        int rows = m - h + 1;

        //  각 행에 대해 너비 w 구간 최소값
        int[][] rowMin = new int[m][cols];
        for (int r = 0; r < m; r++) {
            Deque<Integer> dq = new ArrayDeque<>();
        
            
            for (int c = 0; c < n; c++) {
                // 가장 늦은 값보다 현재 값이 더 이르다면 
                while (!dq.isEmpty() && time[r][dq.peekLast()] >= time[r][c]) {
                    dq.pollLast(); // 가장 늦은 값 제거 
                }
                
                // 현재 비 값 추가
                dq.offerLast(c);

                // 선인장 구역 왼쪽 범위 넘어섰다면 제거 
                while (!dq.isEmpty() && dq.peekFirst() <= c - w) {
                    dq.pollFirst();
                }

                // 일단 w만큼 채우고 그 이후부터 슬라이딩 윈도우
                if (c >= w - 1) {
                    rowMin[r][c - w + 1] = time[r][dq.peekFirst()];
                }
            }
        }

        //  각 열에 대해 높이 h 구간 최소값
        int[][] minArr = new int[rows][cols];
        for (int c = 0; c < cols; c++) {
            Deque<Integer> dq = new ArrayDeque<>();

            for (int r = 0; r < m; r++) {
                while (!dq.isEmpty() && rowMin[dq.peekLast()][c] >= rowMin[r][c]) {
                    dq.pollLast();
                }
                dq.offerLast(r);

                while (!dq.isEmpty() && dq.peekFirst() <= r - h) {
                    dq.pollFirst();
                }

                if (r >= h - 1) {
                    minArr[r - h + 1][c] = rowMin[dq.peekFirst()][c];
                }
            }
        }

        int max = -1;
        int bestR = 0;
        int bestC = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int cur = minArr[r][c];

                if (cur > max) {
                    max = cur;
                    bestR = r;
                    bestC = c;
                }
            }
        }

        return new int[]{bestR, bestC};
    }
}