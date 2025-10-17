import java.util.*;

class Solution {
    static Map<String, List<String>> graph;
    static int n; // 총 방문 도시
    static Set<String> visited = new HashSet<>();
    static String[] answer;
    static boolean isInit = false;
    
    
    public String[] solution(String[][] tickets) {
        n = tickets.length + 1;
        answer = new String[n];
        
        graph = new HashMap<>();
        
        for (String[] ticket : tickets) {
            String start = ticket[0];
            String end = ticket[1];
            
            List<String> list = null;
            if (!graph.containsKey(start)) {
                list = new ArrayList<>();
                list.add(end);
                graph.put(start, list);
                
            } else {
                list = graph.get(start);
                list.add(end);
            }
            
        }
        
        
        String[] path = new String[n];
        path[0] = "ICN";
        
        dfs("ICN", 1, path);
        
        return answer;
    }
    
    private void dfs(String airport, int cnt, String[] path) {
        
        // 경로를 다 만들었으므로 비교 
        if (cnt == n) {
            if (!isInit) {
                isInit = true;
                answer = path.clone();
            }else if (!valid(path)) {
                answer = path.clone();
            }
            
            
            return;
        }
        
        if (!graph.containsKey(airport)) {
            return;
        }
        
        List<String> list = graph.get(airport);
        for (int i = 0; i < list.size(); i++) {
            String check = airport + i;
            
            if (!visited.contains(check)) {
                path[cnt] = list.get(i);
                visited.add(check);
                dfs(list.get(i), cnt + 1, path);
                visited.remove(check);
            }
            
        }
        
    }
    
    private boolean valid(String[] path) {
       
        for (int i = 0; i < n; i++) {
            int rs = answer[i].compareTo(path[i]);
            
            // path가 사전순으로 더 앞선다면 false
            if (rs > 0) return false;
            
            // answer가 사전순으로 더 앞선다면 true 바로 반환 
            if (rs < 0) return true;
            
        }
        
        return true;
    }
}