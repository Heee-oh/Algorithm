import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<int[]> q = new LinkedList<>();
        int sumWeight = 0;
        int curTime = 0; // 현재 시간
        
        
        for (int i = 0; i < truck_weights.length; i++) {
            curTime++; // 매초가 상승
            
            while(!q.isEmpty()) {
                // 트럭이 올라갈 수 있는 최대 갯수이거나, 무게를 초과하면 
                if (q.size() == bridge_length || sumWeight + truck_weights[i] > weight || bridge_length == curTime - q.peek()[1]) {
                    int[] truck = q.poll();
                    
                    sumWeight -= truck[0]; // 맨 앞 트럭을 빼주고
                    curTime += bridge_length - (curTime - truck[1]); // 맨 앞 트럭이 빠진 시간을 현재 시간에 더함
                    
                }else {
                    break;
                }
                
            }
            
            
            // 트럭을 다리에 추가 
            q.add(new int[] {truck_weights[i], curTime});
            sumWeight += truck_weights[i];            
        }

        
        // 다리에 남은 트럭들 처리 
        while (!q.isEmpty()) {
            int[] truck = q.poll();
                    
            sumWeight -= truck[0];
            curTime += bridge_length - (curTime - truck[1]);
        }
        
        
        return curTime;
    }
}