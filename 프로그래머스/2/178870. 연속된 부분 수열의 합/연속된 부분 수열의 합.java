class Solution {
    // 투 포인터 문제 예상
    // 연속된 부분 수열의 합
    // 비 내림차순 정렬 -> 오름차순 정렬 ? 
    
    public int[] solution(int[] sequence, int k) {
        
        int[] answer = {0, 10000001};
        
        // 투 포인터 및 합 변수 선언, 초기화
        int front = 0;
        int back = 0;
        int sum = 0;
        
        while(front < sequence.length 
              || back < sequence.length ) {

            if (sum < k) {
                if (back >= sequence.length) break;
                sum += sequence[back];
                back++;
                
            } else if (sum > k) {
                if (front > back) break;
                sum -= sequence[front];
                front++;
            
                // k == sum
            } else {
                // 부분 수열의 합 길이가 더 짧다면 
                if (answer[1] - answer[0] > (back - 1) - front) {
                    answer[0] = front;
                    answer[1] = back - 1;
                }
                
                sum -= sequence[front];
                front++;
            }
            
            
        }
        
        
        return answer;
    }
}