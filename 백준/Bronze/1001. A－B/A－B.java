import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());


        // 여기에 코드 작성

        bw.write(Integer.parseInt(st.nextToken()) - Integer.parseInt(st.nextToken()) +"");  // 출력 내용 작성
        bw.flush();
        bw.close();
    }
}