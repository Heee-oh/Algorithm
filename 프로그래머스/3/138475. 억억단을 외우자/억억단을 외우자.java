class Solution {
    public int[] solution(int e, int[] starts) {
        int[] answer = new int[starts.length];
        int[] arr = new int[e + 1];
        int[] maxIdx = new int[e + 1];
        
        
        for (int i = 1; i <= e; i++) {
            for (int j = i; j <= e; j += i) {
                arr[j] += 1;
            }
        }
        
        maxIdx[e] = e;
        for (int i = e-1; i >= 1; i--) {
            // 현재 i번째 약수 개수가 이전 i+1의 최대 약수 개수를 가지는 idx의 약수개수보다 많거나 같다면
            if (arr[i] >= arr[maxIdx[i+1]]) {
                maxIdx[i] = i;
                // 이전이 더 많다면 이전 idx 가진다.
            } else {
                maxIdx[i] = maxIdx[i+1];
            }
            
        }
        
        for (int i = 0; i < starts.length; i++) {
            int n = starts[i];
            answer[i] = maxIdx[n];
        }
        
        
        return answer;
    }
}