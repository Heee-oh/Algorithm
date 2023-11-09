import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>(); // stack 사용 최근 '(' 꺼내기
        int cnt = 1;

        while(n-- > 0) {
            boolean tmp = true;
            for(char bracket : br.readLine().toCharArray()) {

                if(stack.isEmpty() && bracket == ')' ) {
                    bw.write("NO\n");
                    tmp = false;
                    break;

                }else if(bracket == '(') {
                    stack.push(1);
                }else{
                    stack.pop();
                }
            }

            if(!tmp)
                continue;

            if(stack.isEmpty())
                bw.write("YES\n"); // 문자열 or 변수명+""
            else
                bw.write("NO\n"); // 문자열 or 변수명+""

            stack.clear();

        }

        bw.flush();
        bw.close(); // 종료
    }
}
