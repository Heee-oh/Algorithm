import java.util.*;

class Solution {
    
    // 가장 작은 수 선택후 소인수 분해
    // 분해된 수들로 해당 배열을 다 나눠본다. boolean으로 나눠지는지 확인
    // 다 나눠지면 상대 카드들을 나눠본다. boolean으로 나눠지는지 확인
    int max = 0;
    
    
    public int solution(int[] arrayA, int[] arrayB) {
        Set<Integer> setA = new HashSet<>();
        Set<Integer> setB = new HashSet<>();

        getPrimeFactorization(setA, arrayA[0]);
        getPrimeFactorization(setB, arrayB[0]);
        
        checkDiv(setA, arrayA, arrayB);
        checkDiv(setB, arrayB, arrayA);


        return max;
    }
    
    
    // 소인수 분해 
    private void getPrimeFactorization(Set<Integer> set, int n) {
        set.add(n);
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                set.add(i);
                set.add(n / i);
            }
        }
    }
    // 자신 것을 나눠서 나눠지는지 확인 
    private void checkDiv(Set<Integer> baseSet, int[] base, int[] target) {
        for (int n : baseSet) {
            boolean check = true;
            
            for (int j = 0; j < base.length; j++) {
                // 자신 것이 안나눠지거나 타겟이 나눠지면 멈춤
                if (base[j] % n != 0 || target[j] % n == 0) {
                    check = false;
                    break;
                }
            }
            
            // 두 조건을 만족하면 최댓값 찾기
            if (check) max = Math.max(n, max);
        }
    }
}