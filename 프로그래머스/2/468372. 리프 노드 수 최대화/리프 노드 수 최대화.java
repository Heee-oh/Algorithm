class Solution {
    public long solution(int dist_limit, int split_limit) {
        long D = dist_limit;
        long S = split_limit;
        long answer = 1; // 아무 분배도 안 하면 리프 1개

        // 2^i 를 순회
        long pow2 = 1;
        for (int i = 0; pow2 <= S; i++) {
            // 해당 i에서 가능한 최대 j 찾기
            long limit = S / pow2;
            int maxJ = 0;
            long pow3 = 1;
            while (pow3 * 3 <= limit) {
                pow3 *= 3;
                maxJ++;
            }
            
            // i개의 2층 + maxJ개의 3층을 허용했을 때 최대로 만들 수 있는 리프 수 계산
            answer = Math.max(answer, simulate(i, maxJ, D));

            // 다음 2^i
            if (pow2 > S / 2) break;
            pow2 *= 2;
        }

        return answer;
    }

    private long simulate(int twoCount, int threeCount, long distLimit) {
        long remain = distLimit;
        long frontier = 1; // 루트의 유일한 자식 1개에서 시작

        // 2분배층들
        for (int t = 0; t < twoCount; t++) {
            if (remain >= frontier) {
                remain -= frontier;
                frontier *= 2;
            } else {
                // 부분 분배
                return frontier + remain;
            }
        }

        // 3분배층들
        for (int t = 0; t < threeCount; t++) {
            if (remain >= frontier) {
                remain -= frontier;
                frontier *= 3;
            } else {
                // 부분 분배
                return frontier + 2L * remain;
            }
        }

        // 더 이상 내려갈 수 있는 층이 없으면 현재 frontier가 곧 리프 수
        return frontier;
    }
}