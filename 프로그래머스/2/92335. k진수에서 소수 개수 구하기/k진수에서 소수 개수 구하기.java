import java.util.*;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int tmp = n;
        
        // k진수로 변환
        while(tmp!= 0) {
            int rs = tmp % k;
            stack.push(rs);
            tmp /= k;
        }
        
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        
        String str = sb.toString();
        
        // 4번 조건 확인, 각 자릿수에 0 미포함
        if (str.length() == Long.bitCount(Long.parseLong(str))
            && isPrime(Long.parseLong(str))) {
            answer++;
        }
        
        String[] split = str.split("0+");
       
        for (int i = 0; i < split.length; i++) {
            long num = Long.parseLong(split[i]);
            if (isPrime(num)) {
                answer++;
            }
        }
        
        
        
        return answer;
    }
    
    private boolean isPrime(long n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        
        return true;
    }
}