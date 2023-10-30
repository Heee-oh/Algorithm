import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringBuilder sb = new StringBuilder();
        String word = br.readLine();
        ArrayList<String> list = new ArrayList<>();
        
        int length = word.length();
        for (int i = 1; i < length-1; i++) {
            for (int j = i+1; j < length; j++) {
                sb.append(word.substring(j, length) + word.substring(i, j) + word.substring(0, i));
                sb.reverse();
                list.add(sb.toString());
                sb.delete(0,length+12);
            }
        }
        
        
        Collections.sort(list);
 
        bw.write(list.get(0)); // 문자열 or 변수명+"" 
        bw.flush();  //버퍼 비우기
        bw.close(); // 종료
    }
}



