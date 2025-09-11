class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        int dRemain = 0; // 배달 남은 양
        int pRemain = 0; // 수거 남은 양

        for (int i = n - 1; i >= 0; i--) {
            dRemain += deliveries[i];
            pRemain += pickups[i];

            // 갈 필요 없으면 넘김
            if (dRemain == 0 && pRemain == 0) continue;

            // 이번에 가야 할 거리
            int trips = 0;

            // 배달과 수거 중 더 많이 필요한 만큼 반복 (cap 단위로 처리)
            while (dRemain > 0 || pRemain > 0) {
                dRemain -= cap;
                pRemain -= cap;
                trips++;
            }

            // 거리 계산: (i+1) * 2 (왕복)
            answer += (long)(i + 1) * 2 * trips;
        }

        return answer;
    }
}