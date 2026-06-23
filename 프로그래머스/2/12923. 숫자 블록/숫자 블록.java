import java.util.Arrays;

class Solution {
    public int[] solution(long begin, long end) {
        int[] answer = new int[(int)(end - begin) + 1];
    
        // 제일 큰 수중에서 나눠 떨어지는 수?
        
//         for (int i = (int)begin / 2; i <= (int)end / 2; i++) {
//             if (i == 0) {
//                 continue;
//             }
            
//             for (int j = 2; j <= (int)end / i; j++) {
//                 if (i * j - (int)begin > answer.length) break;
//                 answer[i * j - (int)begin] = i;
//             }
//         }
        
        for (int i = 0; i < answer.length; i++) {
            if (answer[i] == 0) {
                int rs = prime(i + (int)begin);
                
                // 소수면 1 
                if (rs == -1) {
                    answer[i] = 1;
                    
                    // 10억 이하 약수를 어캐 빠르게 구할까
                    // 값을 2부터 증가? 절반까지? 그럼 시간초과.. 
                    
                } else {
                    answer[i] = rs;
                }
            }
        }
        
        // 1이면 0
        if (begin == 1) answer[0] = 0;
        
        return answer;
    }
    
    private int prime(double n) {
        int max = -1;
        for (int i = 2; i  <= (int)Math.sqrt(n); i++) {
            if (n % i == 0) {
                
                if (n / i <= (int)1e7) {
                    return (int)n / i;
                }
                
                if (i <= (int)1e7) {
                    max = i;
                }
            }
            
        }
        
        return max;
    }
}