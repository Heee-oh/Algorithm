import java.util.*;

class Solution {
    static List<String> pattern = new ArrayList<>();
    static Set<String> set = new HashSet<>();
    static List<Set<String>> check = new ArrayList<>();
    static int setCnt = 0;
    
    public int solution(String[] user_id, String[] banned_id) {
        
        for (String id : banned_id) {
            StringBuilder sb = new StringBuilder();
            sb.append('^');
            for (char c : id.toCharArray()) {
                if (c == '*') {
                    sb.append('.');
                }else {
                    sb.append(c);
                }
            }
            sb.append('$');
            pattern.add(sb.toString());
        }
        
        dfs(user_id,0, 0);
        
        
        return setCnt;
    }
    
    private void dfs(String[] ids, int idx , int cnt) {
        
        if (cnt == pattern.size()) {
        
            // 모든 조합 꺼내서 확인 
            for (int i = 0; i < check.size(); i++) {
                Set<String> tmp = check.get(i);
                int checkCnt = 0;
                for (String id : set) {
                    if (tmp.contains(id)) {
                       checkCnt++; 
                    } 
                }
                
                // 한 조합에서 같은 구성이 있을 경우 중복처리 
                if (checkCnt == pattern.size()) {
                    return;
                }
            }
            
            Set<String> tmpSet = new HashSet<>();
            check.add(tmpSet);
        
            for (String id : set) {
                tmpSet.add(id);
            }

            // 동일한 조합이 아니라면 카운트
            setCnt++;
            return;
        }
        
        for (int i = 0; i < ids.length; i++) {
            for (int j = idx; j < pattern.size(); j++) {
                
                if (ids[i].matches(pattern.get(j)) 
                    && !set.contains(ids[i])) {
                    set.add(ids[i]);
   
                    dfs(ids, j + 1, cnt + 1);
                    set.remove(ids[i]);

                }
                
            }
            

            
        }
        
    }
}