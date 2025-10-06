import java.util.*;

class Solution {
    static int n;
    static List<int[]> alist = new ArrayList<>();
    static List<int[]> blist = new ArrayList<>();
    
    static Map<Integer, Integer> aSum;
    static Map<Integer, Integer> bSum;
    
    static int[][] odice;
    
    public int[] solution(int[][] dice) {
        n = dice.length;
        odice = dice;
        
        makeCombine(1, 0, new int[n/2]);
        
        int max = 0;
        int[] maxDice = null;
        
        for (int i = 0; i < alist.size(); i++) {
            boolean[] inAdice = new boolean[n + 1];
            int[] aDice = alist.get(i);
            int[] bDice = new int[n/2];
            
            // a다이스 체크
            for (int j = 0; j < n/2; j++) {
                inAdice[aDice[j]] = true;
            }
            
            // bdice 생성
            int idx = 0;
            for (int j = 1; j <= n; j++) {
                if (inAdice[j]) continue;
                bDice[idx] = j;
                idx++;
            }
            
            aSum = new HashMap<>();
            bSum = new HashMap<>();
            
            sumDice(0, 0, 0, aDice, true);
            sumDice(0, 0, 0, bDice, false);
            
            
            int[] aSumArr = new int[aSum.size()]; 
            idx = 0;
            
            for (int num : aSum.keySet()) {
                aSumArr[idx++] = num;
            }
            
            Arrays.sort(aSumArr);
            
            
            // 이분탐색으로 승리 개수 구하기 
            int cnt = 0;
            for (int bNum : bSum.keySet()) {
                
                int l = 0, r = aSumArr.length;
                
                while (l < r) {
                    int mid = (l + r) >>> 1;
                    if (aSumArr[mid] <= bNum) {
                        l = mid + 1;
                    } else {
                        r = mid;
                    }
                }
                
                int bcnt = bSum.get(bNum);

                for (int j = l; j < aSumArr.length; j++) {
                    cnt += aSum.get(aSumArr[j]) * bcnt;
                }

                
            }
            
            if (max < cnt) {
                max = cnt;
                maxDice = aDice;
            }
        }
        
        return maxDice;
    }
    
    private void makeCombine(int idx, int depth, int[] tmp) {
        if (depth == n/2) {
            alist.add(tmp.clone());
            return;
        }
        
        for (int i = idx; i <= n; i++) {
            tmp[depth] = i;
            makeCombine(i + 1, depth + 1, tmp);
        }
    }
    
    private void sumDice(int idx, int depth, int sum, int[] tmp, boolean isA) {
        if (depth == n/2) {
            if (isA) {
                int value = aSum.getOrDefault(sum, 0) + 1;
                aSum.put(sum, value);
            } else {
                int value = bSum.getOrDefault(sum, 0) + 1;
                bSum.put(sum, value);
            }
            return;
        }
        
        for (int i = idx; i < n/2; i++) {
            int select = tmp[i];
            int[] score = odice[select - 1];
            
            for (int j = 0; j < score.length; j++) {
                sumDice(i + 1, depth + 1, sum + score[j], tmp, isA);
            }
            
        }
    }
}