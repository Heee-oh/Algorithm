import java.util.*;

class Solution {
    int rowSize, colSize;
    List<String>[] list;
    
    public int solution(String[][] relation) {
        int answer = 0;
        
        rowSize = relation.length;
        colSize = relation[0].length;
        
        list = new ArrayList[colSize + 1];
        
        for (int i = 1; i < list.length; i++) {
            list[i] = new ArrayList<>();
        }
        
        // 부호키 조합을 미리 만듦
        dfs(1, 0, "");
        
        // 후보키 후보
        Set<Integer> candidate = new HashSet<>();
        
        // i 는 후보키 속성 개수
        for (int i = 1; i <= colSize; i++) {
            
            // 후보키 조합에 대하여
            for (String select : list[i]) {
                Set<String> set = new HashSet<>();
                
                // 각 행에 대하여 
                for (int j = 0; j < rowSize; j++) {
                    String row = "";
                    
                    // 후보키 생성 
                    for (int k = 0; k < select.length(); k++) {
                        int idx = select.charAt(k) - '0';
                        row += relation[j][idx];
                    }
                    
                    set.add(row);
                }
                
                // 선택한 후보키 조합이 유일하게 식별하여 row 개수와 같다면 
                if (set.size() == rowSize) {
                    int bit = 0; // 비트 연산으로 선택한 조합의 비트값 생성 
                    for (int b = 0; b < select.length(); b++) {
                        bit |= (1 << select.charAt(b) - '0');
                    }
                    
                    boolean isMinimal = true;
                    // 각 후보키에 대하여 최소성 검사 

                    for (int cand : candidate) {
                        if ((bit & cand) == cand) {
                            isMinimal = false;
                            break;
                        }
                    }

                    if (isMinimal) {
                        candidate.add(bit);
                    }
                    
                  
                }
            }
        }
        
        
        return candidate.size();
    }
    
    
    // 컬럼 조합을 미리 뽑아놓기
    private void dfs(int cnt, int idx, String select) {
        if (cnt > colSize) {
            return;
        }
        
        for (int i = idx; i < colSize; i++) {
            String next = select + i;
            list[cnt].add(next);
            dfs(cnt+1, i+1, next);
        }
    }
}