class Solution {
    public int[] solution(String s) {
        // 0을 제거 (카운트 해야함)
        // 제거후 길이를 다시 2진법으로 바꿈
        // toBinaryString(int i) 사용
        
        char[] arr = s.toCharArray();
        String tempString = "";
        int zeroCount = 0;
        int convertCount = 0;
        
        do {
            for (char c : arr) {
                if (c == '1')
                    tempString += c;
                else 
                    zeroCount++;
            }
            
            arr = Integer.toBinaryString(tempString.length()).toCharArray();
            tempString = "";
            convertCount++;
            
        } while (arr.length > 1);
        
        return new int[] {convertCount, zeroCount};
    }
}