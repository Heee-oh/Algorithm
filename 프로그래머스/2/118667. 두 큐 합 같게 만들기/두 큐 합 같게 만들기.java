import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = -1;
        
        long q1Sum = Arrays.stream(queue1).mapToLong(x -> x).sum();
        long q2Sum = Arrays.stream(queue2).mapToLong(x -> x).sum();
        long sum = q1Sum + q2Sum;
        
        // 홀수이면 불가능
        if (sum % 2 != 0) {
            return -1;
        }
        
        // 이미 완성된 경우
        if (q1Sum == q2Sum) {
            return 0;
        }
        
        
        long target = sum / 2;
        Queue<Long> q1 = new LinkedList<>();
        Queue<Long> q2 = new LinkedList<>();
        
        for (int i = 0; i < queue1.length; i++) {
            q1.offer((long)queue1[i]);
            q2.offer((long)queue2[i]);
        }
            
        int limit = q1.size() * 4;
            
        for (int i = 1; i <= limit; i++) {
            
            if (q2Sum < target) {
                long tmp = q1.poll();
                q2.offer(tmp);
                q1Sum -= tmp;
                q2Sum += tmp;
                    
            } else if (q1Sum < target) {
                long tmp = q2.poll();
                q1.offer(tmp);
                q2Sum -= tmp;
                q1Sum += tmp;
            }
            
            if (q2Sum == q1Sum) {
                return i;
            }
        }    

        return answer;
        
    }

}