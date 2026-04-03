class Solution {
    public int solution(int[][] signals) {
        int answer = -1;
        
        int[] cycle = new int[signals.length];
        
        // 각 사이클의 최소공배수를 구하여 각 신호등이 동시에 끝나는 지점을 구함 
        int lcm = 1;
        for (int i = 0; i < signals.length; i++) {
            cycle[i] = signals[i][0] + signals[i][1] + signals[i][2];
            lcm = lcm(lcm, cycle[i]);
        }
        
        
        // lcm초까지 탐색하여 이중 노란불이 동시인 구간을 탐색 
        for (int i = 1; i <= lcm; i++) {
            
            int cnt = 0; // 정전 신호등 개수 카운트
            for(int j = 0; j < cycle.length; j++) {
                int time = i % cycle[j]; // 사이클로 나머지연산하여 현재 무슨 불인지 판별하기 쉽게만듦
                
                // 초록불 < 노란불 <= 초록 + 노란불끝
                if (signals[j][0] < time && time <= signals[j][0] + signals[j][1]) {
                    cnt++;
                }
                
            }
            
            if (cnt == cycle.length) {
                answer = i;
                break;
            }
        }
        
        return answer;
    }
    
    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        
        return gcd(b, a % b);
    }
    
    private int lcm(int a, int b) {
        
        return (a * b / gcd(a,b));
        
    }
    
}