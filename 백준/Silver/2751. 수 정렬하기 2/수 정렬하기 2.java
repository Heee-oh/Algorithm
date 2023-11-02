import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        TreeSet<Integer> ts = new TreeSet<>();
        int N = Integer.parseInt(br.readLine());
        
        while(N-- > 0) {
            ts.add(Integer.parseInt(br.readLine()));
        }
        
        for(int n : ts){
            bw.write(n + "\n");
            
        }
        bw.flush();  //버퍼 비우기
		bw.close(); // 종료
    }
}
