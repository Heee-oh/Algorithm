import java.util.ArrayList;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        
        ArrayList<Integer> list = new ArrayList<>();
        int max = 0, count = 0;
        
        for (int i = 0; i < speeds.length; i++) {
            int tmp = (100 - progresses[i]) / speeds[i];
            if ((100 - progresses[i]) % speeds[i] != 0) tmp++;
            
            if (max == 0) max = tmp;  
             
            if (max < tmp) {
                list.add(count);
                count = 0;
                max = tmp;
            }
            
            count++;
            
        }
        
        if (count != 0) list.add(count);

        
        
        
        return list.stream()
            .mapToInt(x->x)
            .toArray();
    }
}