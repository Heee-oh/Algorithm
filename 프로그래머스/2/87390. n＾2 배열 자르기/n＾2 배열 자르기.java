
class Solution {
    public int[] solution(int n, long left, long right) {
    
        int[] answer = new int[(int)(right-left+1)];
        
        for (int i = 0; i < answer.length; i++) {
            answer[i] = (int)(Math.max((left + i) / n, (left + i) % n) + 1);
        }

        return answer;
    }
}