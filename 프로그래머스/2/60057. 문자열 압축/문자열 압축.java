class Solution {
 
    public int solution(String s) {
        StringBuilder sb = new StringBuilder(s);
        String answer = "a".repeat(2000);
        
        if (s.length() == 1) return 1;
        
        // 앞에서 부터 잘랐을때 나머지를 제외하고 탐색해야함
        for (int len = 1; len < s.length(); len++) {
            StringBuilder candidate = new StringBuilder();
            String prev = "";
            int cnt = 1;
            int l = 0;
            
            // len개로 나눴을때 구간만 탐색
            for (int i = 0; i < s.length() / len; i++) {
                String tmp = sb.substring(l, l+len);
                l += len;
                
                // 압축 대상 인지 
                if (!prev.equals(tmp)) {
                    
                    if (cnt == 1) {
                        candidate.append(prev);
                    } else {
                        candidate.append(cnt).append(prev);
                    }
                    
                    prev = tmp;
                    cnt = 1;
                } else {
                    cnt++;
                }
            }
            
            if (cnt == 1) {
                candidate.append(prev);
            } else {
                candidate.append(cnt).append(prev);
            }
            
            // len 개로 나누고 나머지 추가 
            candidate.append(sb.substring(s.length() - s.length() % len));
            
            if (answer.length() > candidate.length()) {
                answer = candidate.toString();
            }
        }
        
        return answer.length();
    }
}