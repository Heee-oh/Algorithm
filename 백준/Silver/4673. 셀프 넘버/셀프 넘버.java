import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
        
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = 10000;
        boolean[] check_selfN = new boolean[10000+36]; // 36인 이유는 가장 큰수인 9999생성자의 값이 10035이기 때문

        for(int i = 0; i <= 10000; i++) {
            check_selfN[i] = true; // 기본 값이 true이고 self 숫자가 아닌 것은 false
        }

        for(int i = 1; i <= N; i++) {
            check_selfN[d(i)] = false;
        }

        for(int i = 1; i <= N; i++) {
            if(check_selfN[i] == true) bw.write(i + "\n");
        }

      //  bw.write(변수명+"\n"); // 문자열 or 변수명+""
        // 백준은 값도 줄바꿈 처리되어있으면 해줘야함
        bw.flush();  //버퍼 비우기
        bw.close(); // 종료
    }

    public static int d(int n) {
        int sumN  = n;

        while(n != 0) {
            sumN += (n % 10);
            n /= 10;
        }
        return sumN;
    }
    
}