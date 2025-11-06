import java.util.*;

class Solution {
    public int solution(int n, int[] cores) {
        // 0초에 cores.length개의 작업이 즉시 시작
        if (n <= cores.length) return n;

        // 이진 탐색 구간 설정(안전 상한)
        long lo = 0;
        int maxCore = Arrays.stream(cores).max().getAsInt();
        long hi = (long) maxCore * (n / cores.length + 1L);

        long time = hi; // 처리량이 n 이상이 되는 최소 시간

        // 최소 time 찾기
        while (lo <= hi) {
            long mid = (lo + hi) / 2;

            long done = 0;
            for (int c : cores) {
                // 각 코어는 0초에도 하나를 시작하므로 +1
                done += mid / c + 1;
            }

            if (done >= n) {
                time = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        // time-1까지 처리된 작업 수
        long doneBefore = 0;
        long prev = time - 1;
        for (int c : cores) {
            doneBefore += prev / c + 1;
        }

        // time 시점에 새 작업을 맡는 코어를 순서대로 확인
        for (int i = 0; i < cores.length; i++) {
            if (time % cores[i] == 0) {
                doneBefore++;
                if (doneBefore == n) return i + 1;
            }
        }

        // 논리상 도달하지 않음
        return -1;
    }
}
