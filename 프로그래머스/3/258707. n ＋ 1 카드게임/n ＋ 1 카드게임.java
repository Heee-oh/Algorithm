import java.util.*;

class Solution {
    public int solution(int coin, int[] cards) {
        int answer = 0;
        int n = cards.length;
        int target = n + 1;
        int draw = n / 3;
        
        boolean[] drawCard = new boolean[n + 1];
        boolean[] baseCard = new boolean[n + 1];
        List<Integer> cardList = new ArrayList<>();
        
        for (int i = 0; i < draw; i++) {
            drawCard[cards[i]] = true;
            cardList.add(cards[i]);
            baseCard[cards[i]] = true; 
        }
        
        
        int turn = 1;
        
        for (int i = n/3; i < n; i+= 2) {
            cardList.add(cards[i]);
            cardList.add(cards[i+1]);
            
            drawCard[cards[i]] = drawCard[cards[i+1]] = true;
            if (coin < 0) return turn;
            boolean flag = false;
            
            for (int card : cardList) {
                // 이미뽑혔으면 넘어감
                if (!drawCard[card]) continue;

                if (drawCard[target - card]) {
                    
                    if (!baseCard[card] && !baseCard[target-card]) {
                        if (coin - 2 >= 0) {
                            flag = true;  // 뽑힘 표식

                            coin -= 2;
                        } else {
                          continue;
                        }
                        
                    } else if (!baseCard[card] || !baseCard[target-card]) {
                        if (coin - 1 >= 0) {
                            flag = true;  // 뽑힘 표식
                            coin--;
                        } else {
                            continue;
                        }
                    } 
                    flag = true;  // 뽑힘 표식
                    drawCard[card] = false;
                    drawCard[target-card] = false;
                    break;
                }
            }
            
            // 2개를 못냈다면 종료
            if (!flag) {
                return turn;
            }
            
            turn++;
        }
        
        
        return turn;
    }
}