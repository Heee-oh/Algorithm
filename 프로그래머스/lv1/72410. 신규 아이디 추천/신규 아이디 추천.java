class Solution {
    public String solution(String new_id) {
        
        // id 길이 3이상 15이하
        // a-z/-/_/./ 문자만 사용 가능
        // . 는 처음,끝,연속 사용 불가
        
        
        //1 단계 : 소문자화
        new_id = new_id.toLowerCase();
        
        //2 단계 : 사용가능 문자 제외 모두 삭제
        new_id = new_id.replaceAll("[^a-z|0-9|.|_|-]", "");
        //3 단계 : .가 2번 이상 연속되면 하나의 마침표로 치환
        new_id = new_id.replaceAll("\\.{2,}", ".");
        //4 단계 : . 가 처음 or 끝이면 삭제
        StringBuilder sb = new StringBuilder(new_id);
   
        if (sb.length() != 0 && sb.charAt(sb.length()-1) == '.') {
            sb.deleteCharAt(sb.length() - 1);
        }
        
        if(sb.length() != 0 && sb.charAt(0) == '.') {
            sb.deleteCharAt(0);
        }
        
        
        
        
        
        //5 단계 : 빈 문자열일 시 "a" 대입
        if(sb.length() == 0) {
            sb.append("a");
        }
        //6 단계 : id 길이 16자 이상이면 16부터 문자 모두 삭제
        if(sb.length() > 15) {
            sb.delete(15,sb.length());
            // 15번째 문자가 . 이면 삭제
            if(sb.charAt(14) == '.') {
                sb.deleteCharAt(14);
            }
        }
        
        
        //7 단계 : id 길이 2글자 이하라면 마지막 문자를 길이가 3 이 될 때까지 반복해서 끝에 붙임
        if(sb.length() <= 2) {
            char lastword = sb.charAt(sb.length()-1);
            while(sb.length() < 3) {
                sb.append(lastword);
            }
        }
        
        
        
        return sb.toString();
    }
}