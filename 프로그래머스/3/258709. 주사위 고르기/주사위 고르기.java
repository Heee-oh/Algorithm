import java.util.*;

class Solution {

    static int n;
    static int[][] dice1;
    static List<int[]> A;

    
    
    public int[] solution(int[][] dice) {
        n = dice.length;
        dice1 = dice;
        A = new ArrayList<>();
        
        int max = 0;
        
        int[] answer = new int[n/2];
        // 우선 n/2가지를 뽑는다.
        makeCombine(0, 0, new int[n/2]);
        
        
        for (int[] aDice : A) {
            
            // 반대 쪽도 생성
            int[] bDice = makeBConbine(aDice);
        
            // 뽑은 주사위로 모든 합의 조합을 생성
            List<Integer> sumA = new ArrayList<>();
            List<Integer> sumB = new ArrayList<>();
            
            sumDice(aDice, 0,0, sumA);
            sumDice(bDice, 0,0, sumB);
            
            Collections.sort(sumB);
            
            // 이분 탐색으로 개수 구하기
            
            int cnt = 0;
            
            for (int i = 0; i < sumA.size(); i++) {
                int target = sumA.get(i);
                
                int tmpCnt = 0;
                int left = 0, right = sumB.size();
                
                while(left < right) {
                    int mid = (left + right) >>> 1;
                    
                    if (sumB.get(mid) < target) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                

                cnt += left;
                
            }
            
            
            // 최대면 값 생성
            if (max < cnt) {
                
                max = cnt;
                
                for (int i = 0; i < aDice.length; i++) {
                    answer[i] = aDice[i] + 1;
                }
            }
            
        }
        
        
        return answer;
    }
    
    private static void makeCombine(int idx, int depth, int[] tmp) {    
        if (depth == n/2) {
            A.add(tmp.clone());
            return;
        }
        for (int i = idx; i < n; i++) {
            tmp[depth] = i;
            makeCombine(i + 1, depth + 1, tmp);
        }
    }
    
    private static int[] makeBConbine(int[] aDice) {
        int[] b = new int[n/2];
        
        boolean[] isAdice = new boolean[n];
        
        for (int i = 0; i < aDice.length; i++) {
            isAdice[aDice[i]] = true;
        }
        
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (!isAdice[i] ) {
                b[idx++] = i;
            }
        }
        
        return b;
        
    }
    
    // 뽑은 주사위 배열, 주사위 idx, 합, 깊이, 합을 저장할 리스트
    private static void sumDice(int[] dice, int idx, int sum, List<Integer> list) {
        if (idx == n/2) {
            list.add(sum);
            return;
        }
        
        for (int i = 0; i < 6; i++) {
            sumDice(dice, idx + 1, sum + dice1[dice[idx]][i], list);
        }
    }
    
}