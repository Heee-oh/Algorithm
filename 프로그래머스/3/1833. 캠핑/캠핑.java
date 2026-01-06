import java.util.*;

class Solution {
    public int solution(int n, int[][] data) {
        int answer = 0;
        
        // 1. x, y 좌표 정렬 (좌표 압축 준비)
        Arrays.sort(data, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        // 2. 좌표 압축 (중복 제거 및 순위 매기기)
        List<Integer> xList = new ArrayList<>();
        List<Integer> yList = new ArrayList<>();
        for (int[] p : data) {
            xList.add(p[0]);
            yList.add(p[1]);
        }
        
        List<Integer> uniqueX = new ArrayList<>(new TreeSet<>(xList));
        List<Integer> uniqueY = new ArrayList<>(new TreeSet<>(yList));

        int[][] prefSum = new int[n + 1][n + 1];
        for (int i = 0; i < n; i++) {
            // 좌표를 0 ~ N-1 사이의 인덱스로 변환
            int nx = Collections.binarySearch(uniqueX, data[i][0]);
            int ny = Collections.binarySearch(uniqueY, data[i][1]);
            data[i][0] = nx;
            data[i][1] = ny;
            // 누적 합 배열에 표시 (1-based index 사용)
            prefSum[nx + 1][ny + 1] = 1;
        }

        // 3. 2차원 누적 합 계산
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                prefSum[i][j] += prefSum[i - 1][j] + prefSum[i][j - 1] - prefSum[i - 1][j - 1];
            }
        }

        // 4. 모든 두 쐐기 쌍(i, j)에 대해 검사
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // 직사각형 면적이 0인 경우(좌표가 하나라도 같은 경우) 제외
                if (data[i][0] == data[j][0] || data[i][1] == data[j][1]) continue;

                int x1 = Math.min(data[i][0], data[j][0]);
                int x2 = Math.max(data[i][0], data[j][0]);
                int y1 = Math.min(data[i][1], data[j][1]);
                int y2 = Math.max(data[i][1], data[j][1]);

                // 경계를 제외한 내부 영역: (x1+1, y1+1) ~ (x2-1, y2-1)
                int count;
                if (x1 + 1 > x2 - 1 || y1 + 1 > y2 - 1) {
                    count = 0; // 내부에 정수 좌표가 존재할 수 없는 좁은 영역
                } else {
                    // 2차원 구간 합 공식: S(x2-1, y2-1) - S(x1, y2-1) - S(x2-1, y1) + S(x1, y1)
                    // 1-based index 기준이므로 인덱스 조정에 유의
                    count = prefSum[x2][y2] - prefSum[x1 + 1][y2] - prefSum[x2][y1 + 1] + prefSum[x1 + 1][y1 + 1];
                }

                if (count == 0) answer++;
            }
        }

        return answer;
    }
}