import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        Arrays.sort(targets, (a, b) -> a[0] - b[0]); // x좌표 기준으로 정렬
        int cnt = 0;
        int last = -1;
        for (int i = 0; i < targets.length; i++) {
            int[] missile = targets[i];
            if (missile[0] > last) { // 새로운 요격 미사일 필요
                cnt++;
                last = missile[1] - 1; // 해당 미사일로 커버되는 범위
            } else if (missile[1] - 1 < last) { // 더 작은 범위로 커버 가능한 미사일 필요
                last = missile[1] - 1; // 해당 미사일로 커버되는 범위
            }
        }
        return cnt;
    }
}