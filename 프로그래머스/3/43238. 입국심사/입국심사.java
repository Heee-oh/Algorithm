class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        long s = 1, e = (long)1e14;
        
        while (s < e) {
            
            long mid = (s + e) >>> 1;
            long cnt = 0;
            
            for (int i = 0; i < times.length; i++) {
                cnt += mid / times[i];
            }
            
            if (n > cnt) {
                s = mid + 1;
            } else {
                e = mid;
            }
        }
        
        return s;
    }
}