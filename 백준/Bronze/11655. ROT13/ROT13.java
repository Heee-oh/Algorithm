import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        String s = br.readLine();
        
        
        for (char word : s.toCharArray()) {
            char rot13TransWord = word;
            if (word >= 65 && word <= 90) {
                rot13TransWord = (char) (65 + ((word + 13 - 65) % 26));
                
            }else if (word >= 97 && word <= 122) {
                rot13TransWord = (char)(97 + ((word + 13 - 97) % 26));
            }
            
            sb.append(rot13TransWord);
        }
        
        
        
        bw.write(sb.toString()+"\n"); // 문자열 or 변수명+"" 
		// 백준은 값도 줄바꿈 처리되어있으면 해줘야함
		bw.flush();  //버퍼 비우기  이것을 계속 호출하면 시간초과 뜸 한번만 아니면 필요할때만 호
		bw.close(); // 종료
    }
    

}