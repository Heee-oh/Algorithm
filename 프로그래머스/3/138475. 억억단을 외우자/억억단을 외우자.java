class Solution {
    public int[] solution(int e, int[] starts) {
        int[] answer = new int[starts.length];
        int[] arr = new int[e + 1];
        int[] maxIdx = new int[e + 1];
        
        // 에라토스테네스의 체 활용
        // 소수가 아닌 배수들의 개수 카운트 (이는 즉 약수의 개수가 됨)
        for (int i = 1; i <= e; i++) {
            for (int j = i; j <= e; j += i) {
                arr[j] += 1;
            }
        }
        
        maxIdx[e] = e; // e~e에서 가장 많이 등장하는 것은 e
        
        // i~e 구간에서 가장 많이 등장하면서 작은 수를 기록
        for (int i = e-1; i >= 1; i--) {
            // 현재 i번째 약수 개수가 이전 i+1의 최대 약수 개수를 가지는 idx의 약수개수보다 많거나 같다면
            if (arr[i] >= arr[maxIdx[i+1]]) {
                maxIdx[i] = i;
                // 이전이 더 많다면 이전 idx 가진다.
            } else {
                maxIdx[i] = maxIdx[i+1];
            }
            
        }
        
        // starts의 각 원소로 s~e구간에서 정답을 maxIdx에 기록하였으므로 
        // idx로 접근
        for (int i = 0; i < starts.length; i++) {
            int n = starts[i];
            answer[i] = maxIdx[n];
        }
        
        
        return answer;
    }
}