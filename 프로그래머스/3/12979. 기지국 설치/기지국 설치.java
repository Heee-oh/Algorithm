class Solution {
    // n 이 2억이니 배열로는 불가, O(n) 불가능
    // stations만을 가지고 풀어야함
    // stations의 값들의 범위를 구하고 전체 범위에서 뺀다음 그 나머지를 W*2 + 1로 나누자.
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int waveRange = w * 2 + 1;
        
        // 처음 기지국이 전파 도달 거리가 1번아파트까지 닿지 않을 경우
        if (stations[0] - w > 0) {
           answer += (((stations[0] - w - 1) / waveRange) 
               + ((stations[0] - w - 1) % waveRange == 0 ? 0 : 1));
        }
        
  
        // 기지국 사이의 전파거리가 안닿는 곳도 채움
        for (int i = 1; i < stations.length; i++) {
            int twoApartDist = (stations[i] - w) - (stations[i-1] + w) - 1;
            
            if (twoApartDist > 0) {
                answer += ((twoApartDist / waveRange) + (twoApartDist % waveRange == 0 ? 0 : 1));
            }
        }
        
        // 마지막 기지국이 마지막 아파트에 닿지 않을 경우
        if (stations[stations.length - 1] + w < n) {
            int tmp  = n - (stations[stations.length - 1] + w);
            answer += ((tmp / waveRange) + (tmp % waveRange == 0 ? 0 : 1));
        }

        
        
        return answer;
    }
}