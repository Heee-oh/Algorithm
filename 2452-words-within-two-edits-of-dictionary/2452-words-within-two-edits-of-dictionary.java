
class Solution {
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        int n = queries[0].length();

        char[][] q = new char[queries.length][n];
        boolean[] visited = new boolean[queries.length];
        List<String> ans = new ArrayList<>();

        for (int j = 0; j < queries.length; j++) {
            q[j] = queries[j].toCharArray();
        }

        for (int i = 0; i < dictionary.length; i++) {
            char[] base = dictionary[i].toCharArray();
            for (int j = 0; j < queries.length; j++) {
                int cnt = 0;

                for (int k = 0; k < n; k++) {
                    if (base[k] - q[j][k] != 0) {
                        cnt++;
                    }

                    if (cnt > 2) {
                        break;
                    }
                }

                if (cnt <= 2 && !visited[j]) {
                    visited[j] = true;
                }


            }
        }

        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) continue;
            ans.add(queries[i]);
        }
        
        return ans;
    }
}