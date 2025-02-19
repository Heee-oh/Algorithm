import java.util.Arrays;

class Solution {
    // 야근 피로도 = 남은일 ^ 2
    // 그리드 
    // 1시간동안 작업량 1만큼 처리
    
    public long solution(int n, int[] works) {
        long[] schedule = new long[50001];
        long answer = 0;
        int max = 0;
        
        for (int i = 0; i < works.length; i++) {
            max = Math.max(max, works[i]);
            schedule[works[i]]++;
        }
        
        // max 에서 하나씩 내림
        while (max > 0 && n > 0) {
            
            // max의 개수를 n 과 비교하여 더 작다면 n에서 빼고 max-1로 옮김
            if (schedule[max] <= n) {
                n -= schedule[max];
                schedule[max-1] += schedule[max];
                schedule[max--] = 0;
                // max의 개수가 n보다 크다면 
            } else {
                schedule[max] -= n; // 기존 max에서 n을 빼고
                schedule[max-1] += n; // -1된 max에 n을 추가
                break; // max의 개수가 n보다 크므로 n은 더이상 없음
            }
        }
        
        
        for (int i = max; i >= 1; i--) {
            if (schedule[max] > 0) {
                answer += i * i * schedule[i];
            }
        }
        
        return answer;
        
        
    }
}