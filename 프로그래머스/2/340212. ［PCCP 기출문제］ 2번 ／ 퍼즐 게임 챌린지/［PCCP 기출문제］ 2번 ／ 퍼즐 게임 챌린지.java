import java.util.*;

class Solution {
    // diff <= level : time_cur만큼 시간 사용
    // diff > level : diff-level 번 틀림, 틀릴때 마다 time_cur만큼 시간 사용 + time_prev 만큼의 시간 사용 (이전 퍼즐 다시 품(이때 절때 틀리지 않음))
    // 난이도, 소요시간, 숙련도 양의 정수

    //limit >= (diff[i] - level) * (time_cur[i] + time_prev[i]) + time_cur[i]


    //

    public int solution(int[] diffs, int[] times, long limit) {
        int[] timeSum = new int[times.length];
        // 리밋에서 우선 time_cur만큼 뺀다
        limit -= Arrays.stream(times).sum();
        int level = 1;


        // time_cur + time_prev
        for (int i = 1; i < diffs.length; i++) {
            timeSum[i] = times[i] + times[i-1];
        }

        int front = 1, back = 100000;


        while(front <= back) {
            long sum = 0;

            int mid = (front + back) / 2;
            
            // O(n)
            for (int j = 1; j < diffs.length; j++) {
                if (diffs[j] != 1 && diffs[j] - mid > 0) {
                    sum += (long)(diffs[j] - mid) * timeSum[j];
                }
            }
            
            if (limit >= sum) {
                back = mid - 1;
                level = mid;
            } else {
                front = mid + 1;
            }
        }


        return level;
    }

}