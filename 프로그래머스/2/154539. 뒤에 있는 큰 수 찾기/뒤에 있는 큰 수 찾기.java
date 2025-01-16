import java.util.Stack;

class Solution {
    
    // 스택을 이용
    // idx를 함께 저장
    // 스택 top 원소 < 뒷수 -> 뒷 큰수 넣고, pop()
    
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Stack<int[]> stack = new Stack<>();
        
        for (int i = 0; i < numbers.length; i++) {
            if (stack.isEmpty()) {
                stack.push(new int[] {numbers[i], i});
                
            } else {
                while (!stack.isEmpty()) {
                    if (stack.peek()[0] < numbers[i]) {
                        int idx = stack.pop()[1];
                        answer[idx] = numbers[i];
                    } else {
                        break;
                    }
                }
                // 뒷 큰수를 다 처리했다면 현재 number를 스택에 저장
                stack.push(new int[] {numbers[i], i});
            }
        }
        
        // 뒷 큰수가 존재하지 않은 원소는 -1 처리 
        while (!stack.isEmpty()) {
            int idx = stack.pop()[1];
            answer[idx] = -1;
        }        
        
        
        return answer;
    }
}