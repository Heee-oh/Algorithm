import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String answer = br.readLine().replace("lj","a").replace("s=","a").replace("c=","a").replace("c-","a")
        .replace("dz=","a").replace("d-","a").replace("nj","a").replace("z=","a");
        

        bw.write(answer.length()+"\n"); // 문자열 or 변수명+"" 
		// 백준은 값도 줄바꿈 처리되어있으면 해줘야함
		bw.flush();  //버퍼 비우기
		bw.close(); // 종료
    }
}

