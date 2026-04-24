class Solution {
    public int furthestDistanceFromOrigin(String moves) {
        int diff = 0;
        int under = 0;

        for (char c : moves.toCharArray()) {
            if (c == '_') under++;
            else diff += (c =='R') ? 1 : -1; 
        }


        return Math.abs(diff) + under;
    }
}