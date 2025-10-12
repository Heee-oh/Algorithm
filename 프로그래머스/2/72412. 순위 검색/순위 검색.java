import java.util.*;

class Solution {
    
    String[][] baseInfo = {
        {"cpp", "java", "python", "-"},
        {"backend", "frontend", "-"},
        {"junior", "senior", "-"},
        {"chicken", "pizza", "-"}
    };
    
    Map<String, List<Integer>> map = new HashMap<>();
    static String[] info = new String[4];
    
    public int[] solution(String[] infos, String[] query) {
        int[] answer = new int[query.length];
        
        makeInfo(0, 0, new String[4]);

        for (String infomation : infos) {
            String[] split = infomation.split(" ");
            insertInfo(0, 0, split);
        }

        // 모든 점수 정렬
        for (List<Integer> list : map.values()) {
            Collections.sort(list);
        }
        
        int idx = 0;
        for (String q : query) {
            q = q.replaceAll(" and ", " "); // 중요 수정
            String[] str = q.split(" (?=\\d+$)");

            String key = str[0].trim();
            int target = Integer.parseInt(str[1]);

            List<Integer> list = map.get(key);
            if (list == null) {
                answer[idx++] = 0;
                continue;
            }

            int s = lowerBound(list, target);
            answer[idx++] = list.size() - s;
        }
        
        return answer;
    }
    
    private void insertInfo(int depth, int idx, String[] split) {
        if (depth == 4) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 4; i++) {
                sb.append(info[i]).append(" ");
            }  
            String key = sb.toString().trim();
            List<Integer> list = map.get(key);
            list.add(Integer.parseInt(split[4]));
            map.put(key, list);
            return;
        }
        info[idx] = split[idx];
        insertInfo(depth+1, idx + 1, split);
        
        info[idx] = "-";
        insertInfo(depth+1, idx + 1, split);
    }
    
    private void makeInfo(int depth, int idx, String[] query) {
        if (depth == 4) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 4; i++) {
                sb.append(query[i]).append(" ");
            }
            map.put(sb.toString().trim(), new ArrayList<>());
            return;
        }
        
        for (int i = 0; i < baseInfo[idx].length; i++) {
            query[idx] = baseInfo[idx][i];
            makeInfo(depth+1, idx + 1, query);
        }
    }

    private int lowerBound(List<Integer> list, int target) {
        int left = 0, right = list.size();
        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid) < target) left = mid + 1;
            else right = mid;
        }
        return left;
    }
}
