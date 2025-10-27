import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int[] me = scores[0];
        int mySum = me[0] + me[1];
        
        // 정렬: 근무태도 내림차순, 같으면 동료평가 오름차순
        Arrays.sort(scores, (a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return b[0] - a[0];
        });
        
        int maxPeer = 0;
        List<Integer> valid = new ArrayList<>();
        
        // 한 번의 순회로 인센티브 대상 판정
        for (int[] s : scores) {
            if (s[1] < maxPeer) {
                // 두 점수 모두 낮은 경우 → 탈락
                if (s == me) return -1;
            } else {
                maxPeer = s[1];
                valid.add(s[0] + s[1]);
            }
        }
        
        // 합계 점수 기준으로 나보다 높은 사람 수 계산
        valid.sort(Collections.reverseOrder());
        
        int rank = 1;
        for (int sum : valid) {
            if (sum > mySum) rank++;
        }
        
        return rank;
    }
}
