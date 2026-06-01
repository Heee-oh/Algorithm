class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder sb = new StringBuilder();
        
        // 최악의 경우 16진수로 t = 1000개, m = 100, p = 100일 경우가 있기에
        // 저 조건을 만족하는 안전한 수치만큼 번호 생성
        for (int i = 0; i <= 30000; i++) {
            sb.append(Integer.toString(i, n));
        }
        String str = sb.toString();
        sb = new StringBuilder();
        int cnt = 0;
        for (int i = p - 1; cnt < t ; i+= m, cnt++) {
            char c = str.charAt(i);
            if (Character.isAlphabetic(c)) {
                c -= 32;
            }
            
            sb.append(c);
        }
        
        return sb.toString();
    }
}