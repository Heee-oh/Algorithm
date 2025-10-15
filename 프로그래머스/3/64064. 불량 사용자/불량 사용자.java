import java.util.*;

class Solution {
    static List<String> pattern = new ArrayList<>();
    static Set<Set<String>> result = new HashSet<>();

    public int solution(String[] user_id, String[] banned_id) {
        for (String id : banned_id) {
            pattern.add(id.replace("*", "."));
        }
        dfs(user_id, 0, new HashSet<>());
        return result.size();
    }

    private void dfs(String[] user_id, int idx, Set<String> used) {
        if (idx == pattern.size()) {
            result.add(new HashSet<>(used));
            return;
        }

        for (String id : user_id) {
            if (id.matches("^" + pattern.get(idx) + "$") && !used.contains(id)) {
                used.add(id);
                dfs(user_id, idx + 1, used);
                used.remove(id);
            }
        }
    }
}
