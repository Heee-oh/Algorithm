class Solution {
    
    // xor 연산을 하면 비트가 다르면 1 아니면 0으로 됨
    // 즉 xor 연산 후 1의 개수를 카운트
    // 2개 이하라면 그 값이 정답
    
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < numbers.length; i++) {
            
            
            // 짝수는 마지막이 0이므로 +1만 해줘도 1비트만 달라짐 
            if (numbers[i] % 2 == 0) {
                answer[i] = numbers[i] + 1;
                
                // 홀수의 경우 오른쪽부터 1개수에 따라 다르게 처리
            } else {
                
                char[] bit = Long.toBinaryString(numbers[i]).toCharArray();
                int oneCnt = 0;
                int lastZeroIdx = -1;
                
                // 1 카운트
                for (int j = bit.length - 1; j >= 0; j--) {
                    if (bit[j] == '1') {
                        bit[j] = '0';
                        oneCnt++;
                        
                    } else {
                        bit[j] = '1';
                        lastZeroIdx = j;
                        break;
                    }
                }
                
                // 1이 하나이면 +1 해서 반환
                if (oneCnt == 1) {
                    answer[i] = numbers[i] + 1;  
                    
                    // 1이 2개 이상이면 oneCnt - 2 + 1개 만큼 뒤에서 부터 1을 채움
                } else {
                    
                    if (lastZeroIdx == -1) {
                        sb.append("1");
                    } 
                    
                    for (int j = bit.length - 1; j >= bit.length - (oneCnt - 1); j--) {
                        bit[j] = '1';
                    }
                    
                    sb.append(String.valueOf(bit));
                    answer[i] = Long.parseLong(sb.toString(), 2);
                }
            }
            sb.delete(0, sb.length());
           
        }
        
        return answer;
    }
}