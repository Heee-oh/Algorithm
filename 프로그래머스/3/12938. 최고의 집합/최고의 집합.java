import java.util.Arrays;

class Solution {
    public int[] solution(int n, int s) {
        
        // n > s 면 자연수 n개로 s를 만들 수 없음. 각 원소가 최소값이 1인데 s가 n개수보다 작다면 불가능
        if (n > s) {
            return new int[] {-1};
        }
        
        int[] answer = new int[n];
        int num = s / n;
        
        Arrays.fill(answer, num);
        int tmp = s - (num * n); //
        
        // 남은 tmp값을 n으로 나눠서 그값을 
        for (int i = n - 1; i >= n - tmp; i--) {
            answer[i] += (tmp / n == 0 ? 1 : (tmp / n));
        }
        
        
        return answer;
    }
}