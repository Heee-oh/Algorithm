class Solution {
    static int count = 0;
    
    public int solution(int[] numbers, int target) {
        dfs(numbers, target, 0, 0);
        return count;
    }
    
    // + - 로 두갈림길로 계속해서 나뉨 
    private void dfs(int[] arr, int target, int index, int sum) {
        if (index == arr.length) {
            if (sum == target) count++;
            return;
        } 
        
        sum += arr[index];
        dfs(arr, target, index+1, sum);
        
        sum -= arr[index] * 2;
        dfs(arr, target, index+1, sum);
        
    }
}