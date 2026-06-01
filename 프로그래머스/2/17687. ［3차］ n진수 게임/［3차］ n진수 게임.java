class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= 1000000; i++) {
            sb.append(Integer.toString(i, n));
        }
        String str = sb.toString();
        sb = new StringBuilder();
        int cnt = 0;
        for (int i = p - 1; cnt < t; i+= m, cnt++) {
            char c = str.charAt(i);
            if (Character.isAlphabetic(c)) {
                c -= 32;
            }
            
            sb.append(c);
        }
        
        return sb.toString();
    }
}