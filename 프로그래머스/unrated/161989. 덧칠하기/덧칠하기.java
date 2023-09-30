class Solution {
    public int solution(int n, int m, int[] section) {
        int count = 1;
        int paint = section[0] + m - 1;
        
        for(int wall : section) {
            if(paint < wall) {
                paint = wall + m - 1;
                count++;
            }
        }
        return count;
    }
}


