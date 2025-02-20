import java.util.Arrays;

class Solution {
    // 승점만으로 답 판단, B팀은 순서를 바꿀 수 있음 
    public int solution(int[] A, int[] B) {
        int answer = 0;
        Arrays.sort(A);
        Arrays.sort(B);
        
        // 투 포인터를 쓰자
        
        int front = 0;
        int back = 0;

        while (front <= back && back < B.length) {
             
            if (A[front] < B[back]) {
                front++;
                back++;
                answer++;
                
            } else if (A[front] >= B[back]) {
                back++;
            }
        }
            
        
        
        return answer;
    }
}