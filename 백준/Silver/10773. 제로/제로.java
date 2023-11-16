import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Stack<Integer> stack = new Stack<>();
        
        int k = Integer.parseInt(br.readLine());
        int sum = 0;
        for(int i = 0; i < k; i++) {
            int n = Integer.parseInt(br.readLine());
            if(n == 0) 
                stack.pop();
            else
                stack.push(n);
        }
        int leng = stack.size();
        for(int i = 0; i < leng; i++) {
            sum += stack.pop();    
        }
        
            
        
        bw.write(sum+"\n"); // 문자열 or 변수명+"" 
		// 백준은 값도 줄바꿈 처리되어있으면 해줘야함
		bw.flush();  //버퍼 비우기  이것을 계속 호출하면 시간초과 뜸 한번만 아니면 필요할때만 호
		bw.close(); // 종료
    }
}
