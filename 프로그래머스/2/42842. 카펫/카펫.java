class Solution {
    public int[] solution(int brown, int yellow) {
        
        // 너비는 brown + yellow 와 같다. 
        // 가로 길이 >= 세로
        
        int area = brown + yellow;
        
        for (int i = 3; i < 5001; i++) {
            if (area % i == 0 && (area / i) <= i ) {   
                if (check(new int[] {i, area/i}, brown, yellow)) 
                    return new int[] {i, area/i};     
                
            }
        }
        
        int[] answer = {};
        return answer;
    }
    
    public boolean check(int[] arr, int br, int ye) {
        int temp = (br - (arr[0] * 2)) / 2;
        if (temp <= arr[1] - 2)
            return true;
        
        return false;
    }
}
