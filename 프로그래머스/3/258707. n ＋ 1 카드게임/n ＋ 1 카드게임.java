class Solution {
    static int n;
    
    public int solution(int coin, int[] cards) {
        int answer = 1;
        n = cards.length;
        
        boolean[] free = new boolean[n + 1];
        boolean[] myCard = new boolean[n + 1];
        
        for (int i = 0; i < n/3; i++) {
            myCard[cards[i]] = true;
            free[cards[i]] = true;
        }
        
        for (int i = n/3; i < n; i += 2) {
            myCard[cards[i]] = true;
            myCard[cards[i+1]] = true;
            
            boolean pass = false;
            int minCost = 3;
            int removeCard = -1;
            
            for (int j = 1; j <= n; j++) {
                
                if (!myCard[j]) continue;
                
                if (myCard[n+1 - j]) {
                    int cost = (free[j] ? 0 : 1) + (free[n + 1 -j] ? 0 : 1);
                    if (coin < cost || minCost <= cost) continue;
                    
                    pass = true;
                    minCost = cost;
                    removeCard = j;
                }
            }
            
            if (!pass) {
                break;
            }
            
            myCard[removeCard] = false;
            myCard[n + 1 - removeCard] = false;
            coin -= minCost;
            answer++;
        }
        
        
        return answer;
    }
}


