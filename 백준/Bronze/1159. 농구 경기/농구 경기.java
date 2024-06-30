import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        HashMap<Character, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        
        
        
        int numPeople = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < numPeople; i++) {
            char firstName = br.readLine().charAt(0);
            map.put(firstName, map.getOrDefault(firstName, 0) + 1);
        }
        
        boolean flag = true;
        for (char firstWord : map.keySet()) {
            if (map.get(firstWord) >= 5) {
                sb.append(firstWord);
                flag = false;
            }
        }
        
        if (flag) sb.append("PREDAJA");
        
        
        
        
    
        
        
        bw.write(sb.toString()); // 문자열 or 변수명+"" 
		// 백준은 값도 줄바꿈 처리되어있으면 해줘야함
		bw.flush();  //버퍼 비우기  이것을 계속 호출하면 시간초과 뜸 한번만 아니면 필요할때만 호
		bw.close(); // 종료
    }
    

}