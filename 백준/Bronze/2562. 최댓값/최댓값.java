import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int idx = 0;
        int max = 0;
        int cnt = 0;

        for (int i = 0; i < 9; i++) {

            int num = Integer.parseInt(br.readLine());
            if (max < num) {
                idx = i + 1;
                max = num;
            }
        }



        // 여기에 코드 작성


        bw.write(max + "\n" + idx);  // 출력 내용 작성
        bw.flush();
        bw.close();
    }
}