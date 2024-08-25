class Solution {
    int count = 0;
    boolean flag = false;
    public int solution(String word) {
        dfs("", trans(word), 0);
        return count;
    }
    
    private void dfs(String a, String word, int depth) {

        if (a.equals(word)) {
            flag = true;
            return;
        }

        if (depth > 4) {
            return;
        }



        for (int i = 1; i <= 5; i++) {
            if (flag) return;

            count++;
            dfs(a + i, word, depth+1);
        }


    }

    private String trans(String word) {
        String s = "";
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == 'A') s += '1';
            else if (word.charAt(i) == 'E') s += '2';
            else if (word.charAt(i) == 'I') s += '3';
            else if (word.charAt(i) == 'O') s += '4';
            else if (word.charAt(i) == 'U') s += '5';

        }

        return s;
    }
}