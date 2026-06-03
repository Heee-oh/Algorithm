import java.util.Arrays;

class Solution {
    // a, A," ",".","-"
    // 영문자 시작, 숫자 하나 이상 포함
    
    class File {
        String origin;
        String head;
        int number;
        
        public File(String origin) {
            this.origin = origin;
            String[] tmp = origin.split("[0-9]+");
            head = tmp[0].toLowerCase(); // 대소문자 구분 x
            number = Integer.parseInt(origin.split("[^0-9]+")[1]);
        }
    }
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        File[] arr = new File[files.length];
        
        for (int i = 0; i < files.length; i++) {
            arr[i] = new File(files[i]);
        }
        
        Arrays.sort(arr, (a, b) -> {
            if (!a.head.equals(b.head)) {
                return a.head.compareTo(b.head);
            }
            
            if (a.number != b.number) {
                return a.number - b.number;    
            }
            
            return 0; // 헤드와 넘버가 같다면 순서 바꾸지 않음
        });
        
        
        for (int i = 0; i < arr.length; i++) {
            answer[i] = arr[i].origin;
        }
        
        return answer;
    }
}