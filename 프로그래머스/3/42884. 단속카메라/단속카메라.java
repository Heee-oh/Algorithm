import java.util.Arrays;

class Solution {
    // 고속도로 진입 지점을 기준으로 정렬
    // 끝 범위 안에 시작지점이 있으면 하나로 취급
    
    public int solution(int[][] routes) {
        
        Arrays.sort(routes, (o1, o2) -> o1[0] - o2[0]);
        
        int end = 1000000;
        int answer = 0;
        int idx = 0;
        
        while (idx < routes.length) {
        
            // 끝지점 안에 시작지점이 포함되어 있다면 
            if (end >= routes[idx][0]) {
                end = Math.min(end, routes[idx][1]);
                idx++;
                
            }else {
                answer++;
                end = 1000000;
            }
            
        }
        
        return answer + 1;
    }
}