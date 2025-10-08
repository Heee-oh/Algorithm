class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int cnt = 0;
        int invisible_num = 0;
        int rank = 7;
        
        for(int i = 0; i < lottos.length; i++) {
            if(lottos[i] == 0){
                invisible_num++;
            }   
            
            for(int j = 0; j < win_nums.length; j++) {
                if(lottos[i] == win_nums[j]) {
                    cnt++;         

                }
            }
        }
        int max = rank - (cnt + invisible_num);
        int min = rank - cnt;
        
        if(max > 6) max = 6;
        if(min > 6) min = 6;
    
        return new int[] {max, min};
    }
}