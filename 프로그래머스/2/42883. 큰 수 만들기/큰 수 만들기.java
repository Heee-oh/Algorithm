import java.util.PriorityQueue;
import java.util.Stack;

class Solution {
    // k - 현재 숫자 위치  >= 0 
    // number.length - k 까지만 탐색해서 가장 큰 값부터 차례대로 
    // K가 0이 아니라면
        // 순차를 돌면서 X, X+1 대해 바꿨을때 큰수가 되면 그 수는 버린다. 
    
    public String solution(String number, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]); // 큰 값 먼저
        char maxNum = '0';
        String max = "0";
        int idx = 0;
        
        // k까지 위치에서 가장 큰 첫 수를 얻음
        for (int i = 0; i <= k; i++) {
            if (maxNum <= number.charAt(i)) {
                maxNum = number.charAt(i);
                pq.add(new int[] {maxNum - '0', i});
            }
        }

        while (!pq.isEmpty()) {
            Stack<Character> stack = new Stack<>();
            StringBuilder sb = new StringBuilder();
            int[] curMaxNum = pq.poll();
            
            if (curMaxNum[0] != maxNum - '0') break;
            stack.push(maxNum);
            
            // k - i > 0 이면 그만큼 숫자를 버리는 과정 진행
            int tmpK = k - curMaxNum[1];
                
            // i, i+1 을 비교했을 때 i+1이 더 크다면 i번째 수는 제거
            for (int i = curMaxNum[1] + 1; i < number.length(); i++) {
                if (tmpK > 0) {
                    if (i + 1 < number.length()) {
                        if (stack.peek() >= number.charAt(i)) {
                            stack.push(number.charAt(i));
                            continue;
                        } 
                            
                        
                    } 
                        
                    while(tmpK > 0 && stack.peek() < number.charAt(i)) {
                       stack.pop();
                       tmpK--;
                    }
                    stack.push(number.charAt(i));
                            
                // 더 크지 않다면 숫자 하나 추가 
                } else {
                    stack.push(number.charAt(i));
                }
            }
        
            while (tmpK-- > 0) {
                stack.pop();
            }
            
            while (!stack.isEmpty()) {
                sb.append(stack.pop());
            }
            
            sb.reverse();
            
            if (max.compareTo(sb.toString()) < 0) {
                max = sb.toString();
            }

            break;
        }
        
        
        
        return max;
    }
}