import java.util.*;

class Solution {
    // 1. 글자 수 적은 순
    // 2. 같다면, 사전 순
    
    public String solution(long n, String[] bans) {
        String answer = "";
        long[] count = new long[12];
        count[0] = 1;
        int pos = 0;
        
        // 26^1 ~26^11 까지 미리 구하기
        for (int i = 1; i < 12; i++) {
            count[i] = count[i-1] * 26L;
            if (count[i] < n) {
                pos = i;
            }
        }
        
        // 길이별, 사전순 정렬
        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
            if (a.length() == b.length()) {
                return a.compareTo(b);
            }
            
            return a.length() - b.length();
        });
        
        for (String ban : bans) {
            pq.offer(ban);
        }
        
        // 밴하지 않았을때 n번째 주문서 찾기 
        int[] arr = new int[pos+1];
        long target = n;
        int idx = 0;
        
        // 밴없이 n번째 문자 추출
        // 현재 만들어진 문자를 기준으로 우선순위 큐에서 하나씩 꺼내서 
        // 꺼낸 문자가 더 앞서다면 카운팅, 더 앞서는게 없을때까지 꺼냄
        // (n + 카운팅 수)에 있는 문자를 기준으로 다시 우선순위 큐로 꺼내기 반복
        // 우선순위 큐에 남은게 만든 문자보다 사전순으로 나중이라면 해당 문자가 정답
        long newN = n;
        
        while (true) {
            long[] tmp = new long[2];
            int len = getLengthAndReduce(count, newN, tmp);
            long k = tmp[1]; // 순번
            String str = createStr(count, len, k);
            
            int cnt = 0;
            
            while (!pq.isEmpty()) {
                String ban = pq.peek();
                // ban이 더 앞선다면 
                if (str.length() > ban.length() 
                    || (str.length() == ban.length() && str.compareTo(ban) >= 0)) {
                    pq.poll();
                    cnt++;
                    
                } else {
                    break;
                }
            }
            
            // pq에 사전순으로 더 앞선것이 더이상 없으면 종료
            if (cnt == 0) {
                answer = str;
                break;
            }
            
            newN += cnt;
            target = newN;
        }
        
        return answer;
    }
    
    private char changeIdxToChar(int cnt) {
        return (char)('a' + cnt);
    }
    
    // 현재 len은 ...len -2, len -1자리 길이로 만든 모든 주문서를 통과하고 3자리 주문서중 k번째를 찾는중
    // 각 자리수의 시작은 aaaa.. 
    private String createStr(long[] count, int len, long k) {
        StringBuilder sb = new StringBuilder();
        long idx = k - 1; // 0-based 를 위해 -1

        for (int i = len - 1; i >= 0; i--) {
            long block = count[i];
            int digit = (int)(idx / block);   // 0~25
            sb.append((char)('a' + digit));
            idx %= block;
        }

        return sb.toString();
    }
    // 문자열 길이와 그 길이중 순번 구하기 
    private int getLengthAndReduce(long[] count, long target, long[] out) {
        int len = 1;
        long remain = target;

        while (remain > count[len]) {
            remain -= count[len];
            len++;
        }

        out[0] = len;      // 문자열 길이
        out[1] = remain;   // 그 길이 안의 순번 (1-based)
        return len;
    }
}