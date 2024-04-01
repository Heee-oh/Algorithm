class Solution {
    public int solution(int n) {
        
        String nBinary = Integer.toBinaryString(n);
        int length1 = nBinary.replace("0", "").length();
        int length2 = 0;
        
        for (int i = 1; i < 1000000; i++) {
            length2 = Integer.toBinaryString(n + i).replace("0", "").length();
            if (length1 == length2) {
                return n + i;
            }
        }
        
        return 0;
    }
}