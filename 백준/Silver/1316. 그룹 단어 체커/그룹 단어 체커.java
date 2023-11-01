import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int words = Integer.parseInt(br.readLine());
        ArrayList<Character> charlist = new ArrayList<>();
        
        int total = words; // 전체 개수에서 그룹 단어가 아니면 빼기 
        
        while(words-->0) {
            char pre = 0;
            for(char c : br.readLine().toCharArray()) {
                if(pre == c || !charlist.contains(c)){
                    charlist.add(c);
                    pre = c;
                    
                }else if(pre != c && charlist.contains(c)){
                    total--;
                    break;
                }
                
            }
            charlist.clear();
        }

        bw.write(total+"\n"); // 문자열 or 변수명+"" 
		// 백준은 값도 줄바꿈 처리되어있으면 해줘야함
		bw.flush();  //버퍼 비우기
		bw.close(); // 종료
    }
}