class Solution {
    // 나올 수 있는 조합
    // 2:2 3:3 4:4
    // 2:3 (2:4) => (1:2)
    // 3:4
    
    public long solution(int[] weights) {
        long answer = 0;
        long[] arr = new long[4001];
        
        for (int i = 0; i < weights.length; i++) {
            arr[weights[i]]++;
        }
        
        for (int i = 100; i <= 1000; i++) {
            if (arr[i] == 0) continue;
            
            // iC2 같은게 여러개 있는 경우 
            answer += (arr[i] * (arr[i] - 1) / 2);
            
            // 2 : 4
            answer += arr[i] * arr[i * 2];
            
            // 2 : 3
            if ((i * 3) % 2 == 0) {
                answer += arr[i] * arr[i * 3 / 2];
            }
            
            // 3 : 4
            if ((i * 4) % 3 == 0) {
                answer += arr[i] * arr[i * 4 / 3];
            }
            
        }
        
        
        return answer;
    }
}