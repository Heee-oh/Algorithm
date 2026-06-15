class Solution {
    public int solution(int[] players, int m, int k) {
        int[] close = new int[48];
        int answer = 0;
        int cur = 0;
        
        for (int t = 0; t < 24; t++) {
            if (players[t] >= (cur+1) * m) {
                int add = (players[t] - (cur * m)) / m;
                close[t + k - 1] += add;
                answer += add;
                cur += add;
                
            }
            cur -= close[t]; // 증설이 끝난 서버가 있다면 차감
        }
        
        return answer;
    }
}