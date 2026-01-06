import java.util.*;

class Solution {
    // 직사각, 텐트 네면이 동서남북
    // 영역 1 이상
    // 영역 내 다른 쐐기 X, 경계는 가능 (즉 쐐기 공유는 가능함)
    public int solution(int n, int[][] data) { // n <= 5_000
        int answer = 0;
        // 쐐기 2개를 뽑아서 직사각을 만들기 위한 조건 
        // 1. 뽑은 쐐기 2쌍이 서로 대각인지 확인
        // 2. 쐐기가 경계만 걸치면서 같은 영역은 되는지 모르겠음
        
        // x 기준 정렬, 같다면 y기준 정렬 오름차순
        Arrays.sort(data, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

for (int i = 0; i < n; i++) {
    for (int j = i + 1; j < n; j++) {
        // x좌표나 y좌표가 같으면 직사각형을 못 만듦 (면적 0)
        if (data[i][0] == data[j][0] || data[i][1] == data[j][1]) continue;

        boolean isPossible = true;
        int minY = Math.min(data[i][1], data[j][1]);
        int maxY = Math.max(data[i][1], data[j][1]);

        // i와 j 사이에 있는 점들만 검사 (정렬 덕분에 x는 이미 그 사이임)
        for (int k = i + 1; k < j; k++) {
            if (data[k][1] > minY && data[k][1] < maxY) {
                // x는 정렬되어 있어서 i < k < j 이면 data[i][0] <= data[k][0] <= data[j][0] 임
                // 만약 x좌표가 i나 j와 같다면 경계선에 있는 것이므로 무시해도 됨 (조건: 내부만 아니면 됨)
                if (data[k][0] > data[i][0] && data[k][0] < data[j][0]) {
                    isPossible = false;
                    break;
                }
            }
        }
        if (isPossible) answer++;
    }
}
        
        return answer;
    }
}