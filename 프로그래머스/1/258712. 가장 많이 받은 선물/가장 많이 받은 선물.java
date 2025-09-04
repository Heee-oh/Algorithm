import java.util.*;

class Solution {
    // 이번달까지 두 사람 중 많이 준 사람이 다음달에 하나 받음
    // 두 사람이 선물 x, 또는 개수가 같다면 선물 지수  더 큰사람 <- 작은 사람 (선물 1개)
    // 선물 지수 = 이번 달 까지 친구들에게 준 선물 수 - 받은 선물 수
    // 선물 지수도 같다면 다음 달에 선물을 주고받지 않음
    
    //  answer = 선물을 가장 많이 받을 친구가 받을 선물의 수 = 최대 선물 받는 수
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        
        // 이름 별 2차원 배열로 선물 주고받는 걸 기록
        Map<String, Integer> nameIdx = new HashMap<>();
        int[][] giftState = new int[friends.length][friends.length];
        int[][] giftIdc = new int[friends.length][3];
        int[] nextMonthGiftedCnt = new int[friends.length];
        
        // 선물지수 계산
        for (int i = 0; i < friends.length; i++) {
            nameIdx.put(friends[i], i);
        }
        
        for (int i = 0; i < gifts.length; i++) {
            String[] names = gifts[i].split(" ");
            
            int from = nameIdx.get(names[0]);
            int to = nameIdx.get(names[1]);
            
            giftState[from][to]++;
        }
        
        for (int i = 0; i < friends.length; i++) {
            // 준 선물
            int giftCnt = 0;
            for (int j = 0; j < friends.length; j++) {
                if (i == j) continue;
                giftCnt += giftState[i][j];
            }
            
            int giftedCnt = 0;
            // 받은 선물
            for (int j = 0; j < friends.length; j++) {
                if (i == j) continue;
                giftedCnt += giftState[j][i];
            }
            
            // 선물 지수 초기화
            giftIdc[i][0] = giftCnt;
            giftIdc[i][1] = giftedCnt;
            giftIdc[i][2] = giftCnt - giftedCnt;
        }
        
        
        // 조건 적용
                
        for (int i = 0; i < friends.length; i++) {
            for (int j = i; j < friends.length; j++) {
                if (i == j) continue;
                
                // A - B 의 관계에서 
                int a = giftState[i][j]; // A -> B 선물
                int b = giftState[j][i]; // B -> A 선물
                
                // 기록이 없거나 둘다 같다면
                if (a == 0 && b == 0 || a == b) {
                    // 선물 지수 비교
                    // A쪽이 선물지수가 더 크다면 A가 선물을 받음
                    if (giftIdc[i][2] > giftIdc[j][2]) {
                        nextMonthGiftedCnt[i]++;
                        
                    } else if (giftIdc[i][2] < giftIdc[j][2]) {
                        nextMonthGiftedCnt[j]++;
                        // 선물 지수도 같다면 선물 주지 않음
                    } else {
                        continue;
                    }
                    
                    // 선물 주고받은 기록이 있다면
                } else {
                    
                    // A가 더 많이 선물했으므로 A가 다음달에 받음
                    if (a > b) {
                        nextMonthGiftedCnt[i]++;
                        
                    } else {
                        nextMonthGiftedCnt[j]++;
                    }
                }
            }
        }
        
        // 최대값 찾기 
        return Arrays.stream(nextMonthGiftedCnt).max().getAsInt();
    }
}