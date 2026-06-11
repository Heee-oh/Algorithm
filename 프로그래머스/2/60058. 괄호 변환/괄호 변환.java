class Solution {
    char[] arr;
    
    public String solution(String p) {
        arr = p.toCharArray();
        
        // 2. 올바른 괄호 문자열 변환 로직
        return v(0, arr.length - 1);
    }
    
    private String reverse(int s, int e) {
        if (e == arr.length - 1) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = s; i <= e; i++) {
            if (arr[i] == ')') arr[i] = '(';
            else arr[i] = ')';
            
            sb.append(arr[i]);
        }
        
        return sb.toString();
    }
    
    private String v(int s, int e) {
        if (s >= arr.length) {
            return "";
        }
        
        // 1. 올바른 괄호 문자열 판단
        if (isAlright(s, e)) {
            return charToString(s, e);
        }
        
        int open = 0, close = 0;
        for (int i = s; i <= e; i++) {
            if (arr[i] == '(') open++;
            else close++;
            
            // 3-1. u를 균형잡힌 괄호 문자열로 분리 
            // u가 더이상 분리 불가 균형잡힌 괄호 문자열이여야함
            if (open == close) {
                String v = v(i+1, e);
                
                if (isAlright(s, i)) {
                    String u = charToString(s, i);
                    return u + v;
                }
                
                String reverseU = reverse(s + 1, i - 1);
                return "(" + v + ")" + reverseU;
            }
        }
        
        return "";
    }
    
    private boolean isAlright(int s, int e) {
        int open = 0;
        
        for (int i = s; i <= e; i++) {
            char c = arr[i];
            if (c == ')') {
                if (open == 0) return false;
                open--;
            } else {
                open++;
            }
        }
        
        return true;
    }
    
    private String charToString(int s, int e) {
        StringBuilder sb = new StringBuilder();
        for (int i = s; i <= e; i++) {
            sb.append(arr[i]);
        }
        
        return sb.toString();
    }
}