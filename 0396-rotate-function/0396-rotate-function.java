class Solution {
    public int maxRotateFunction(int[] nums) {
        

        int n = nums.length;
        int[] arr = new int[n * 2];
        int[] pSum = new int[n * 2];
        

        for (int i = 0; i < n; i++) {
            arr[i] = arr[i + n] = nums[i];
        }

        // 누적합 생성 
        pSum[0] = arr[0];
        int F = 0;
        for (int i = 1; i < n * 2; i++) {
            pSum[i] = pSum[i-1] + arr[i % n]; 
            
            if (i < n) {
                F = F + arr[i % n] * (i % n);
            }
        }

        // F 최댓값을 구함
        int answer = F;
        for (int s = n - 1; s > 0; s--) {
            int e = s + n - 1;
            // F 수식에서 k가 올라갈 때마다 마지막 수는 사라지고 *0으로 처리된다. 
            // 그리고 0 ~ n-2요소들이 + 1씩 더 생긴다. 
            // ex) F(0) 4 x 0 3 x 1 2 x 2 6 x 3 
            // F(1)     4 x 1 3 x 2 2 x 3 6 x 0
            // k 증가시 기존 F에 누적합으로 0 ~ n-2 슬라이드 간격만큼 더하고, 맨 마지막 n-1은 0으로 변경해야하기에 빼준다.
            
            int add = pSum[e] - pSum[s];    
            F = F + add - (arr[e + 1] * (n - 1));
            answer = Math.max(answer, F);
        }

        return answer;
    }
}