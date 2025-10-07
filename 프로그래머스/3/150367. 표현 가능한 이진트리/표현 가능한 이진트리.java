class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        int idx = 0;
        
        for (int i = 0; i < numbers.length; i++) {
            String bit = Long.toString(numbers[i], 2);
            
            // 1. 포화 이진 트리로 만든다.
            
            // 2. 루트를 찾는다.
            
            // 3. 각 서브루트를 탐색하며 가능한지 여부 체크 
            // 3-1. 서브 루트가 0인데 그 하위 리프노트 중 1이 있으면 안됨
            
            // n = 2^(h) - 1
            
            int n = bit.length();
            
            int h = (int)Math.ceil((Math.log(n+1) / Math.log(2)));
            StringBuilder sb = new StringBuilder();
            
            for (int j = 0; j < Math.pow(2, h) - n - 1; j++) {
                sb.append("0");
            }
            
            sb.append(bit);
            
            char[] arr = sb.toString().toCharArray();
            
            answer[idx++] = dfs(arr, 0, arr.length - 1) ? 1 : 0;
            
        }
        return answer;
    }
    
    private boolean dfs(char[] arr, int s, int e) {
        if (s > e) {
            return true;
        }
        
        int mid = (s + e) >>> 1;
        
        if (arr[mid] == '0') {
            for (int i = s; i <= e; i++) {
                if (mid == i) continue;
                
                if (arr[i] == '1') {
                    return false;
                }

            }
        }
        
        
        return dfs(arr, s , mid - 1) & dfs(arr, mid + 1, e);
        
        
        
    }
}