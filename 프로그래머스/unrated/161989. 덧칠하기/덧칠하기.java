class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 0;
        int paint = 0;
        
        for(int no : section) {

            if(paint < no){
                paint = no + m - 1;
                answer++;
            }
        }
        return answer;
    }
}