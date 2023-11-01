import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String polyomino = br.readLine().replace("XXXX", "AAAA").replace("XX", "BB");
        
        if(polyomino.contains("X")) {
            bw.write("-1\n");
        }else{
            bw.write(polyomino+"\n"); // 문자열 or 변수명+""     
        }
        
		bw.flush();  //버퍼 비우기
		bw.close(); // 종료
    }
}
