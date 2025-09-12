import java.util.Arrays;

class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        
        for (int i = 0; i < numbers.length; i++) {
            String bit = Long.toBinaryString(numbers[i]);
            int len = bit.length();
            
            int h = (int)Math.ceil((Math.log(len + 1) / Math.log(2)));
            int n = (int)Math.pow(2, h) - 1;
            
            StringBuilder sb =  new StringBuilder();
            for (int j = len; j < n; j++) {
                sb.append('0');
            }
            sb.append(bit);
        
            int[] binTree = Arrays.stream(sb.toString().split(""))
                .mapToInt(Integer::parseInt)
                .toArray();
            
            answer[i] = dfs(binTree, 0, n - 1) ? 1: 0;
            
        }
        return answer;
    }
    
    private boolean dfs(int[] tree, int s, int e) {
        if (s >= e) return true;
        
        int mid = (s + e) / 2;
        
        if (tree[mid] == 0) {
            for (int i = s; i <=e; i++) {
                if (i == mid) continue;
                if (tree[i] == 1) return false;
            } 
        }     
        
        return dfs(tree, s, mid - 1) & dfs(tree, mid + 1, e);
    }
}