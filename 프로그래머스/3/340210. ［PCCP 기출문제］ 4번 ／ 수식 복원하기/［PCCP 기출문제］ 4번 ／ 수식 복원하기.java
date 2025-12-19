import java.util.*;

class Solution {
    // 완전탐색? 
    // 각 식을 계산해서 2~9진법으로 변환 해서 값을 비교
    public String[] solution(String[] expressions) {
        String[] arr = new String[expressions.length];
        String[] origin = new String[expressions.length];
        
        List<Integer> xList = new ArrayList<>();
        List<Integer> radixList = new ArrayList<>();
        
        // 가장 큰 수를 찾음 (이는 최소 진법을 알 수 있음)
        int radix = 1;
        for (String exp : expressions) {
            for (char c : exp.toCharArray()) {
                if (Character.isDigit(c) && radix < c -'0') {
                    radix = c - '0';
                }
            }
        }
        
        for (int i = 0; i < expressions.length; i++) {
            if (expressions[i].charAt(expressions[i].length() - 1) == 'X') {
                xList.add(i);
            }
        }
        
        // 각 진수로 변환해보기
        for (int i = radix + 1; i <= 9; i++) {
            for (int j = 0; j < expressions.length; j++) {
                String[] exp = expressions[j].split(" ");
                int a = Integer.parseInt(exp[0], i);
                int b = Integer.parseInt(exp[2], i);
                
                // 계산 
                int sum = (exp[1].equals("+") ? a + b : a - b);
                
                arr[j] = Integer.toString(sum, i);
                origin[j] = exp[exp.length  - 1];
            }
            
            // 비교 
            boolean check = true;
            for (int j = 0; j < arr.length; j++) {
                if (origin[j].equals("X")) continue; // X는 제외
                
                if (!arr[j].equals(origin[j])) {
                    check = false;
                    break;
                }
            }
            
            // 다 통과했다면 진법 후보에 추가
            if (check) {
                radixList.add(i);
            }

        }
        
        String[] answer = new String[xList.size()];
        String[] cur =  new String[xList.size()];
        Arrays.fill(cur, "");
        
        // 전부 다 같다면 값이 존재, 아니라면 ?
        for (int i = 0; i < xList.size(); i++) {
            StringBuilder sb = new StringBuilder();
            int idx = xList.get(i);
            String result = "";
            
            sb.append(expressions[idx].substring(0, expressions[idx].length() - 1));
            
            for (int rdx : radixList) {
                String[] exp = expressions[idx].split(" ");
                int a = Integer.parseInt(exp[0], rdx);
                int b = Integer.parseInt(exp[2], rdx);

                // 계산 
                int sum = (exp[1].equals("+") ? a + b : a - b);
                result = Integer.toString(sum, rdx);
                // 처음이면 그냥 계산
                if (cur[i] == "") {
                    cur[i] = result;
                    continue;
                    
                } else {
                    // 다르다면 바로 ? 
                    if (!cur[i].equals(result)) {
                        sb.append("?");
                        answer[i] = sb.toString();
                        break;
                    }
                }
            }
            
            // 후보 진법들이 전부 같은 값을 내놨다면 해당 수식은 결괏값이 확실함
            if (answer[i] == null) {
                sb.append(result);
                answer[i] = sb.toString();
            }


        }

        
        return answer;
    }
}