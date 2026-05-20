import java.util.Arrays;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        int answer = 0;
        
        int l = 1, r = distance;
        int target = rocks.length + 1 - n; // 끝지점 distance도 바위로 취급해서 바우 간의 거리 배열 조건을 맞춤
        
        int[] nRocks = new int[rocks.length + 2];
        
        for (int i = 1; i <= rocks.length; i++) {
            nRocks[i] = rocks[i - 1];
        }
        nRocks[nRocks.length - 1] = distance;
        
        
        // n개를 제외한 나머지 바위를 탐색
        while(l <= r) {
            int mid = (l + r) >>> 1; // 최소 거리가 mid 이상으로 기준을 잡음
            
            
            // rocks.len - n개의 바위 선정
            int s = 0; 
            int cnt = 0;
            for (int i = 1; i <= rocks.length + 1; i++) {
                if (nRocks[i] - s < mid) continue;
                s = nRocks[i];
                cnt++;
            }
            
            // 필요한 바위만큼 선정했다면
            if (target <= cnt) {
                answer = mid;
                
                l = mid + 1;
                // 못했다면
            } else {
                r = mid - 1;
            }
            
        }
        
        return answer;
    }
}