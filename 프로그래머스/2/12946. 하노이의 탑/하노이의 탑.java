import java.util.*;

class Solution {
        ArrayList<int[]> list = new ArrayList<>();
    
    public int[][] solution(int n) {
        
        // 반복
        // n-1 개를 2번으로 
        // 1개를 3번으로
        // n-1 개를 3번으로 
        
        
        Hanoi(n,1,2,3);
        
        
        return list.stream().toArray(int[][]::new);
    }
    
    // n, start(시작) mid(중앙) end(최종적으로 가야하는 곳)
    private void Hanoi(int n, int start, int mid, int end) {
        
        if (n == 1) {
            list.add(new int[] {start, end});
            return;
        }
        
        // n-1 개를 중앙으로
        Hanoi(n-1, start, end, mid);
        
        // 1개를 3번으로
        Hanoi(1, start, mid, end);
        
        // n-1개를 3번으로
        Hanoi(n-1, mid, start, end);
    
    }
}