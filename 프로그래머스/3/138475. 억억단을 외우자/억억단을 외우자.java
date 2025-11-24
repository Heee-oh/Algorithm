class Solution {
    // 각 수를 소인수 분해 해서 나온 수들의 개수 + 1이 등장 횟수
    public int[] solution(int e, int[] starts) {
        int[] answer = new int[starts.length];
        int[] arr = new int[e+1];
        int[] maxIdx = new int[e+1];
        
        // 각 수의 약수 개수 
        for (int i = 1; i <= e; i++) {
            for (int j = i; j <= e; j += i) {
                arr[j] += 1;
            }
        }
        
        // 뒤에서 부터 최대 약수 개수를 가진 번호
        maxIdx[e] = e;
        for (int i = e - 1; i >= 1; i--) {
            if (arr[i] >= arr[maxIdx[i+1]]) {
                maxIdx[i] = i;
            } else {
                maxIdx[i] = maxIdx[i+1];
            }
        }
        
      
        for (int i = 0; i < starts.length; i++) {
            int s = starts[i];
            answer[i] = maxIdx[s];
        }
        
        
        return answer;
    }
    
   
    
}